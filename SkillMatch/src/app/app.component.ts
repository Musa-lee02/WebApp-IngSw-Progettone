import { AfterViewChecked, AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ServizioAnnunciService } from './servizio-annunci.service';
import { ActivatedRoute, Router } from '@angular/router';
import { faArrowDown } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'

})
export class AppComponent implements AfterViewChecked{


  constructor(private router: Router, private service: ServizioAnnunciService){}
  
  arrowIcon= faArrowDown
  title = 'SkillMatch';
  sizeAnnunci:number=0;
  isHome:boolean=false;
  linkHome:String="http://localhost:4200/Home"

  
  ngAfterViewChecked(): void {
    
   
    if(this.router.url==="/Home"){
      this.isHome=false
    }
    else{
      this.isHome=true
    }
  }

  getRange(sizeAnnunci: number){
    return this.sizeAnnunci
  }

  setLavoratore(bool: boolean){
   
    this.service.setlavoratoreBool(bool);

  }


}
