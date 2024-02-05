import { AfterContentChecked, AfterViewChecked, AfterViewInit, Component, ElementRef, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { ServizioAnnunciService } from './service/servizio-annunci.service';
import { ActivatedRoute, Router } from '@angular/router';
import { faArrowDown } from '@fortawesome/free-solid-svg-icons';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'

})
export class AppComponent implements OnInit, OnChanges, AfterContentChecked, AfterViewChecked{


  constructor(private router: Router, private service: ServizioAnnunciService){}
  ngAfterContentChecked(): void {

    if(this.router.url==="/Home"){
      this.isHome=true

    }
    else{
      this.isHome=false
    }


  }
  ngOnChanges(changes: SimpleChanges): void {

  }
  ngOnInit(): void {

  }

  arrowIcon= faArrowDown
  title = 'SkillMatch';
  sizeAnnunci:number=0;
  isHome:boolean;

  linkHome:String="http://localhost:4200/Home"


  ngAfterViewChecked(): void {



  }





  getRange(sizeAnnunci: number){
    return this.sizeAnnunci
  }




}
