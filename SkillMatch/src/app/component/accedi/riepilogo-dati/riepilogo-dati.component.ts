import {AfterContentChecked, AfterViewChecked, Component, Input, signal} from '@angular/core'


import { DatiRegistrazioneService } from "../../../service/DatiRegistrazioneService";
import Swal  from "sweetalert2";

import {Router } from "@angular/router";
import {AccediComponent} from "../accedi.component";
import {BackEndService} from "../../../service/BackEndService";
import {error} from "@angular/compiler-cli/src/transformers/util";
import {Utente} from "../../../model/Utente";
import {Lavoratore} from "../../../model/Lavoratore";
import {withInterceptorsFromDi} from "@angular/common/http";
@Component({
  selector: 'app-riepilogo-dati',
  templateUrl: './riepilogo-dati.component.html',
  styleUrls: ['./riepilogo-dati.component.css','../accedi.component.css']
})
export class RiepilogoDatiComponent implements AfterContentChecked{
  @Input("utente") utente : Utente
  @Input("scelta") scelta : string



  constructor(private router: Router, private accediComponent : AccediComponent, private backEndService: BackEndService) {
  }

  ngOnInit(): void {
  }

  public getAmbiti() : string[]{
    return (<Lavoratore>this.utente).ambiti
  }

  public getZona() : string{
    return (<Lavoratore>this.utente).provincia_lavoro
  }


  public goToAccount() {
    console.log("sdssda")
    //let data =
    //this.utente.dataRegistrazione = data;
    this.backEndService.completeSignUp(this.utente, this.scelta).subscribe(response => {
      if (response) {
        this.router.navigate(['/Profilo']);
      } else {
        Swal.fire("Errore nella registrazione")
      }
    })
  }

  public goToAccess() {
    this.accediComponent.backToGeneralita()
  }

  ngAfterContentChecked(): void {
    this.utente as Lavoratore
  }
}

