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


declare var window: any;

@Injectable({
  providedIn: 'root'
})
export class BackEndService{
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



  checkAuthentication(){
    this.http.post<AuthToken>(this.url + "/isAuthenticated",
      {"Authorization":"Basic " + this.token}, {withCredentials: true}).subscribe(
      res => {
        if (!res){
          this.removeToken();
        }
      }
    );
  }

  isAuthenticated(){
    return this.getToken() != undefined;
  }
  /*

  public login(utente : UtenteCredenziali){
    this.http.post<AuthToken>(this.url + "/login",utente,{withCredentials: true})
      .subscribe(response => {
        this.setToken(response.token);
        console.log(this.getToken())

      });
  }
  */

  //Funziona
  public loginLavoratore(utente : UtenteCredenziali){
    this.http.post<LoginLavoratoreDto>(this.url + "/retriveData/loginLavoratore",utente)
      .subscribe(response => {
        this.setToken(response.token);
        response.lavoratore.password = ""; // Rimuovi la password. è un modo bruttissimo, ma fa quello che deve
        localStorage.setItem("utente", JSON.stringify(response.lavoratore));
        localStorage.setItem("scelta", "lavoratore")
        console.log(this.getToken())
        console.log(localStorage.getItem("utente"))


        this.router.navigate(["/Profilo"]);
      },(error) =>{
        console.log("errore da gestire?: (password od username non valide)" + error) //TODO
      });

  }

  //Funziona
  public loginCliente(utente : UtenteCredenziali){
    this.http.post<LoginClienteDto>(this.url + "/retriveData/loginCliente",utente)
      .subscribe(response => {
        this.setToken(response.token);
        response.cliente.password = ""; // Rimuovi la password. è un modo bruttissimo, ma fa quello che deve
        localStorage.setItem("utente", JSON.stringify(response.cliente));
        localStorage.setItem("scelta", "cliente")

        console.log(this.getToken())
        console.log(localStorage.getItem("utente"))

        this.router.navigate(["/Profilo"]);
      },(error) =>{
        console.log("errore da gestire?: (password od username non valide)" + error) //TODO
      });
  }


  logout(){
    this.http.post<AuthToken>(this.url + "/logout",
      {"Authorization":"Basic " + this.token}, {withCredentials: true}).subscribe(
      res => {
        if (res){
          this.removeToken();
        }
        /*this.router.navigate(["/"]);*/
      }
    );
  }

  public getUtente(): Observable<Lavoratore>{

        return this.http.get<Lavoratore>(this.url + "/lavoratore/getLavoratoreByUsername?token=" + this.getToken());

  }

  public postCheckRegistrationCredential(utente : UtenteCredenziali): Observable<string> {
    return this.http.post<string>(this.url+"/signup/passo1", utente);
  }

  public CheckExistenceGoogleAccount(utente : Utente): Observable<boolean> {
    return this.http.post<boolean>(this.url+"/signup/google/checkExistence", utente);

  }

  /*
  //Non serve, non si prendono in questo modo le immagini (es. di come si prende un'immagine: http://localhost:8080/images/default.jpg)
  public postGetPicProfile(utenteId: string): Observable<string>{

    return this.http.post<string> ( this.url+"/images/", utenteId );//da modificare
  }
  */
  /*public retriveWorkerProfile(username: string): Observable<Lavoratore> {
    return this.http.get<Lavoratore>(this.url+"/lavoratore/signin/infoprofilo"+username);
  }*/

  public postSignUpCliente(stringa :string):Observable<string>{

    console.log(this.url)
    return this.http.post<string>(this.url+"/data/cliente/signUp",stringa);
  }

  //Funziona
  public completeSignUp(utente : Utente, scelta: string): Observable<boolean> {

    const formData = new FormData();
    if(utente.imgProfilo != undefined){
      formData.append('img', utente.imgProfilo);
      utente.imgProfilo = undefined
    }

    const utenteBlob = new Blob([JSON.stringify(utente)], {type: 'application/json'});

    formData.append('lavoratore', utenteBlob);

    if (scelta==="lavoratore") {

      console.log((<Lavoratore>utente))
      return this.http.post<boolean>(this.url + "/signup/completeRegistration/Lavoratore", formData);
    }

    return this.http.post<boolean>(this.url + "/signup/completeRegistration/Cliente", <Cliente>utente);

  }

  //Funziona
  public insertAnnuncio(annuncio: Annuncio, image: File){

    const annuncioBlob = new Blob([JSON.stringify(annuncio)], {type: 'application/json'});
    const formData = new FormData();
    formData.append('annuncio', annuncioBlob);
    if (image != null) {
      formData.append('img', image);
    }
    formData.append('token', this.getToken()!);

    return this.http.post<Boolean>(
      this.url + "/annuncio/insertNewAnnuncio",
      formData
    );
  }

  // Funziona
  public getAnnunciWithToken(): Observable<Annuncio[]>{
    if( localStorage.getItem("scelta")==="cliente"){
      return this.http.get<Annuncio[]>(
      this.url+"/annuncio/getAnnunciWithToken/"+this.getToken());
    }
    else{
      return this.http.get<Annuncio[]>(
        this.url+"/annuncio/getAnnunciWithTokenLavoratore/"+this.getToken());
    }
  }
  public getAnnunciWithChat(): Observable<Annuncio[]>{
    if( localStorage.getItem("scelta")==="cliente"){
      return this.http.get<Annuncio[]>(

          this.url+"/annuncio/getAnnunciWithChat/"+this.getToken());
    }
    else{
      return this.http.get<Annuncio[]>(
          this.url+"/annuncio/getAnnunciWithTokenLavoratore");
    }

  }

  public verifyToken(token: string, username : string){
    this.http.get(this.url+"/ConfermaAccount",{params: {token: token}}).subscribe(data => {
  })}

    public getAmbiti(): Observable<Ambito[]>{
      return this.http.get<Ambito[]>(this.url+"/ambito/getAmbiti");
    }

}
