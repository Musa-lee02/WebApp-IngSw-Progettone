import { Component, ElementRef, EventEmitter, Output, ViewChild } from '@angular/core';

@Component({
  selector: 'app-scelta-utente',
  templateUrl: './scelta-utente.component.html',
  styleUrl: './scelta-utente.component.css'
})
export class SceltaUtenteComponent {

  @ViewChild ("containerScelta") containerScelta: ElementRef 
  @ViewChild ("sceltaCliente") sceltaCliente: ElementRef
  @ViewChild ("sceltaLavoratore") sceltaLavoratore: ElementRef
  @Output() mandaSceltaUtente= new EventEmitter()


  mandaScelta(scelta: string){
    
    if(scelta==='cliente'){
      this.mandaSceltaUtente.emit("cliente")
      
    }
    else
      this.mandaSceltaUtente.emit('lavoratore')
    
      
  }
}
    
  

