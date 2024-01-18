import { AfterViewInit, Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { ServizioAnnunciService } from '../../../service/servizio-annunci.service';
import Swal from 'sweetalert2';

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
  
  constructor( private service: ServizioAnnunciService){}
  ngAfterViewInit(): void {
    console.log("sass")
    
  }
  ngOnChanges(changes: SimpleChanges): void {
    
    console.log(this.primoCaricamento)
   
    if(this.primoCaricamento && this.chat){
      this.messaggi=this.service.getMessaggiByChat();
      /*this.primoCaricamento=false;*/
      this.DestinatarioCard=this.service.getDestinatarioCard(this.chat)
    }
    

  }

  
  back(){
    this.tornaDietro.emit();
  }


  ngOnInit(): void {
  
  }

  getProposta(){
    if(this.chat)

     return this.service.getPropostaVera(this.chat);

     return
  }
  
}
