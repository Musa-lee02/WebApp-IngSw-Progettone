import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit{
  
  @Input() contatto: any;
  
  constructor(){}


  ngOnInit(): void {
    console.log(this.contatto)
  }

 
}
