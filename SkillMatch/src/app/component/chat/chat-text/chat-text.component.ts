import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-chat-text',
  templateUrl: './chat-text.component.html',
  styleUrl: './chat-text.component.css'
})
export class ChatTextComponent implements OnInit, OnChanges{


  @Input() chat: any;
  
  constructor(){}
  ngOnChanges(changes: SimpleChanges): void {
    console.log(this.chat)
  }


  ngOnInit(): void {
    console.log(this.chat +"init")
  }
  
}
