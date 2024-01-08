import { AfterViewInit, Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ServizioAnnunciService } from '../../../service/servizio-annunci.service';

@Component({
  selector: 'app-chat-text',
  templateUrl: './chat-text.component.html',
  styleUrl: './chat-text.component.css'
})
export class ChatTextComponent implements OnInit, OnChanges,AfterViewInit{



  @Input() primoCaricamento:boolean;
  @Input() chat: any;
  
  messaggi : any;
  lavoratoreCard:any;
  
  constructor( private service: ServizioAnnunciService){}
  ngAfterViewInit(): void {
    console.log("sass")
    
  }
  ngOnChanges(changes: SimpleChanges): void {
    
    console.log(this.primoCaricamento)
    console.log(this.chat)
    if(this.primoCaricamento && this.chat){
      this.messaggi=this.service.getMessaggiByChat();
      /*this.primoCaricamento=false;*/
      this.lavoratoreCard=this.service.getLavoratoreCard(this.chat)
    }
    

  }


  ngOnInit(): void {
  
  }
  
}
