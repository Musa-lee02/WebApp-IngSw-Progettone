import { Component } from '@angular/core';
import { ServizioAnnunciService } from '../../servizio-annunci.service';

@Component({
  selector: 'app-profilo',
  templateUrl: './profilo.component.html',
  styleUrls: ['./profilo.component.css','../../app.component.css']
})
export class ProfiloComponent {

  constructor(private service: ServizioAnnunciService){

    service.setAutenticato()

  }

}
