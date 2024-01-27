import {
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


@Component({
  selector: 'app-chat-text',
  templateUrl: './chat-text.component.html',
  styleUrl: './chat-text.component.css'
})
export class ChatTextComponent implements OnInit, OnChanges,AfterViewInit, OnDestroy{


  @Input() primoCaricamento:boolean;
  @Input() chat: any;
  @Output() tornaDietro = new EventEmitter<boolean>()


  interval: any
  messaggi : any;
  DestinatarioCard:any;
  textForm:FormGroup;
  proposta : Proposta
  constructor( private service: ServizioAnnunciService, private chatService : ChatService){}
  ngAfterViewInit(): void {
    console.log("sass")

  }


  ngOnInit(): void {

    this.interval=setInterval(() => {
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

  getMessaggi(){

    this.chatService.getMessaggiByChat(this.chat).subscribe(data =>{
      this.messaggi=data
    })

  }

  inviaMessaggio(){


    let messaggio : Messaggio={

      contenuto: this.textForm.get("messaggio")?.value,
      data: new Date(),
      isLavoratore: localStorage.getItem("scelta") === "lavoratore",
      letto: false,
      chat: this.chat

    }

    this.chatService.inviaMessaggio(messaggio)



}
  back(){
    this.tornaDietro.emit();
  }

  isInviato(messaggio: Messaggio){


    if(messaggio.isLavoratore && localStorage.getItem("scelta")==="lavoratore"){

      return true
    }
    else if(!messaggio.isLavoratore && localStorage.getItem("scelta")==="cliente"){

      return true
    }
    else{
      return false
    }

  }


  /*getProposta(): Proposta{
    if(this.chat)

      this.chatService.getProposta(this.chat).subscribe(data =>{

        this.proposta=data
      });

    return this.proposta
  }*/

  setProposta(){


  }



}
