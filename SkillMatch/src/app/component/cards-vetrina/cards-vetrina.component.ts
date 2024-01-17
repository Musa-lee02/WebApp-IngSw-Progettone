import { AfterContentChecked, AfterContentInit, AfterViewChecked, Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import { ActivatedRoute } from '@angular/router';
import { faStar } from '@fortawesome/free-solid-svg-icons';
import { library } from '@fortawesome/fontawesome-svg-core';

library.add(faStar);

@Component({
  selector: 'app-cards-vetrina',
  templateUrl: './cards-vetrina.component.html',
  styleUrl: './cards-vetrina.component.css'
})
export class CardsVetrinaComponent implements OnInit, AfterViewChecked, AfterContentChecked{

  @Input() annunci : any;
 
  ambito: string;
  starImg: string = "../../assets/star.jpg";
  
 
  constructor(private route : ActivatedRoute, private servizioAnnunci: ServizioAnnunciService){}
  ngAfterContentChecked(): void {
   
    
  }
  getSizeAnnunci(){
    return this.annunci.length
  }


  ngAfterViewChecked(): void {

  }

  ngOnInit(): void {
  
  }

  initAnnunci(){
    if(this.route.snapshot.paramMap.get('ambito')){

      this.ambito=this.route.snapshot.paramMap.get('ambito')!;
      this.annunci=this.servizioAnnunci.getAnnunciByAmbitoEZona();
      
    }
    else {
      this.annunci=this.servizioAnnunci.getAnnunci()
      
    }
  }

}
