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
@Component({
  selector: 'app-riepilogo-dati',
  templateUrl: './riepilogo-dati.component.html',
  styleUrls: ['./riepilogo-dati.component.css','../accedi.component.css']
})
export class RiepilogoDatiComponent implements AfterContentChecked{
  @Input("utente") utente : Cliente | Lavoratore
  @Input("scelta") scelta : string


  constructor(private router: Router, private accediComponent : AccediComponent, private backEndService: BackEndService) {
  }

  ngOnInit(): void {

  }

  public getAmbiti() : string[]{
    console.log((<Lavoratore>(this.utente)).ambiti)
    return (<Lavoratore>this.utente).ambiti.map(a => a.nome)

  }

  public getZona() : string{
    return (<Lavoratore>this.utente).provinciaLavoro
  }


  public goToAccount() {

    this.backEndService.completeSignUp(this.utente, this.scelta, this.utente.imgProfilo).subscribe(response => {
      if (response) {
        const utenteCredenziali : UtenteCredenziali ={
          password: this.utente.password,
          username: this.utente.username
        }
        if (this.scelta === "cliente") {

          this.backEndService.login(utenteCredenziali)

          //this.router.navigate(['/Profilo/Cliente']);
        }else {
          console.log(utenteCredenziali)
          this.backEndService.login(utenteCredenziali)

        //this.router.navigate(['/Profilo/Lavoratore']);
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

