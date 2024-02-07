import { Component, Input, OnInit } from '@angular/core';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import { FormControl } from '@angular/forms';
import { Observable, map, retry, startWith } from 'rxjs';
import { Annuncio } from '../../model/Annuncio';
import { BackEndService } from '../../service/BackEndService';
import {AnnuncioService} from "../../service/AnnuncioService";
import { Router } from '@angular/router';
import {Lavoratore} from "../../model/Lavoratore";
import {Cliente} from "../../model/Cliente";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {


  ambito: string;
  starImg: string = "../../assets/star.jpg";
  isHome:boolean=false;
  annunciConsigliati: Annuncio[] = [];

  myControl = new FormControl();
  ambiti : any;
  province : any;
  annunci: Annuncio[];

  icon= faSearch;
  title = 'SkillMatch';
  sizeAnnunci:number=0;

  selectedAmbito: string = '';
  selectedZona: string = '';

  constructor( private servizioAnnunci: ServizioAnnunciService, private backEndService: BackEndService, private annuncioService: AnnuncioService){}

  ngOnInit(): void {

    this.annuncioService.getAnnunci().subscribe(data=> {

        console.log(data)
        console.log(data.length)
        this.annunci = data
      this.sortAnnunciConsigliati()

      }
    )


  }

  sortAnnunciConsigliati(){
    const utenteLogged = localStorage.getItem("utente");
    const utenteL: Lavoratore | Cliente = JSON.parse(utenteLogged!);
    const provincia = utenteL.provincia;

    // Ottenere gli ambiti dell'utente, se presenti
    if ('ambiti' in utenteL) { // se l'utente Ã¨ un lavoratore
      const ambitiUtente  = utenteL.ambiti.map((ambito: any) => ambito.nome);
      // Ordinare gli annunci in base agli ambiti in comune con l'utente
      for (let i = 0; i < ambitiUtente.length; i++) {
        const index = this.annunci.findIndex(a => a.ambito.nome === ambitiUtente[i]);

        if (index !== -1) {
          const tipo = this.annunci[index].ambito.nome;

          for(let j = index; j < this.annunci.length; j++){
            if (this.annunci[j].ambito.nome === tipo) {
              const annuncioRimosso = this.annunci.splice(j, 1)[0];
              annuncioRimosso.consigliato = true;
              this.annunci.unshift(annuncioRimosso);
              this.annunciConsigliati.unshift(annuncioRimosso);
            }
          }
        }
      }return
    }
    let index = this.annunci.findIndex(a => a.provinciaAnnuncio === provincia);
    // Se trovi l'indice, sposta l'elemento corrispondente all'inizio dell'array
    if (index !== -1) {
      for(let i = 0; i < this.annunci.length; i++){
        if (this.annunci[i].provinciaAnnuncio === provincia) {
          const annuncioRimosso = this.annunci.splice(i, 1)[0];
          annuncioRimosso.consigliato = true;
          this.annunci.unshift(annuncioRimosso);
          this.annunciConsigliati.unshift(annuncioRimosso);
        }
      }
    }
  }


}
