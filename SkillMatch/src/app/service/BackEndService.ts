import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
import {LavoratoreSignUp, LavoratoreSignUpGoogle} from "../SignUpLavoratore";
import * as console from "console";
declare var window: any;

@Injectable({
  providedIn: 'root'
})
export class BackEndService implements OnInit{
  private url = "http://localhost:8080";
  constructor(private http: HttpClient) { }

  public postSignupWorker(lavoratore : LavoratoreSignUp): Observable<boolean> {
    console.log(lavoratore);
    return this.http.post<boolean>(this.url+"/lavoratore/signup/passo1", lavoratore);
  }

  public postSignupRegistrationWithGoogle(lavoratore : LavoratoreSignUpGoogle): Observable<boolean> {
    return this.http.post<boolean>(this.url+"/lavoratore/signup/google", lavoratore);

  }

  ngOnInit(): void {
    window['backEndServiceRef'] = this;
  }
}

