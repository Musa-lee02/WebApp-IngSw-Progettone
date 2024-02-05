import { Component, OnInit } from '@angular/core';
import { ServizioAnnunciService } from '../../../service/servizio-annunci.service';
import { faArrowDown } from '@fortawesome/free-solid-svg-icons';
import {ActivatedRoute} from "@angular/router";
import {BackEndService} from "../../../service/BackEndService";
import {AnnuncioService} from "../../../service/AnnuncioService";
import {Annuncio} from "../../../model/Annuncio";
import {FormGroup} from "@angular/forms";

import  {Recensione} from "../../../model/Recensione";
import {Ambito} from "../../../model/Ambito";
import {Province} from "../../../model/Province";


type RecensioneType = {
  id: number
  titolo: string
  descrizione: string
  voto: number
  usrCliente: string
  usrLavoratore: string


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
  recensione : RecensioneType
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

    this.recensione = {
      id: 0,
      titolo: "",
      descrizione: "",
      voto: 0,
      usrCliente: "",
      usrLavoratore: ""
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

  setStarsClicked(voto : number, annuncioSel : Annuncio | undefined){

    console.log(voto);
    this.starsClicked = !this.starsClicked;
    this.recensione.voto = this.starsClicked ? voto : 0;
    this.annuncioSelezionato = annuncioSel;
    console.log("Annuncio sel: " + this.annuncioSelezionato?.cliente.username)
    console.log(this.recensione.voto);
    console.log(this.propostaAccettata.username);


  }
  isStarsClicked(){

    return this.starsClicked
  }

  insertDescrizione(descrizione: Event): void {

      const target = descrizione.target as HTMLTextAreaElement;
      const valoreTextArea = target.value;
      this.recensione.descrizione = valoreTextArea;
      console.log("Descrizione rec: " + valoreTextArea); // Stampa il valore della textarea
      // Puoi fare qualcosa con il valore ottenuto, ad esempio assegnarlo a una variabile o elaborarlo in qualche modo
  }
  getEntita(){


    return this.entita
  }


  inviaRecensione() {

    if (this.starsClicked){
      console.log("entra")

      const  recensione: Recensione = this.recensione

      let usrLogged = localStorage.getItem("utente");
      const cliente =  JSON.parse(usrLogged!);
      recensione.usrCliente = cliente.username

      const usrLavoratore = this.annuncioSelezionato?.proposta.lavoratore.username
      recensione.usrLavoratore = usrLavoratore!
      console.log("recensione.usrCliente: " + recensione.usrCliente)
      console.log("recensione.usrLavoratore: " + recensione.usrLavoratore)


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
