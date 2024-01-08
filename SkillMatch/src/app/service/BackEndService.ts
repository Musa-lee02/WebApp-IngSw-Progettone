import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
import {LavoratoreSignUp, LavoratoreSignUpGoogle} from "../SignUpLavoratore";

@Injectable({
  providedIn: 'root'
})
export class BackEndService {
  private url = "http://localhost:8080";
  constructor(private http: HttpClient) { }

  public postSignupWorker(lavoratore : LavoratoreSignUp): Observable<boolean> {
    console.log(lavoratore);
    return this.http.post<boolean>(this.url+"/lavoratore/signup/passo1", lavoratore);
  }

  public postSignupRegistrationWithGoogle(lavoratore : LavoratoreSignUpGoogle): Observable<boolean> {

  }
}

