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
export class ChatService{
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

  public inviaMessaggio(messaggio : Messaggio){

      console.log(messaggio.isLavoratore)
      this.http.post<boolean>(this.url+"/chat/inviaMessaggio", messaggio).subscribe(response =>
        console.log(response)
      )

  }

  public getMessaggiByChat(chat : Chat):Observable<Messaggio[]> {


    return this.http.post<Messaggio[]>(this.url + "/chat/getMessaggi",chat)

  }
  public getLavoratoriByIdAnnuncio(id : number): Observable<Lavoratore[]>{

    return this.http.get<Lavoratore[]>(this.url+"/lavoratore/getLavoratoreByIdAnnuncio?id="+id)

  }

  public getProposta(chat : Chat): Observable<Proposta>{

    return this.http.post<Proposta>(this.url+"/chat/getProposta",chat)
  }

  public setProposta(proposta : Proposta): Observable<Proposta>{

    console.log("dsdssd")

    return this.http.post<Proposta>(this.url+"/chat/setProposta",proposta)
  }

  public accettaProposta(proposta : Proposta):Observable<boolean>{


    return this.http.post<boolean>(this.url + "/chat/accettaProposta", proposta);

  }

}

