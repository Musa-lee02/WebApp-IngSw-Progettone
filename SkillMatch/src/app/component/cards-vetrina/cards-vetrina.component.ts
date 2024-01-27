import { AfterContentChecked, AfterContentInit, AfterViewChecked, Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import { ActivatedRoute } from '@angular/router';
import { faStar } from '@fortawesome/free-solid-svg-icons';
import { library } from '@fortawesome/fontawesome-svg-core';
import { BackEndService } from '../../service/BackEndService';

library.add(faStar);

@Component({
  selector: 'app-cards-vetrina',
  templateUrl: './cards-vetrina.component.html',
  styleUrl: './cards-vetrina.component.css'
})
export class CardsVetrinaComponent implements OnInit, AfterViewChecked, AfterContentChecked{

  @Input('annunci') annunci : any;
 
  ambito: string;
  starImg: string = "../../assets/star.jpg";
  
 
  constructor(private route : ActivatedRoute, private servizioAnnunci: ServizioAnnunciService, private backEndService: BackEndService){}
  ngAfterContentChecked(): void {
   
    
  }
  getSizeAnnunci(){
    return this.annunci.length
  }


  ngAfterViewChecked(): void {
    console.log(this.annunci)
  }

  ngOnInit(): void {
  
  }

  initAnnunci(){
    if(this.route.snapshot.paramMap.get('ambito')){

      this.ambito=this.route.snapshot.paramMap.get('ambito')!;
      this.annunci=this.servizioAnnunci.getAnnunciByAmbitoEZona(); //TODO
      
    }
    else {
      this.backEndService.getAllAnnunci().subscribe(
        response => {
          this.annunci = response
      }, (error) => {
          console.log("errore. da modificare(?)")
      });
      
    }
  }

}
