import {
  AfterContentChecked,
  AfterViewInit,
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnDestroy,
  OnInit,
  Output,
  SimpleChanges
} from '@angular/core';
import { ServizioAnnunciService } from '../../../service/servizio-annunci.service';
import Swal from 'sweetalert2';
import {BackEndService} from "../../../service/BackEndService";
import {ChatService} from "../../../service/ChatService";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Messaggio} from "../../../model/Messaggio";
import {Lavoratore} from "../../../model/Lavoratore";
import {Chat} from "../../../model/Chat";
import {Proposta} from "../../../model/Proposta";
import {Router} from "@angular/router";
import {Cliente} from "../../../model/Cliente";



@Component({
  selector: 'app-chat-text',
  templateUrl: './chat-text.component.html',
  styleUrl: './chat-text.component.css'
})
export class ChatTextComponent implements OnInit, OnChanges, OnDestroy, AfterContentChecked{


  @Input() primoCaricamento:boolean;
  @Input() chat: Chat;
  @Output() tornaDietro = new EventEmitter<boolean>()
  @Input()  proposta : Proposta

  interval: any
  messaggi : any;
  DestinatarioCard: Lavoratore | Cliente;
  textForm:FormGroup;

  propostaCaricata: boolean=false

  constructor( private service: ServizioAnnunciService, private chatService : ChatService, private router: Router){}


  ngOnInit(): void {

      this.interval = setInterval(() => {
        this.getMessaggi();
      }, 500);


    this.textForm = new FormGroup({
      messaggio: new FormControl(null, Validators.required),

    })


  }

  ngOnDestroy(): void {
    clearInterval(this.interval)
  }
  ngOnChanges(changes: SimpleChanges): void {


    if(this.chat){

      if(localStorage.getItem("scelta")==="cliente") {
        this.DestinatarioCard=this.chat.lavoratore
      }else{
        this.DestinatarioCard=this.chat.cliente
      }

    }
  }
  ngAfterContentChecked(): void {

  }

  getMessaggi(){

    if(this.chat) {
      this.chatService.getMessaggiByChat(this.chat).subscribe(data => {
        this.messaggi = data
        /*console.log(this.messaggi)*/
      })
    }

  }

  inviaMessaggio(){


    let messaggio : Messaggio={

      contenuto: this.textForm.get("messaggio")?.value,
      data: new Date(),
      isLavoratore: localStorage.getItem("scelta") === "lavoratore",
      inviato: localStorage.getItem("scelta") === "lavoratore",
      chat: this.chat

    }

    console.log(messaggio.isLavoratore)
    this.chatService.inviaMessaggio(messaggio)

}
  back(){
    this.tornaDietro.emit();
  }

  isInviato(messaggio: Messaggio){


    if(messaggio.inviato && localStorage.getItem("scelta")==="lavoratore"){

      return true
    }
    else if(!messaggio.inviato && localStorage.getItem("scelta")==="cliente"){

      return true
    }
    else{
      return false
    }

  }


  setProposta(propostaParametri : any){

    let proposta : Proposta={
      annuncioRelativo: this.chat.annuncio,
      dataLavoro: propostaParametri.data,
      descrizione: propostaParametri.descrizione,
      lavoratore: this.chat.lavoratore,
      prezzoLavoro: propostaParametri.prezzo,
      stato: "inCorso",
      statoLavoro: undefined,

    }
    console.log(proposta)
    this.chatService.setProposta(proposta).subscribe(response =>{

      if(response){
        console.log("true")

      }
      else{
        console.log("false")
      }
      }
    )

  }





}
