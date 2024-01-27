import { AfterContentChecked, Component, Input, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import {Proposta} from "../../../../model/Proposta";

@Component({
  selector: 'app-proposta',
  templateUrl: './proposta.component.html',
  styleUrl: './proposta.component.css'
})
export class PropostaComponent implements AfterContentChecked{

  @Input() proposta : Proposta

  ngAfterContentChecked(): void {

    //console.log(this.proposta)

  }
  alertConferma(){
    Swal.fire({
      title: "Sei sicuro di voler accettare la proposta del lavoratore",
      showCancelButton: true,
      confirmButtonText: "Accetta",
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        Swal.fire("Proposta accettata con successo");
      }
    });
  }

  alertRifiuta(){
    Swal.fire({
      title: "Sei sicuro di voler rifiutare la proposta del lavoratore",
      showCancelButton: true,
      confirmButtonText: "Si desidero rifiutare",
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        Swal.fire("Proposta rifiutata con successo");
      }
    });
  }




}
