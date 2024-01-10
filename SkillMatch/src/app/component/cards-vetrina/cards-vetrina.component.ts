import { AfterContentChecked, AfterContentInit, AfterViewChecked, Component, EventEmitter, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-cards-vetrina',
  templateUrl: './cards-vetrina.component.html',
  styleUrl: './cards-vetrina.component.css'
})
export class CardsVetrinaComponent implements OnInit, AfterViewChecked, AfterContentChecked{

  annunci: any;
  ambito: string

  constructor(private route : ActivatedRoute, private servizioAnnunci: ServizioAnnunciService){}
  ngAfterContentChecked(): void {
    if(this.route.snapshot.paramMap.get('ambito')){

      
      this.ambito=this.route.snapshot.paramMap.get('ambito')!;
      this.annunci=this.servizioAnnunci.getAnnunciByAmbito(this.ambito);
      
    }
    else {
      this.annunci=this.servizioAnnunci.getAnnunci()
      
    }
  }

  
  ngAfterViewChecked(): void {
 
  }

  ngOnInit(): void {
    
  }

}
