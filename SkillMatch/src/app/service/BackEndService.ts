import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
//import {Lavoratore, LavoratoreSignUp, LavoratoreSignUpGoogle} from "../model/Lavoratore";
import { Cliente } from '../model/Cliente';
import { Utente } from '../model/Utente';
import {DatiRegistrazioneService} from "./DatiRegistrazioneService";
import {SceltaUtenteComponent} from "../component/accedi/scelta-utente/scelta-utente.component";
declare var window: any;

@Injectable({
  providedIn: 'root'
})
export class BackEndService{
  private url = "http://localhost:8080";
  constructor(private http: HttpClient) { }

  /*public postCheckRegistrationCredential(utenteSignUp : UtenteSignUp): Observable<string> {
    console.log(lavoratore);
    return this.http.post<string>(this.url+"/lavoratore/signup/passo1", lavoratore );
  }*/

 /* public postSignupRegistrationWithGoogle(lavoratore : LavoratoreSignUpGoogle): Observable<boolean> {
    return this.http.post<boolean>(this.url+"/lavoratore/signup/google", lavoratore);

  }*/

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

  public completeSignUp(datiRegistrazione : DatiRegistrazioneService): Observable<boolean> {
    return this.http.post<boolean>(this.url+"/lavoratore/signup/completeRegistration", datiRegistrazione);
  }
e
  public getUtenteByUsername(tipo: string, username: string): Observable<Utente>{

   // return this.http.post<Utente>()
  }

}

