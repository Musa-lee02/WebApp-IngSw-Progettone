import { Component } from '@angular/core';
import { ServizioAnnunciService } from '../../servizio-annunci.service';

@Component({
  selector: 'app-effettua-annuncio',
  templateUrl: './effettua-annuncio.component.html',
  styleUrl: './effettua-annuncio.component.css'
})
export class EffettuaAnnuncioComponent {

  constructor(private service: ServizioAnnunciService){

  }

  isAutenticato(){
    return this.service.isAutenticato()
  }
}
