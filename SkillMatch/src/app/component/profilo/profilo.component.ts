import { Component, OnInit } from '@angular/core';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import { faPencil } from '@fortawesome/free-solid-svg-icons';
import { ActivatedRoute } from '@angular/router';
import { faArrowDown } from '@fortawesome/free-solid-svg-icons';
import {BackEndService} from "../../service/BackEndService";
import {Cliente} from "../../model/Cliente";
import {Lavoratore} from "../../model/Lavoratore";
import {Utente} from "../../model/Utente";
import {LavoratoreFieldService} from "../../service/LavoratoreFieldService";
import { Annuncio } from '../../model/Annuncio';
import {AnnuncioService} from "../../service/AnnuncioService";
import Swal from "sweetalert2";
import {RichiestaPagamento} from "../../model/RichiestaPagamento";
import {Proposta} from "../../model/Proposta";



@Component({
  selector: 'app-profilo',
  templateUrl: './profilo.component.html',
  styleUrls: ['./profilo.component.css','../../app.component.css']
})
export class ProfiloComponent implements OnInit {


  annunci:Annuncio[]
  pencil=faPencil
  utente : Lavoratore | Cliente
  entita:string | null
  propostaAccettata: Proposta

  richiestapagamento : RichiestaPagamento

  url:string;
  constructor(private service: ServizioAnnunciService,
              private route: ActivatedRoute,
              private backEndService: BackEndService,
              private lavoratoreService : LavoratoreFieldService,
              private annunciService : AnnuncioService
  ) { }


  ngOnInit(): void {

    /*
    this.http.post<LoginClienteDto>(this.url + "/retriveData/loginCliente",utente)
      .subscribe(response => {
        this.setToken(response.token);
        response.cliente.password = ""; // Rimuovi la password. è un modo bruttissimo, ma fa quello che deve
        localStorage.setItem("utente", JSON.stringify(response.cliente));

        console.log(this.getToken())
        console.log(localStorage.getItem("utente"))

        this.router.navigate(["/Profilo/Cliente"]);
      },(error) =>{
        console.log("errore da gestire?: (password od username non valide)" + error) //TODO
      });
    */

    this.annunciService.getAnnunciFinalizzati().subscribe(
      response => {
        this.annunci = response
        for (const annuncio of this.annunci){
          this.backEndService.getPropostaByAnnuncio(annuncio.id).subscribe(
            response => {
              annuncio.proposta = response


            }
          )
        }

      }, (error) => {
        console.log("errore.")
      });
    /*this.annunci = this.service.getAnnunci();*/
    this.utente = this.getUtente();
    console.log("ut:" + this.utente.username +"|")

    this.entita=localStorage.getItem("scelta")

    console.log(this.backEndService.getToken())
    this.getUtente()
    console.log("img:" + this.utente.imgProfilo);
    console.log("data di nascita: " + this.utente.dataNascita);

  }


  onSelectFile(e:any){
    if(e.target.files){
      var reader = new FileReader();
      reader.readAsDataURL(e.target.files[0]);
      reader.onload=(event:any)=>{
        this.url=event.target.result;

      }

    }
  }
  sortAnnunciFinalizzati(proposte : Proposta[]){
    for(const proposta of proposte){

    }

  }

  getPicProfile(){
    return this.getUtente().imgProfilo;
  }

  getUtente() : Lavoratore | Cliente{
    var utenteLogged = localStorage.getItem("utente");
    return JSON.parse(utenteLogged!);
  }

  public getAmbiti() : string[]{
    return this.lavoratoreService.getAmbiti(this.utente)

  }

  public getZona() : string{
    return this.lavoratoreService.getZona(this.utente)
  }


  vaiAlPagamento(annuncio : Annuncio) {
    console.log(annuncio.id)
    this.backEndService.getPropostaByAnnuncio(annuncio.id).subscribe( response => {
      this.propostaAccettata = response
      this.richiestapagamento = {
        customAmount: this.propostaAccettata.prezzoLavoro,
        idDest: this.propostaAccettata.lavoratore.username

      }
      console.log("Custo amount: "  + this.richiestapagamento.customAmount)

      if (this.propostaAccettata != null) {
        Swal.fire("Pagamento verso: " + this.richiestapagamento.idDest + "\n" + "Importo: " + this.richiestapagamento.customAmount + " euro")

        window.location.href = `http://localhost:8080/Pagamento?customAmount=${this.richiestapagamento.customAmount}
      &idDest=${this.richiestapagamento.idDest}&idMitt=${this.utente.username}
      &idAnnuncio=${this.propostaAccettata.annuncioRelativo.id.toString()}`

      }


    }, (error : any) => {
      Swal.fire("Errore")
    })

  }
}
