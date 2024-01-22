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
  @Input("image") image!: File | undefined

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
    console.log((<Lavoratore>this.utente).provinciaLavoro )
    //let data =
    //this.utente.dataRegistrazione = data;


    this.backEndService.completeSignUp(this.utente, this.scelta).subscribe(response => {
      if (response) {
        if (this.scelta === "cliente") {
          this.router.navigate(['/Profilo/Cliente']);
        }else {
        this.router.navigate(['/Profilo/Lavoratore']);
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

