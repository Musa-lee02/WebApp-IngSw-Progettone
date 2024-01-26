import {AfterContentChecked, AfterViewChecked, Component, Input, signal} from '@angular/core'



import Swal  from "sweetalert2";

import {Router } from "@angular/router";
import {AccediComponent} from "../accedi.component";
import {BackEndService} from "../../../service/BackEndService";
import {error} from "@angular/compiler-cli/src/transformers/util";
import {Utente, UtenteCredenziali} from "../../../model/Utente";
import {Lavoratore} from "../../../model/Lavoratore";
import {withInterceptorsFromDi} from "@angular/common/http";
import {Ambito} from "../../../model/Ambito";
import {Cliente} from "../../../model/Cliente";
import {LavoratoreFieldService} from "../../../service/LavoratoreFieldService";
@Component({
  selector: 'app-riepilogo-dati',
  templateUrl: './riepilogo-dati.component.html',
  styleUrls: ['./riepilogo-dati.component.css','../accedi.component.css']
})
export class RiepilogoDatiComponent implements AfterContentChecked{
  @Input("utente") utente : Utente
  @Input("scelta") scelta : string


  constructor(private router: Router, private accediComponent : AccediComponent, private backEndService: BackEndService, private lavoratoreService : LavoratoreFieldService) {
  }

  ngOnInit(): void {

  }

  public getAmbiti() : string[]{
   return this.lavoratoreService.getAmbiti(this.utente)

  }

  public getZona() : string{
    return this.lavoratoreService.getZona(this.utente)
  }


  public goToAccount() {

    console.log("UTENTE: " +this.utente)

    this.backEndService.completeSignUp(this.utente, this.scelta).subscribe(response => {
      console.log("response is :" + response)
      if (response) {
        const utenteCredenziali : UtenteCredenziali ={
          password: this.utente.password,
          username: this.utente.username
        }
        if (this.scelta === "cliente") {

          this.backEndService.loginCliente(utenteCredenziali)

        }else {

          console.log(utenteCredenziali)
          this.backEndService.loginLavoratore(utenteCredenziali)

        }
      } else {
        Swal.fire("Errore nella registrazione")
      }
    })
  }

  public modify() {
    this.accediComponent.backToGeneralita()
  }

  ngAfterContentChecked(): void {
    this.utente as Lavoratore
  }
}

