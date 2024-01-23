import { Component, OnInit } from '@angular/core';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import { faPencil } from '@fortawesome/free-solid-svg-icons';
import { ActivatedRoute } from '@angular/router';
import { faArrowDown } from '@fortawesome/free-solid-svg-icons';
import {BackEndService} from "../../service/BackEndService";
import {Cliente} from "../../model/Cliente";
import {Lavoratore} from "../../model/Lavoratore";
import {Utente} from "../../model/Utente";


@Component({
  selector: 'app-profilo',
  templateUrl: './profilo.component.html',
  styleUrls: ['./profilo.component.css','../../app.component.css']
})
export class ProfiloComponent implements OnInit {



  annunci:any
  pencil=faPencil
  utente : Lavoratore
  entita:string
  propostaAccettata: any

  url:string;
  constructor(private service: ServizioAnnunciService, private route: ActivatedRoute, private backEndService: BackEndService){

  }
  ngOnInit(): void {


    this.annunci = this.service.getAnnunci();
    console.log(this.annunci)

    if (this.route.snapshot.paramMap.get('Entita')) {

      this.entita = this.route.snapshot.paramMap.get('Entita')!;
      if (this.entita === "Cliente")
        this.entita == "Cliente";
      if (this.entita === "Lavoratore")
        this.entita == "Lavoratore";
    }

    console.log(this.backEndService.getToken())
    
  }


  onSelectFile(e:any){
    if(e.target.files){
      var reader = new FileReader();
      reader.readAsDataURL(e.target.files[0]);
      reader.onload=(event:any)=>{
        this.url=event.target.result;
        this.service.setPicProfile(this.url)
      }

    }
  }

  getPicProfile(){
    return this.getUtente().imgProfilo;
  }


  getUtente() : Utente{
    var utenteLogged = localStorage.getItem("utente");
    return JSON.parse(utenteLogged!);
  }



}
