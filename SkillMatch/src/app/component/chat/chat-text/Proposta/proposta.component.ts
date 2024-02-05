import {AfterContentChecked, Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import Swal from 'sweetalert2';
import {Proposta} from "../../../../model/Proposta";
import {AnnuncioService} from "../../../../service/AnnuncioService";
import {ChatService} from "../../../../service/ChatService";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-proposta',
  templateUrl: './proposta.component.html',
  styleUrl: './proposta.component.css'
})
export class PropostaComponent implements AfterContentChecked, OnInit{

  @Input() proposta : Proposta
  @Output() sendProposta = new EventEmitter<any>()

  entita: string | null
  propostaForm: FormGroup

  constructor(private chatService : ChatService) {

  }


  ngOnInit(): void {

    this.entita=localStorage.getItem("scelta")
    this.propostaForm = new FormGroup({

      descrizione: new FormControl(null, Validators.required),
      data: new FormControl(null, Validators.required),
      prezzo: new FormControl(null, Validators.required)

    })
    console.log(this.proposta)
    console.log(this.entita)
  }



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

        this.proposta.stato="accettata"
        this.chatService.accettaProposta(this.proposta).subscribe(response =>{
          if(response){
            Swal.fire("Proposta accettata con successo");
          }
          else{
            Swal.fire("Errore imprevisto");
          }
        })



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

  isFormPropostaValid(){

    return this.propostaForm.valid
  }

  setProposta(){

    var dateString = this.propostaForm.get("data")?.value;
    var dateObject = new Date(Date.parse(dateString));

    let proposta : any={

      descrizione: this.propostaForm.get("descrizione")?.value,
      prezzo: this.propostaForm.get("prezzo")?.value,
      data: dateObject
    }
    console.log(proposta)

    Swal.fire({
      title: "Sei sicuro di voler inviare la proposta? il processo è irreversibile",
      showCancelButton: true,
      confirmButtonText: "Si invia",
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        this.sendProposta.emit(proposta)
        Swal.fire("Proposta Inviata, Ricarica la pagina");
      }
    });

  }





}
