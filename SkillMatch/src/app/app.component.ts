import { Component, OnInit } from '@angular/core';
import { ServizioAnnunciService } from './servizio-annunci.service';
import { Router } from '@angular/router';
import { faArrowDown } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'

})
export class AppComponent{

  arrowIcon= faArrowDown
  title = 'SkillMatch';
  sizeAnnunci:number=0;
  isHome:boolean=false;
  linkHome:String="http://localhost:4200/Home"
  
  getRange(sizeAnnunci: number){
    return this.sizeAnnunci
  }

}
