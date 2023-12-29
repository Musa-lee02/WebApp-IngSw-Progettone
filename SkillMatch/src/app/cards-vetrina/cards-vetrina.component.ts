import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ServizioAnnunciService } from '../servizio-annunci.service';

@Component({
  selector: 'app-cards-vetrina',
  templateUrl: './cards-vetrina.component.html',
  styleUrl: './cards-vetrina.component.css'
})
export class CardsVetrinaComponent implements OnInit{

  annunci: any;
  constructor(private servizioAnnunci: ServizioAnnunciService){}

  ngOnInit(): void {
    this.annunci=this.servizioAnnunci.annunciGet()
  }

}
