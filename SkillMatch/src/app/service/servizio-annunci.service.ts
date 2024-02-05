import { Injectable, OnInit } from '@angular/core';
import { CardsVetrinaComponent } from '../component/cards-vetrina/cards-vetrina.component';
import {Annuncio} from "../model/Annuncio";



type Annunci ={

  username:string
  img: string
  descrizione: string
  ambito: string
  titolo: string
  id:string
  stato: string
  zona:string
  dataDiScadenza: Date

}

type Proposte={
  username:string
  img: string
  descrizione:string
  id:string
  stato: string
}

type Proposta={

  descrizione:string
  dataScadenza:string
  stato:string
  prezzo:number
  idAnnuncio:string
  usernameLavoratore:string

}


type Messaggio={

  contenuto:string
  data:string
  idChat: Chat
  inviato:boolean

}

type Chat={

  idAnnuncio: string
  interlocutore1: string
  interlocutore2:string

}

type Lavoratore={
  username:string
  img:string

}
@Injectable({
  providedIn: 'root'
})

export class ServizioAnnunciService implements OnInit {

  selectedAmbito: string;
  selectedZona: string;
  annunci: Annuncio[]
  buttonSearchClickedBool: boolean = false;
  doingAccesso: boolean;
  private lavoratoreBool: boolean = false;
  private autenticato: boolean = false;
  private skipAutentication: boolean;
  currentImage: string = "../../../assets/default.jpg";

  url: string;
  chatAttuale: Chat;


  usernameUtente = "utente1"

  ngOnInit(): void {
  }
  setAnnunciEsplora(annunci : Annuncio[]){
    this.annunci=annunci
  }
  getAnnunciEsplora(){
    return this.annunci
  }
}
