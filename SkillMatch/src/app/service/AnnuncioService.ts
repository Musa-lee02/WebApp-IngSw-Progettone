import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
//import {Lavoratore, LavoratoreSignUp, LavoratoreSignUpGoogle} from "../model/Lavoratore";
import { Cliente } from '../model/Cliente';
import {AuthToken, Utente, UtenteCredenziali} from '../model/Utente';
import {Ambito} from "../model/Ambito";
import {Lavoratore} from "../model/Lavoratore";
import {Router} from "@angular/router";
import { Annuncio } from '../model/Annuncio';
import { LoginLavoratoreDto } from '../model/LoginLavoratoreDto';
import { LoginClienteDto } from '../model/LoginClienteDto';
import {Messaggio} from "../model/Messaggio";
import {Chat} from "../model/Chat";
import {Proposta} from "../model/Proposta";


declare var window: any;

@Injectable({
  providedIn: 'root'
})
export class AnnuncioService{
  private url = "http://localhost:8080";
  constructor(private http: HttpClient , private router: Router) { }

  public token?:string | null;

  getToken(){
    if (this.token == undefined){
      this.token = localStorage.getItem("user-token");
    }
    return this.token;
  }

  setToken(token:string){
    this.token = token;
    localStorage.setItem("user-token", token);

  }

  removeToken(){
    this.token = undefined;
    localStorage.removeItem("user-token");
    localStorage.removeItem("scelta");
    localStorage.removeItem("utente")
  }

  public insertAnnuncio(annuncio: Annuncio, image: File){

    const annuncioBlob = new Blob([JSON.stringify(annuncio)], {type: 'application/json'});
    const formData = new FormData();
    formData.append('annuncio', annuncioBlob);
    if (image != null) {
      formData.append('img', image);
    }
    formData.append('token', this.getToken()!);

    console.log("Annuncio & Image sended")

    return this.http.post<Boolean>(
      this.url + "/annuncio/insertNewAnnuncio",
      formData
    );
  }

  public getAnnunci (): Observable<Annuncio[]>{

    return this.http.get<Annuncio[]>(this.url+"/annuncio/getAnnunci")

  }
  public getAnnunciByAmbitoEZona(ambito: string, provincia : string):Observable<Annuncio[]>{

    console.log(ambito , provincia)
    console.log((this.url+"/annuncio/getAnnunciByAmbitoEZona?ambito="+ambito+"&provincia="+encodeURIComponent(provincia)))
    return this.http.get<Annuncio[]>(this.url+"/annuncio/getAnnunciByAmbitoEZona?ambito="+ambito+"&provincia="+encodeURIComponent(provincia))
  }


  public getAnnunciFinalizzati(): Observable<Annuncio[]>{
    return this.http.get<Annuncio[]>(this.url+"/annuncio/getAnnunciFinalizzati/"+this.getToken())

  }
  public getLavoratoreAnnuncio(id:number) : Observable<Lavoratore>{

    return this.http.get<Lavoratore>(this.url+"/lavoratore/getLAVORATOREByIdAnnuncio?id="+id)
  }




  public getAmbiti(): Observable<Ambito[]>{
    return this.http.get<Ambito[]>(this.url+"/ambito/getAmbiti");
  }


  public inviaCandidatura(annuncio : Annuncio){

    let chat: Chat={
      annuncio: annuncio,
      cliente: annuncio.cliente,
      lavoratore: JSON.parse(localStorage.getItem("utente")!),
      //messaggi: []

    }
    this.http.post<boolean>(this.url+"/chat/creaChat", chat).subscribe(response=>{

        if(response){
          this.router.navigate(["/Chat"])
        }
    })
  }



}

