import { Component, OnDestroy, OnInit } from '@angular/core';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import { ActivatedRoute } from '@angular/router';
import { CardsVetrinaComponent } from '../cards-vetrina/cards-vetrina.component';
import {AnnuncioService} from "../../service/AnnuncioService";
import {Ambito} from "../../model/Ambito";
import {Province} from "../../model/Province";
import {HttpClient} from "@angular/common/http";


@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrl: './search-bar.component.css'
})
export class SearchBarComponent implements OnInit, OnDestroy {

  ambiti : Ambito[];
  province : Province[];
  selectedAmbito: string = '';
  selectedZona: string = '';

  constructor(private router: ActivatedRoute, private servizioAnnunci:
              ServizioAnnunciService, private annunciService : AnnuncioService,
              private httpClient: HttpClient
  ){}
  ngOnDestroy(): void {
    console.log("suca")
  }

  ngOnInit(): void {
    this.annunciService.getAmbiti().subscribe(data=>{

      this.ambiti=data

    })

    this.httpClient.get<Province[]>('http://mobilio.altervista.org').subscribe( data =>
        {
          console.log(data)
          this.province=data
        }
    )

  }

  ambitoClicked(ambito: string){
    if (this.servizioAnnunci.ambiti.includes(ambito)) {
      this.servizioAnnunci.setSelectAmbito(ambito)
    }
  }
  zonaClicked(zona: string){
    if (this.servizioAnnunci.province.includes(zona)) {
      this.servizioAnnunci.setSelectZona(zona)
    }
  }


  searchValid(){
    // Verifica se l'ambito e la zona sono validi
    if (this.servizioAnnunci.isAmbitoValid() &&
    this.servizioAnnunci.isZoneValid()) {
      return true;
    }
    return false;
  }
  searchClicked(){
    if (this.searchValid()) {
      /*this.servizioAnnunci.buttonSearchClicked()*/
      this.annunciService.getAnnunciByAmbitoEZona(this.getSelectedAmbito(), this.getSelectedZona())
    }
  }

  getSelectedAmbito(){
    return this.servizioAnnunci.getSelectAmbito()
  }
  getSelectedZona(){
    return this.servizioAnnunci.getSelectZona()
  }
  getParametriRicerca(){
    return "" + this.getSelectedAmbito() + "/" + this.getSelectedZona()
  }

}
