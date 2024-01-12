import { Component, OnInit } from '@angular/core';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import { FormControl } from '@angular/forms';
import { Observable, map, retry, startWith } from 'rxjs';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  myControl = new FormControl();
  ambiti : any;
  province : any;

  icon= faSearch;
  title = 'SkillMatch';
  sizeAnnunci:number=0;

  selectedAmbito: string = '';
  selectedZona: string = '';

  constructor(private servizioAnnunci: ServizioAnnunciService){}

  ngOnInit(): void {
    this.ambiti=this.servizioAnnunci.getAmbiti();
    this.province=this.servizioAnnunci.getProvince();
    this.sizeAnnunci=this.servizioAnnunci.annunciGetSize()
    
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

  getSelectedAmbito(){
    return this.servizioAnnunci.getSelectAmbito()
  }
  getSelectedZona(){
    return this.servizioAnnunci.getSelectZona()
  }
  getParametriRicerca(){
    console.log("" + this.getSelectedAmbito() + "/" + this.getSelectedZona())
    return "" + this.getSelectedAmbito() + "/" + this.getSelectedZona()
  }

  
  searchClick(){
    // Verifica se l'ambito e la zona sono validi
    if (this.servizioAnnunci.isAmbitoValid() && 
    this.servizioAnnunci.isZoneValid()) {
      return true;
    }
    return false;
  }

  getRange(sizeAnnunci: number){
    return this.sizeAnnunci
  }
}

