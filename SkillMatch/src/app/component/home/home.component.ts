import { Component, Input, OnInit } from '@angular/core';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import { FormControl } from '@angular/forms';
import { Observable, map, retry, startWith } from 'rxjs';
import { Annuncio } from '../../model/Annuncio';
import { BackEndService } from '../../service/BackEndService';
import {AnnuncioService} from "../../service/AnnuncioService";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {


  myControl = new FormControl();
  ambiti : any;
  province : any;
  annunci: Annuncio[];

  icon= faSearch;
  title = 'SkillMatch';
  sizeAnnunci:number=0;

  selectedAmbito: string = '';
  selectedZona: string = '';

  constructor(private servizioAnnunci: ServizioAnnunciService, private backEndService: BackEndService, private annuncioService: AnnuncioService){}

  ngOnInit(): void {

    this.annuncioService.getAnnunci().subscribe(data=> {


        this.annunci = data
      }
    )



    //this.annunci=this.servizioAnnunci.getAnnunci();

  }


}

