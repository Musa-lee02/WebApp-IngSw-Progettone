import { Component, OnDestroy, OnInit } from '@angular/core';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import {ActivatedRoute, Router} from '@angular/router';
import { CardsVetrinaComponent } from '../cards-vetrina/cards-vetrina.component';
import {AnnuncioService} from "../../service/AnnuncioService";
import {Ambito} from "../../model/Ambito";
import {Province} from "../../model/Province";
import {HttpClient} from "@angular/common/http";
import {Annuncio} from "../../model/Annuncio";


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
  ngOnDestroy(): void {

  }

  ngOnInit(): void {
    this.annunciService.getAmbiti().subscribe(data=>{

      this.ambiti=data

    })

    this.httpClient.get<Province[]>('http://mobilio.altervista.org').subscribe( data =>
        {

          this.province=data
        }
    )

  }

  ambitoClicked(ambito: string){


    this.selectedAmbito=ambito!

    console.log(this.selectedAmbito)
  }

  zonaClicked(zona: string){

    this.selectedZona=zona


  }



  searchClicked(){



      if (this.selectedAmbito==undefined || this.selectedAmbito==""){

          this.selectedAmbito="all"

      }
      if (this.selectedZona==undefined  || this.selectedZona==""){
          this.selectedZona="all"
      }

      this.annunciService.getAnnunciByAmbitoEZona(this.selectedAmbito, this.selectedZona).subscribe(data=>
      {

        this.annunci=data
        this.servizioAnnunci.setAnnunciEsplora(this.annunci)
        this.router.navigate(["Esplora/"+this.selectedAmbito+"/"+this.selectedZona])

      })

  }


}
