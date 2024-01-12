import { Component } from '@angular/core'

import { LavoratoreSignUp} from "../../../SignUpLavoratore";

import { DatiRegistrazioneService } from "../../../service/DatiRegistrazioneService";

@Component({
  selector: 'app-riepilogo-dati',
  templateUrl: './riepilogo-dati.component.html',
  styleUrls: ['./riepilogo-dati.component.css','../accedi.component.css']
})
export class RiepilogoDatiComponent {
  username: string
  email: string
  nome: string
  cognome: string
  indirizzo: string
  dataDiNascita: Date
  ambito: string
  zonaDiCompetenza: string

  constructor(private datiRegistrazione: DatiRegistrazioneService) {
  }

  ngOnInit(): void {
    this.username = this.datiRegistrazione.getUsername();
    this.email = this.datiRegistrazione.getEmail();
    this.nome = this.datiRegistrazione.getNome();
    this.cognome = this.datiRegistrazione.getCognome();
    //this.indirizzo = this.datiRegistrazione.getIndirizzo();
    this.dataDiNascita = this.datiRegistrazione.getDataDiNascita();
    this.ambito = this.datiRegistrazione.getAmbiti();
    this.zonaDiCompetenza = this.datiRegistrazione.getZonaDiCompetenza();
  }
}
