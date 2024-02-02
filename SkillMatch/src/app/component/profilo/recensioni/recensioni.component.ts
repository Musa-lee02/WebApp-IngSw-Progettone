import { Component, OnInit } from '@angular/core';
import { ServizioAnnunciService } from '../../../service/servizio-annunci.service';
import { faArrowDown } from '@fortawesome/free-solid-svg-icons';
import {ActivatedRoute} from "@angular/router";
import {BackEndService} from "../../../service/BackEndService";
import {AnnuncioService} from "../../../service/AnnuncioService";
import {Annuncio} from "../../../model/Annuncio";
import {FormGroup} from "@angular/forms";

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

  nuovaRecensione:FormGroup

  starVoto : number

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

    this.starsClicked = !this.starsClicked;
    this.starVoto = this.starsClicked ? voto : 0;
    console.log(this.starVoto);

  }
  isStarsClicked(){

    return this.starsClicked
  }

  insertDescrizione(descrizione: Event): void {
      const target = descrizione.target as HTMLTextAreaElement;
      const valoreTextArea = target.value;
      console.log(valoreTextArea); // Stampa il valore della textarea
      // Puoi fare qualcosa con il valore ottenuto, ad esempio assegnarlo a una variabile o elaborarlo in qualche modo
  }
  getEntita(){


    return this.entita
  }


}
