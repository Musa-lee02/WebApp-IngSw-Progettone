import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
//import {Lavoratore, LavoratoreSignUp, LavoratoreSignUpGoogle} from "../model/Lavoratore";
import { Cliente } from '../model/Cliente';
import { Utente, UtenteCredenziali } from '../model/Utente';
import {DatiRegistrazioneService} from "./DatiRegistrazioneService";

declare var window: any;

@Injectable({
  providedIn: 'root'
})
export class BackEndService{
  private url = "http://localhost:8080";
  constructor(private http: HttpClient) { }

  public postCheckRegistrationCredential(utente : UtenteCredenziali): Observable<string> {
    return this.http.post<string>(this.url+"/signup/passo1", utente);
  }

  public CheckExistenceGoogleAccount(utente : Utente): Observable<boolean> {
    return this.http.post<boolean>(this.url+"/signup/google/checkExistence", utente);

  }

  public postGetPicProfile(utenteId: string): Observable<string>{

    return this.http.post<string> ( this.url+"/images/", utenteId );//da modificare
  }
  /*public retriveWorkerProfile(username: string): Observable<Lavoratore> {
    return this.http.get<Lavoratore>(this.url+"/lavoratore/signin/infoprofilo"+username);
  }*/

  public postSignUpCliente(stringa :string):Observable<string>{

    console.log(this.url)
    return this.http.post<string>(this.url+"/data/cliente/signUp",stringa);
  }

  public completeSignUp(utente : Utente, scelta: string): Observable<boolean> {
    if (scelta==="lavoratore") {
      return this.http.post<boolean>(this.url + "/signup/completeRegistration/Lavoratore", utente);
    }

    return this.http.post<boolean>(this.url + "/signup/completeRegistration/Cliente", utente);

  }



  public verifyToken(token: string, username : string){
    this.http.get(this.url+"/ConfermaAccount",{params: {token: token}}).subscribe(data => {
  })

}
}
