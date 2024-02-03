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
  annunci:Annuncio[]
  recensioneT : RecensioneType
  recensioneForm: FormGroup
  propostaAccettata: any
  ArrowDown=faArrowDown
  starsClicked: boolean = false
  entita: string | null

  annuncioSelezionato : Annuncio | undefined

  nuovaRecensione:FormGroup


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
/*
    this.recensioneForm = new FormGroup({
      idRecensione: new FormControl(null,Validators.required),
      titolo: new FormControl(null,Validators.required),
      descrizione: new FormControl(null,Validators.required),
      punteggio: new FormControl(null,Validators.required),
      lavoratore: new FormControl(null,Validators.required),
      cliente: new FormControl(null,Validators.required),
    })*/


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

  isStarsClicked(){

    return this.starsClicked
  }


  setStarsClicked(voto : number, annuncioSel : Annuncio | undefined){

    console.log(voto);
    this.starsClicked = !this.starsClicked;
    this.recensioneT.punteggio = this.starsClicked ? voto : 0;
    this.recensioneT.cliente = annuncioSel?.cliente;
    this.annunciService.getLavoratoreAnnuncio(annuncioSel?.id!).subscribe(data => {
      this.recensioneT.lavoratore = data;
    })

    console.log("Ann id: " +this.recensioneT.lavoratore?.username)

    console.log("recensioneT.punteggio: " + this.recensioneT.punteggio)
    console.log("recensioneT.lavora: " + annuncioSel?.proposta.descrizione)
  }

  insertDescrizione(descrizione: Event): void {

      const target = descrizione.target as HTMLTextAreaElement;
      const valoreTextArea = target.value;
      this.recensioneT.titolo = valoreTextArea[0]; // titolo provvisorio
      this.recensioneT.descrizione = valoreTextArea;
      console.log("Descrizione rec: " + valoreTextArea); // Stampa il valore della textarea
      // Puoi fare qualcosa con il valore ottenuto, ad esempio assegnarlo a una variabile o elaborarlo in qualche modo
  }
  getEntita(){
    return this.entita
  }


  inviaRecensione() {

    if (this.starsClicked){
      console.log("entra")

      const  recensione: Recensione = {
        idRecensione: this.recensioneT.idRecensione,
        titolo: this.recensioneT.titolo,
        descrizione: this.recensioneT.descrizione,
        punteggio: this.recensioneT.punteggio,
        lavoratore: this.recensioneT.lavoratore!,
        cliente: this.recensioneT.cliente!
      }

      /*

      let usrLogged = localStorage.getItem("utente");
      const cliente =  JSON.parse(usrLogged!);
      recensione.cliente.username = cliente.username*/


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
  /*
  inserisciAnnuncio(): void{

    if(this.nuovoAnnuncioForm.valid){
      const ambito: Ambito = {
        id: this.nuovoAnnuncioForm.value.ambito.id,
        nome: this.nuovoAnnuncioForm.value.ambito.nome
      };
      const annuncio: Annuncio = this.nuovoAnnuncioForm.value


      annuncio.provinciaAnnuncio = (annuncio.provinciaAnnuncio as unknown as Province).nome;

      annuncio.ambito = ambito

      console.log("annuncio.provinciaAnnuncio"+annuncio.provinciaAnnuncio)


      this.backEndService.insertAnnuncio(annuncio, this.image).subscribe(
        (response) => {
          console.log("response è: " + response)

        },
        (error) => {
          console.log("error è: " + error)

        });




    }*/
}
