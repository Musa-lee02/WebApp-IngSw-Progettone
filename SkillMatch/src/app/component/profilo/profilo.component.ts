import { Component, OnInit } from '@angular/core';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import { faPencil } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-profilo',
  templateUrl: './profilo.component.html',
  styleUrls: ['./profilo.component.css','../../app.component.css']
})
export class ProfiloComponent implements OnInit {

  annunci:any
  pencil=faPencil
  constructor(private service: ServizioAnnunciService){

    this.service.setAutenticato(true);

  }
  ngOnInit(): void {
    this.annunci=this.service.getAnnunci();
    console.log(this.annunci)
  }

  

}
