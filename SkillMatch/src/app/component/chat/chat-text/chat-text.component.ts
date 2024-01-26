import { AfterViewInit, Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { ServizioAnnunciService } from '../../../service/servizio-annunci.service';
import Swal from 'sweetalert2';
import {BackEndService} from "../../../service/BackEndService";
import {ChatService} from "../../../service/ChatService";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Messaggio} from "../../../model/Messaggio";

@Component({
  selector: 'app-chat-text',
  templateUrl: './chat-text.component.html',
  styleUrl: './chat-text.component.css'
})
export class ChatTextComponent implements OnInit, OnChanges,AfterViewInit{


  @Input() primoCaricamento:boolean;
  @Input() chat: any;
  @Output() tornaDietro = new EventEmitter<boolean>()

  messaggi : any;
  DestinatarioCard:any;
  textForm:FormGroup;

  constructor( private service: ServizioAnnunciService, private chatService : ChatService){}
  ngAfterViewInit(): void {
    console.log("sass")

  }

  ngOnInit(): void {

    this.textForm = new FormGroup({
      messaggio: new FormControl(null, Validators.required),

    })
  }
  ngOnChanges(changes: SimpleChanges): void {

    console.log(this.primoCaricamento)

    if(this.primoCaricamento && this.chat){
      this.messaggi=this.service.getMessaggiByChat();
      /*this.primoCaricamento=false;*/
      this.DestinatarioCard=this.service.getDestinatarioCard(this.chat)
    }


  }


  inviaMessaggio(){


    let messaggio : Messaggio={

      contenuto: this.textForm.get("messaggio")?.value,
      data: new Date(),
      isLavoratore: localStorage.getItem("scelta") != "cliente",
      letto: false

    }
    this.chatService.inviaMessaggio(messaggio)

}
  back(){
    this.tornaDietro.emit();
  }




  getProposta(){
    if(this.chat)

      return this.service.getPropostaVera(this.chat);

    return
  }

}
