import {Component, Input, signal} from '@angular/core'


import { DatiRegistrazioneService } from "../../../service/DatiRegistrazioneService";
import Swal  from "sweetalert2";

import {Router } from "@angular/router";
import {AccediComponent} from "../accedi.component";
import {BackEndService} from "../../../service/BackEndService";
import {error} from "@angular/compiler-cli/src/transformers/util";
import {FormGroup} from "@angular/forms";
@Component({
  selector: 'app-riepilogo-dati',
  templateUrl: './riepilogo-dati.component.html',
  styleUrls: ['./riepilogo-dati.component.css','../accedi.component.css']
})
export class RiepilogoDatiComponent {
  /*username: string
  email: string
  password: string
  nome: string
  cognome: string
  indirizzo: string
  dataDiNascita: Date
  ambito: string
  zonaDiCompetenza: string*/

  @Input('utente') utente:any

  constructor(private datiRegistrazione: DatiRegistrazioneService, private router: Router, private accediComponent : AccediComponent, private backEndService: BackEndService) {
  }

  ngOnInit(): void {
    /*this.username = this.datiRegistrazione.getUsername();
    this.email = this.datiRegistrazione.getEmail();
    this.password = this.datiRegistrazione.getPassword();

    this.nome = this.datiRegistrazione.getNome();
    this.cognome = this.datiRegistrazione.getCognome();

    this.dataDiNascita = this.datiRegistrazione.getDataDiNascita();
    this.ambito = this.datiRegistrazione.getAmbiti();
    this.zonaDiCompetenza = this.datiRegistrazione.getZonaDiCompetenza();*/
  }


  public goToAccount() {
    let data = new Date();
    this.datiRegistrazione.setDataRegistrazione(data);
    this.backEndService.completeSignUp(this.datiRegistrazione).subscribe(data => {
      if (data) {
        this.router.navigate(['/accedi/account']);
      } else {
        Swal.fire("Errore nella registrazione")
      }
    })
  }

  public goToAccess() {
    this.accediComponent.backToGeneralita()
  }
}

