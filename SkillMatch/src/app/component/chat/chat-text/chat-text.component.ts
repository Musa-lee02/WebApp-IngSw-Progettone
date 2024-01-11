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
  lavoratoreCard:any;
  
  constructor( private service: ServizioAnnunciService){}
  ngAfterViewInit(): void {
    console.log("sass")
    
  }
  ngOnChanges(changes: SimpleChanges): void {
    
    console.log(this.primoCaricamento)
   
    if(this.primoCaricamento && this.chat){
      this.messaggi=this.service.getMessaggiByChat();
      /*this.primoCaricamento=false;*/
      this.lavoratoreCard=this.service.getLavoratoreCard(this.chat)
    }
    

  }

  alertConferma(){
    Swal.fire({
      title: "Sei sicuro di voler accettare la proposta del lavoratore",
      showCancelButton: true,
      confirmButtonText: "Accetta",
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        Swal.fire("Proposta accettata con successo");
      } 
    });
  }

  alertRifiuta(){
    Swal.fire({
      title: "Sei sicuro di voler rifiutare la proposta del lavoratore",
      showCancelButton: true,
      confirmButtonText: "Si desidero rifiutare",
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        Swal.fire("Proposta rifiutata con successo");
      } 
    });
  }
  back(){
    this.tornaDietro.emit();
  }


  ngOnInit(): void {
  
  }
  
}
