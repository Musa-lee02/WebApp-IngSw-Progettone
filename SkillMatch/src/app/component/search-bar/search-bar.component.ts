import { Component, OnDestroy, OnInit } from '@angular/core';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import {ActivatedRoute, Router} from '@angular/router';
import { CardsVetrinaComponent } from '../cards-vetrina/cards-vetrina.component';
import {AnnuncioService} from "../../service/AnnuncioService";
import {Ambito} from "../../model/Ambito";
import {Province} from "../../model/Province";
import {HttpClient} from "@angular/common/http";
import {Annuncio} from "../../model/Annuncio";
import {Lavoratore} from "../../model/Lavoratore";
import {LavoratoreFieldService} from "../../service/LavoratoreFieldService";
import {Cliente} from "../../model/Cliente";


@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrl: './search-bar.component.css'
})
export class SearchBarComponent implements OnInit, OnDestroy {

  ambiti : Ambito[];
  province : Province[];
  selectedAmbito: string;
  selectedZona: string;
  annunci: Annuncio[]

  constructor(private router: Router, private servizioAnnunci:
              ServizioAnnunciService, private annunciService : AnnuncioService,
              private httpClient: HttpClient
  ){}
  ngOnDestroy(): void {}

  ngOnInit(): void {
    this.annunciService.getAmbiti().subscribe(data=>{

      this.ambiti=data
      this.sortAmbitiConsigliati()

    })

    this.httpClient.get<Province[]>('http://mobilio.altervista.org').subscribe( data =>
        {
          console.log(data)
          this.province=data
          this.sortZoneConsigliate()
        }
    )
  }
  sortAmbitiConsigliati(){
    var utenteLogged = localStorage.getItem("utente");
    let utenteL : Lavoratore | Cliente = JSON.parse(utenteLogged!);

    if ('ambiti' in utenteL) {
        let ambiti = utenteL.ambiti;
        for (let i = 0; i < ambiti.length; i++) {
            let index = this.ambiti.findIndex(a => a.nome === ambiti[i].nome);
            if (index !== -1) {
                let ambitoRimosso = this.ambiti.splice(index, 1)[0];
                ambitoRimosso.consigliato = true;
                this.ambiti.unshift(ambitoRimosso);
            }
        }
    }
  }
  sortZoneConsigliate(){
    var utenteLogged = localStorage.getItem("utente");
    let utenteL : Lavoratore | Cliente = JSON.parse(utenteLogged!);
    let provincia = utenteL.provincia

    let index = this.province.findIndex(p => p.nome === provincia);

    // Se trovi l'indice, sposta l'elemento corrispondente all'inizio dell'array
    if (index !== -1) {
      let provinciaRimossa = this.province.splice(index, 1)[0]; // Rimuovi l'elemento e ottienilo
      provinciaRimossa.consigliata = true;
      console.log("Prov rimossa: " + provinciaRimossa.nome)
      this.province.unshift(provinciaRimossa); // Aggiungi l'elemento all'inizio dell'array
    }
  }

  ambitoClicked(ambito: string){
    this.selectedAmbito=ambito!

    console.log(this.selectedAmbito)
  }

  zonaClicked(zona: string){

    this.selectedZona=zona

    console.log(this.selectedZona)

  }



  searchClicked(){

      console.log(this.selectedAmbito , this.selectedZona)

      if (this.selectedAmbito==undefined || this.selectedAmbito==""){

          this.selectedAmbito="all"

      }
      if (this.selectedZona==undefined  || this.selectedZona==""){
          this.selectedZona="all"
      }
      console.log("Esplora/"+this.selectedAmbito+"/"+this.selectedZona)
      this.annunciService.getAnnunciByAmbitoEZona(this.selectedAmbito, this.selectedZona).subscribe(data=>
      {

        this.annunci=data
        this.servizioAnnunci.setAnnunciEsplora(this.annunci)
        this.router.navigate(["Esplora/"+this.selectedAmbito+"/"+this.selectedZona])

      })

  }


}
