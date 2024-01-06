import { AfterContentInit, AfterViewChecked, Component, EventEmitter, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import { ActivatedRoute } from '@angular/router';
import { OwlOptions } from 'ngx-owl-carousel-o';

@Component({
  selector: 'app-cards-vetrina',
  templateUrl: './cards-vetrina.component.html',
  styleUrl: './cards-vetrina.component.css'
})
export class CardsVetrinaComponent implements OnInit, AfterViewChecked{

  annunci: any;
  ambito: string
  caroselloOpzioni: OwlOptions = {
    items: 4,
    loop: true,
    nav: true,
    dots: false,
    autoplay: true,
    autoplayTimeout: 2000,
    autoplayHoverPause: true,
  };
  constructor(private route : ActivatedRoute, private servizioAnnunci: ServizioAnnunciService){}

  
  ngAfterViewChecked(): void {
    if(this.route.snapshot.paramMap.get('ambito')){

      
      this.ambito=this.route.snapshot.paramMap.get('ambito')!;
      this.annunci=this.servizioAnnunci.getAnnunciByAmbito(this.ambito);
      
    }
    else {
      this.annunci=this.servizioAnnunci.getAnnunci()
      
    }
  }

  ngOnInit(): void {
    
  }

}
