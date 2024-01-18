import { Component, OnInit } from '@angular/core';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import { ActivatedRoute } from '@angular/router';
import { CardsVetrinaComponent } from '../cards-vetrina/cards-vetrina.component';


@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrl: './search-bar.component.css'
})
export class SearchBarComponent implements OnInit {

  ambiti : any;
  province : any;
  selectedAmbito: string = '';
  selectedZona: string = ''; 

  constructor(private router: ActivatedRoute, private servizioAnnunci: ServizioAnnunciService){}

  ngOnInit(): void {
    this.ambiti=this.servizioAnnunci.getAmbiti();
    this.province=this.servizioAnnunci.getProvince();
    
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
      this.servizioAnnunci.setAnnunci()
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