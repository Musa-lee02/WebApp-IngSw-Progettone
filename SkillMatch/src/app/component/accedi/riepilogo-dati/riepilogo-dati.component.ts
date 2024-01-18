import {Component, signal} from '@angular/core'


import { DatiRegistrazioneService } from "../../../service/DatiRegistrazioneService";

import {Router } from "@angular/router";
import {AccediComponent} from "../accedi.component";
@Component({
  selector: 'app-riepilogo-dati',
  templateUrl: './riepilogo-dati.component.html',
  styleUrls: ['./riepilogo-dati.component.css','../accedi.component.css']
})
export class RiepilogoDatiComponent {
  username: string
  email: string
  password: string
  nome: string
  cognome: string
  indirizzo: string
  dataDiNascita: Date
  ambito: string
  zonaDiCompetenza: string

  constructor(private datiRegistrazione: DatiRegistrazioneService, private router: Router, private accediComponent : AccediComponent) {
  }

  ngOnInit(): void {
    this.username = this.datiRegistrazione.getUsername();
    this.email = this.datiRegistrazione.getEmail();
    this.password = this.datiRegistrazione.getPassword();

    this.nome = this.datiRegistrazione.getNome();
    this.cognome = this.datiRegistrazione.getCognome();
    //this.indirizzo = this.datiRegistrazione.getIndirizzo();
    this.dataDiNascita = this.datiRegistrazione.getDataDiNascita();
    this.ambito = this.datiRegistrazione.getAmbiti();
    this.zonaDiCompetenza = this.datiRegistrazione.getZonaDiCompetenza();
  }


  public goToAccount() {
    const formData = new FormData();
    formData.append('username', this.username);
    formData.append('email', this.email);
    formData.append('password', this.password);
    formData.append('nome', this.nome);
    formData.append('cognome', this.cognome);
    formData.append('dataDiNascita', this.dataDiNascita.toString());
    formData.append('ambito', this.ambito);
    formData.append('zonaDiCompetenza', this.zonaDiCompetenza);
    //formData.append('indirizzo', this.indirizzo);


  }

  public goToAccess() {
    this.accediComponent.backToGeneralita()
  }
}

