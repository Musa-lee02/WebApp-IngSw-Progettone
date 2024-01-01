import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ServizioAnnunciService } from '../servizio-annunci.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-cards-vetrina',
  templateUrl: './cards-vetrina.component.html',
  styleUrl: './cards-vetrina.component.css'
})
export class CardsVetrinaComponent implements OnInit{

  annunci: any;
  ambito: string
  constructor(private route : ActivatedRoute, private servizioAnnunci: ServizioAnnunciService){}

  ngOnInit(): void {
    
    if(this.route.snapshot.paramMap.get('ambito')){

      this.ambito=this.route.snapshot.paramMap.get('ambito')!;
      this.annunci=this.servizioAnnunci.getAnnunciByAmbito(this.ambito);
    }
    else{
      this.annunci=this.servizioAnnunci.getAnnunci()
    }
    
  }

}
