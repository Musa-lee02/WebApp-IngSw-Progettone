import { Component } from '@angular/core';
import { ServizioAnnunciService } from '../../servizio-annunci.service';
import { faSearch } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  icon= faSearch;
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

