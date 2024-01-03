import { Component } from '@angular/core';
import { ServizioAnnunciService } from '../../servizio-annunci.service';

@Component({
  selector: 'app-effettua-annuncio',
  templateUrl: './effettua-annuncio.component.html',
  styleUrls: ['./effettua-annuncio.component.css','../profilo/profilo.component.css']
})
export class EffettuaAnnuncioComponent {

  constructor(private service: ServizioAnnunciService){

  }

  isAutenticato(){
    return this.service.isAutenticato()
  }
}
