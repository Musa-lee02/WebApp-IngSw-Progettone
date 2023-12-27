import { Component, OnInit } from '@angular/core';
import { ServizioAnnunciService } from './servizio-annunci.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'SkillMatch';
  sizeAnnunci:number=0;

  constructor(private servizioAnnunci: ServizioAnnunciService){}

  ngOnInit(): void {
    this.sizeAnnunci=this.servizioAnnunci.annunciGetSize()
  }
  getRange(sizeAnnunci: number){
    return this.sizeAnnunci
  }

}
