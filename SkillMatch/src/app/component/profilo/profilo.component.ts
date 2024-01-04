import { Component } from '@angular/core';
import { ServizioAnnunciService } from '../../servizio-annunci.service';
import { faPencil } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-profilo',
  templateUrl: './profilo.component.html',
  styleUrls: ['./profilo.component.css','../../app.component.css']
})
export class ProfiloComponent {

  pencil=faPencil
  constructor(private service: ServizioAnnunciService){

    

  }

}
