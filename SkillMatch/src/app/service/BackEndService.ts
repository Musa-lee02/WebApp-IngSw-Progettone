import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
import {Lavoratore, LavoratoreSignUp, LavoratoreSignUpGoogle} from "../SignUpLavoratore";
declare var window: any;

@Injectable({
  providedIn: 'root'
})
export class BackEndService{
  private url = "http://localhost:8080";
  constructor(private http: HttpClient) { }

  public postSignupWorker(lavoratore : LavoratoreSignUp): Observable<boolean> {
    console.log(lavoratore);
    return this.http.post<boolean>(this.url+"/lavoratore/signup/passo1", lavoratore);
  }

  public postSignupRegistrationWithGoogle(lavoratore : LavoratoreSignUpGoogle): Observable<boolean> {
    return this.http.post<boolean>(this.url+"/lavoratore/signup/google", lavoratore);

  }

  public retriveWorkerProfile(username: string): Observable<Lavoratore> {
    return this.http.get<Lavoratore>(this.url+"/lavoratore/signin/infoprofilo"+username);
  }

}

