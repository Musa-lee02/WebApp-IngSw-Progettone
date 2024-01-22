import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
//import {Lavoratore, LavoratoreSignUp, LavoratoreSignUpGoogle} from "../model/Lavoratore";
import { Cliente } from '../model/Cliente';
import { Utente, UtenteCredenziali } from '../model/Utente';
import {DatiRegistrazioneService} from "./DatiRegistrazioneService";
import {Ambito} from "../model/Ambito";
import {Lavoratore} from "../model/Lavoratore";
import { Lavoratore } from '../SignUpLavoratore';
import { Annuncio } from '../model/Annuncio';


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

  public completeSignUp(utente : Lavoratore | Cliente, scelta: string, image: File | undefined): Observable<boolean> {

    const utenteBlob = new Blob([JSON.stringify(utente)], {type: 'application/json'});
    const formData = new FormData();
    formData.append('utente', utenteBlob);

    if(image != undefined){
      formData.append('img', image);
    }

    if (scelta==="lavoratore") {

      console.log((<Lavoratore>utente).provinciaLavoro)
      return this.http.post<boolean>(this.url + "/signup/completeRegistration/Lavoratore", (<Lavoratore>utente));
    }

    return this.http.post<boolean>(this.url + "/signup/completeRegistration/Cliente", (<Cliente>utente));

  }
  /*public completeSignUp(utente : Utente, scelta: string): Observable<boolean> {

    return this.http.post<boolean>(this.url + "/signup/completeRegistration/Utente", { params: { utente } });

  }*/
  public insertAnnuncio(annuncio: Annuncio, image: File){

    const annuncioBlob = new Blob([JSON.stringify(annuncio)], { type: 'application/json' });
    const formData = new FormData();
    formData.append('annuncio', annuncioBlob);
    if(image != null){
      formData.append('img', image);
    }
    console.log("Annuncio & Image sended")

    return this.http.post<Boolean>(
        this.url+"/annuncio/insertNewAnnuncio",
        formData
    );
  }

  public addImage(image: File){


    //const datiBlob = new Blob([JSON.stringify(data)], { type: 'application/json' });
    const formData = new FormData();
    //formData.append('dati', datiBlob);
    formData.append('img', image);

    console.log("Image sended")

    return this.http.post<Boolean>(
        this.url+"/images/annuncioImage",
        formData
    );
  }


  public verifyToken(token: string, username : string){
    this.http.get(this.url+"/ConfermaAccount",{params: {token: token}}).subscribe(data => {
  })}

    public getAmbiti(): Observable<Ambito[]>{
      return this.http.get<Ambito[]>(this.url+"/ambito/getAmbiti");
    }

}
