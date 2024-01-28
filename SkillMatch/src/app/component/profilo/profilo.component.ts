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
  propostaAccettata: any

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
    }, (error) => {
        console.log("errore. da modificare(?)")
    });
    /*this.annunci = this.service.getAnnunci();*/
    this.utente = this.getUtente();
    console.log(this.annunci)

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


}
