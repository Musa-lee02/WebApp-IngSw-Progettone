import { Component, OnInit } from '@angular/core';
import { ServizioAnnunciService } from '../../../service/servizio-annunci.service';
import { faArrowDown } from '@fortawesome/free-solid-svg-icons';
import {ActivatedRoute} from "@angular/router";
import {BackEndService} from "../../../service/BackEndService";
import {AnnuncioService} from "../../../service/AnnuncioService";
import {Annuncio} from "../../../model/Annuncio";

@Component({
  selector: 'app-recensioni',
  templateUrl: './recensioni.component.html',
  styleUrl: './recensioni.component.css'
})
export class RecensioniComponent implements OnInit {

  constructor (private backEndService : BackEndService, private annunciService : AnnuncioService, private route: ActivatedRoute){}
  starImg: string = "../../assets/star.png";
  annunci:Annuncio[]
  propostaAccettata: any
  ArrowDown=faArrowDown
  starsClicked: boolean = false
  entita: string | null

  ngOnInit(): void {

    this.annunciService.getAnnunciFinalizzati().subscribe(data =>{


      this.annunci=data;
      console.log(this.annunci)
      console.log(this.entita)

    })
    this.entita=localStorage.getItem("scelta")
    console.log(this.entita)
    if(this.route.snapshot.paramMap.get('Entita')){

      this.entita=this.route.snapshot.paramMap.get('Entita')!;
      if(this.entita==="Cliente")
        this.entita=="Cliente";
      if(this.entita==="Lavoratore")
        this.entita=="Lavoratore";
    }
  }

  setPropostaAccettata(id :string){

  }


  getPropostaAccettataImg(){


    if(this.propostaAccettata){

      return this.propostaAccettata.img
    }
  }
  getPropostaAccettataNome(){


    if(this.propostaAccettata){

      return this.propostaAccettata.username
    }
  }

  setStarsClicked(voto : number){

    if(this.starsClicked){
      this.starsClicked=false
      voto=0

    }
    else
    this.starsClicked=true

  }
  isStarsClicked(){

    return this.starsClicked
  }
  getEntita(){


    return this.entita
  }


}
