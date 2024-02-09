import { Component, OnInit } from '@angular/core';
import { ServizioAnnunciService } from '../../../service/servizio-annunci.service';
import { faArrowDown } from '@fortawesome/free-solid-svg-icons';
import {ActivatedRoute} from "@angular/router";
import {BackEndService} from "../../../service/BackEndService";
import {AnnuncioService} from "../../../service/AnnuncioService";
import {Annuncio} from "../../../model/Annuncio";
import {FormControl, FormGroup, Validators} from "@angular/forms";

import  {Recensione} from "../../../model/Recensione";
import {Ambito} from "../../../model/Ambito";
import {Province} from "../../../model/Province";
import {Lavoratore} from "../../../model/Lavoratore";
import {Cliente} from "../../../model/Cliente";

type RecensioneType = {
  idRecensione: number
  titolo: string
  descrizione: string
  punteggio: number
  lavoratore: Lavoratore | undefined
  cliente: Cliente | undefined
}

@Component({
  selector: 'app-recensioni',
  templateUrl: './recensioni.component.html',
  styleUrl: './recensioni.component.css'
})
export class RecensioniComponent implements OnInit {

  constructor (private backEndService : BackEndService, private annunciService : AnnuncioService, private route: ActivatedRoute){}
  starImg: string = "../../assets/star.png";
  starBorderImg: string = "../../assets/starborder.png";
  annunci:Annuncio[]
  recensioneT : RecensioneType
  recensioneForm: FormGroup
  propostaAccettata: any
  ArrowDown=faArrowDown
  starsClicked: boolean = false
  entita: string | null
  recensioniLavoratore: RecensioneType[]

  mediaRecensioniRicevute: string

  ngOnInit(): void {

    this.annunciService.getAnnunciFinalizzati().subscribe(data =>{


      this.annunci=data;
      console.log(this.annunci)
      console.log(this.entita)

    })
    this.entita=localStorage.getItem("scelta")
    //console.log("Entità " + this.entita)

    if(this.entita==="lavoratore"){

      this.annunciService.getRecensioniLavoratore().subscribe(data =>{
        this.recensioniLavoratore=data
        console.log("Recensioni del lavoratore: " + this.recensioniLavoratore[0].punteggio)
        this.mediaRecensioniRicevute = this.mediaRecensioni();


      })

    }
    else {
      this.recensioneT = {
        idRecensione: -1,
        titolo: "",
        descrizione: "",
        punteggio: 0,
        lavoratore: {
          provinciaLavoro: "",
          ambiti: [],
          notificaEmail: false,
          punteggio: 0,
          username: '',
          email: '',
          password: '',
          nome: '',
          cognome: '',
          dataNascita: new Date(),
          provincia: '',
          imgProfilo: undefined,
          registrato: false,
          dataRegistrazione: new Date()
        },
        cliente: {
          username: "",
          password: "",
          email: "",
          nome: "",
          cognome: "",
          provincia: "",
          dataNascita: new Date(),
          imgProfilo: undefined,
          registrato: false,
          dataRegistrazione: new Date()
        }
      }
      this.initStarsRecensioni()
    }



  }
  initStarsRecensioni(){
    for (let i = 0; i < this.annunci.length; i++) {
      this.annunci[i].starClicked = false;
    }
  }

  resetStarsRecensioni(ann : Annuncio | undefined){
    for (let i = 0; i < this.annunci.length; i++) {
      if (ann == this.annunci[i]) {
        ann.starClicked = !ann.starClicked;
      }
      else this.annunci[i].starClicked = false;
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


  mediaRecensioni(){
    let somma = 0;
    for (let i = 0; i < this.recensioniLavoratore.length; i++) {
      somma += this.recensioniLavoratore[i].punteggio;
    }
    return (somma / this.recensioniLavoratore.length).toFixed(1);

  }

  getRange(punteggio: number): number[] {
    if (punteggio < 0) {
      console.log("Primo if: " + Array(5).fill(0))
      return Array(5).fill(0)
    }
    console.log(Array(punteggio).fill(0))
    return Array(punteggio).fill(0)
  }

  /*
  isStarsClicked(){

    return this.starsClicked
  }
*/

  setStarsClicked(voto : number, annuncioSel : Annuncio | undefined){

    console.log(voto);
    this.resetStarsRecensioni(annuncioSel);
    this.recensioneT.punteggio = annuncioSel?.starClicked ? voto : 0;
    this.recensioneT.cliente = annuncioSel?.cliente;

    this.annunciService.getLavoratoreAnnuncio(annuncioSel?.id!).subscribe(data => {
      this.recensioneT.lavoratore = data;
    })

    console.log("Ann id: " +this.recensioneT.lavoratore?.username)

    console.log("recensioneT.punteggio: " + this.recensioneT.punteggio)
    console.log("recensioneT.lavora: " + annuncioSel?.proposta.descrizione)
  }

  insertDescrizione(descrizione: Event, annuncioSel: Annuncio): void {
    const target = descrizione.target as HTMLTextAreaElement;
    const valoreTextArea = target.value;
    console.log("Titolo: " + annuncioSel?.titolo);
    this.recensioneT.titolo = annuncioSel?.titolo;
    this.recensioneT.descrizione = valoreTextArea;
    console.log("Descrizione rec: " + valoreTextArea); // Stampa il valore della textarea
    // Puoi fare qualcosa con il valore ottenuto, ad esempio assegnarlo a una variabile o elaborarlo in qualche modo
  }
  getEntita(){


    return this.entita
  }


  inviaRecensione() {
      console.log("entra")
      const  recensione: Recensione = {
        idRecensione: this.recensioneT.idRecensione,
        titolo: this.recensioneT.titolo,
        descrizione: this.recensioneT.descrizione,
        punteggio: this.recensioneT.punteggio,
        lavoratore: this.recensioneT.lavoratore!,
        cliente: this.recensioneT.cliente!
      }
      console.log("recensione è: " + recensione.titolo)

      console.log("recensione è: " + recensione.descrizione)
      console.log("usr lavoratore: " + recensione.lavoratore.username)

      this.backEndService.insertRecensione(recensione).subscribe(
        (response) => {
          console.log("response è: " + response)

        },
        (error) => {
          console.log("error è: " + error)

        });


  }

}
