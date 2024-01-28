import {BackEndService} from "../../service/BackEndService";
import { HttpClient } from '@angular/common/http';
declare var google: any;
declare var window: any;
import { AfterViewChecked, Component, ComponentFactoryResolver, ComponentRef, ElementRef, NgZone, OnDestroy, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons';
import { faGoogle } from '@fortawesome/free-brands-svg-icons';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service'
import Swal from 'sweetalert2';
import {HttpErrorResponse} from "@angular/common/http";
import {Utente, UtenteCredenziali} from "../../model/Utente";
import {Lavoratore} from "../../model/Lavoratore";
import {elementSelectors} from "@angular/cdk/schematics";
import {Cliente} from "../../model/Cliente";
import {Ambito} from "../../model/Ambito";
import {Province} from "../../model/Province";



@Component({
  selector: 'app-accedi',
  templateUrl: './accedi.component.html',
  styleUrls: ['./accedi.component.css']
})
export class AccediComponent implements OnInit, AfterViewChecked, OnDestroy {
  @ViewChild('container') container: ElementRef | undefined;
  @ViewChild('register') registerBtn: ElementRef | undefined;
  @ViewChild('login') loginBtn: ElementRef | undefined;
  @ViewChild('componentScelta') componentScelta: ElementRef | undefined;
  states: string[] = [
    'Alabama',
    'Alaska',
    'Arizona',
    'Arkansas',
  ];

  ambiti: Ambito[]

  province: Province[]



  utente: Lavoratore | Cliente

  /*lavoratore: Lavoratore={
    ambiti: [],
    cognome: "",
    dataNascita: '0-0-0',
    dataRegistrazione:'' ,
    email: "",
    imgProfilo: null,
    nome: "",
    notifica_email: false,
    password: "",
    provincia: "",
    provincia_lavoro: "",
    punteggio: 0,
    registrato: false,
    username: ""
  }*/



  picProfile: any
  generalitaForm: FormGroup
  credenzialiForm: FormGroup
  loginForm: FormGroup
  ambitoForm: FormGroup
  arrowLeft = faArrowLeft
  googleIcon = faGoogle
  riepilogoDati: boolean = false
  url = ""
  scelta: string

  constructor(private httpClient: HttpClient, private service: ServizioAnnunciService, private backEndService: BackEndService) {
  }


  ngAfterViewChecked(): void {


  }

  /*setTrue(){

    console.log(this.credenzialiForm.valid+ "+"+ this.generalitaForm.valid +"+"+ this.ambitoForm.valid)
    if (this.credenzialiForm.valid && this.generalitaForm.valid && !this.isLavoratore() ){

      this.service.setAutenticato()
      return
    }

    if (this.credenzialiForm.valid && this.generalitaForm.valid && this.ambitoForm.valid){

      this.service.setAutenticato()
      return
    }
  }*/
  private googlepassword: string;
  private googleUsername: string;
  private googleEmail: string;


  ngOnInit(): void {
    window['accediComponentRef'] = this;
    window['backEndServiceRef'] = this.backEndService;


    function decodeJWTToken(token : any){
          return JSON.parse(atob(token.split(".")[1]))
    }


      (globalThis as any).handleOauthResponse = (response : any) => {
          const responsePayload = decodeJWTToken(response.credential)
          console.log(responsePayload)


          window['backEndServiceRef'].CheckExistenceGoogleAccount(responsePayload.sub).subscribe((res: boolean) => {
              if (res) {
                  this.doLoginGoogle(responsePayload)
              } else {
                  this.registrazioneGoogle(responsePayload)
              }
          })
      }



    this.backEndService.getAmbiti().subscribe(
      data => {
        this.ambiti = data
        console.log(this.ambiti)
      }
    )

    this.httpClient.get<Province[]>('http://mobilio.altervista.org').subscribe( data =>
      {
        console.log(data)
        this.province=data
      }
    )


    this.generalitaForm = new FormGroup({
      nome: new FormControl(null, Validators.required),
      cognome: new FormControl(null, Validators.required),
      dataNascita: new FormControl(null, Validators.required),
      provincia: new FormControl(null, Validators.required)

    })

    this.credenzialiForm = new FormGroup({

      username: new FormControl(null, Validators.required),
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null, [Validators.required,
        Validators.minLength(8),
        Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+-=]).{8,}$/)
      ]),
      confermaPassword: new FormControl(null, [Validators.required,
        Validators.minLength(8),
        Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+-=]).{8,}$/)
      ])

    }, {validators: this.passwordMatchValidators});

    this.loginForm = new FormGroup({
      username: new FormControl(null, Validators.required),
      password: new FormControl(null, Validators.required),

    })

    this.ambitoForm = new FormGroup({
      foto: new FormControl(),
      zona: new FormControl(null, Validators.required),
      ambito: new FormControl(null, Validators.required),
    })




  }

  passwordMatchValidators(control: AbstractControl) {
    const password = control.get('password')?.value;
    const ripetiPassword = control.get('confermaPassword')?.value;

    return password === ripetiPassword ? null : {mismatch: true};
  }

  ngOnDestroy(): void {


    if (this.credenzialiForm.valid && this.generalitaForm.valid && this.ambitoForm.valid) {
      Swal.fire("Ricorda di confermare l'email se vuoi pubblicare o proporti per un annuncio")

    }
  }


  onSelectFile(e: any) {
    if (e.target.files) {
      console.log(e.target.files[0])
      this.picProfile = e.target.files[0]
      var reader = new FileReader();
      reader.readAsDataURL(e.target.files[0]);
      reader.onload = (event: any) => {
        this.url = event.target.result;
      }
      /* per prendere l'immagine, basta questo, se si vuole utilizzare la variabile image:
          if(e.target.files){
            this.image = e.target.files[0]
          }
          //... altrimenti va bene il picProfile
          */
    }
  }



  onRiceviScelta(scelta: string) {
    this.scelta = scelta
    this.componentScelta?.nativeElement.classList.add('remove')

  }

  clickArrow() {

    console.log(this.generalitaForm)
    this.container?.nativeElement.classList.remove('generalita')
    this.container?.nativeElement.classList.remove('ambito')
    this.ambitoForm.clearValidators();
    this.generalitaForm.clearValidators();
    this.generalitaForm.reset();
    this.ambitoForm.reset();

    console.log(this.generalitaForm)
  }

  doingAccesso() {

    return this.service.doingAccesso

  }

  skipAutentication() {

  }

  doLogin(){

    const username: string=this.loginForm.value.username
    const password: string=this.loginForm.value.password
    const utenteCredenziali : UtenteCredenziali ={
      password: password,
      username: username
    }
    console.log(utenteCredenziali)
    if(this.scelta === "lavoratore"){
      this.backEndService.loginLavoratore(utenteCredenziali)
    }
    else{
      this.backEndService.loginCliente(utenteCredenziali)
    }
  }

  doLoginGoogle(responsePayload: any){
        const credenzialiGoogle = {username: responsePayload.sub, password: this.generateUniqueString(responsePayload.email)}
        if(this.scelta === "lavoratore"){
            this.backEndService.loginLavoratore(credenzialiGoogle)
        }
        else{
            this.backEndService.loginCliente(credenzialiGoogle)
        }

    }

  onSubmitCredenziali(){
    if (this.credenzialiForm.valid) {
      const utente = this.credenzialiForm.value
      this.backEndService.postCheckRegistrationCredential(utente).subscribe(
        (response) => {
          this.container?.nativeElement.classList.add('generalita')

        }, (error: HttpErrorResponse) => {
          console.log(error)

          if (error.error === "Email già in uso")
            Swal.fire("Email già in uso")

          else if (error.error === "Username già in uso")
            Swal.fire("Username già in uso")

          else if (error.error === "Password non valida (deve contenere almeno una lettera maiuscola)")
            Swal.fire("Password non valida (deve contenere almeno una lettera maiuscola)")

          else if (error.error === "Password non valida (deve contenere almeno un numero)")
            Swal.fire("Password non valida (deve contenere almeno un numero)")

          else if (error.error === "Password non valida (deve contenere almeno 8 caratteri)")
            Swal.fire("Password non valida (deve contenere almeno 8 caratteri)")

          else if (error.error === "Password non valida (deve contenere almeno un carattere speciale)")
            Swal.fire("Password non valida (deve contenere almeno un carattere speciale)")

          else {
            Swal.fire("Errore generico")
          }


        })
    }
  }
  onSubmitGeneralita(){

      if (this.generalitaForm.valid) {

        if (this.scelta === "cliente") {
        this.utente = {
          cognome: this.generalitaForm.get("cognome")?.value,
          dataNascita: this.generalitaForm.get("dataNascita")?.value,
          dataRegistrazione: new Date(),
          email: this.credenzialiForm.get("email")?.value ? this.credenzialiForm.get("email")?.value : this.googleEmail,
          imgProfilo: this.picProfile,
          nome: this.generalitaForm.get("nome")?.value,
          password: this.credenzialiForm.get("password")?.value ? this.credenzialiForm.get("password")?.value : this.googlepassword,
          provincia: this.generalitaForm.get("provincia")?.value.nome,
          registrato: false,
          username: this.credenzialiForm.get("username")?.value ? this.credenzialiForm.get("username")?.value : this.googleUsername


        }


          this.riepilogoDati = true
          return

      }else if(this.scelta==="lavoratore"){
          this.container?.nativeElement.classList.add('ambito')
        }
    }


  }
  onSubmit() {

      if (this.ambitoForm.valid) {


        this.utente={
          ambiti: this.ambitoForm.get("ambito")?.value,
          cognome: this.generalitaForm.get("cognome")?.value,
          dataNascita: this.generalitaForm.get("dataNascita")?.value,
          dataRegistrazione: new Date(),
          email : this.credenzialiForm.get("email")?.value ?  this.credenzialiForm.get("email")?.value : this.googleEmail,
          imgProfilo:this.picProfile,
          nome:this.generalitaForm.get("nome")?.value,
          notificaEmail: false,
          password: this.credenzialiForm.get("password")?.value ? this.credenzialiForm.get("password")?.value : this.googlepassword,
          provincia: this.generalitaForm.get("provincia")?.value.nome,
          provinciaLavoro: this.ambitoForm.get("zona")?.value.nome,
          punteggio: 0,
          registrato: false,
          username: this.credenzialiForm.get("username")?.value ? this.credenzialiForm.get("username")?.value : this.googleUsername

        }



        this.riepilogoDati = true
        return

      }
      }




  removeActive() {

    if (this.container) {

      this.container.nativeElement.classList.remove('active');
    }

  }

  addActive() {
    if (this.container) {

      this.container.nativeElement.classList.add('active');

    }
  }

  checkPassword(form: FormGroup): boolean {


    if (form.get("password")?.value === form.get("confermaPassword")?.value) {
      return true;
    } else return false;
  }

  registrazioneGoogle(googleData: any) {

        this.googleUsername = googleData.sub
        this.googleEmail =  googleData.email
        this.googlepassword =  this.generateUniqueString(googleData.email)


        this.generalitaForm.patchValue({
            nome: googleData.given_name,
            cognome: googleData.family_name
        })
        console.log("sasa")
        this.container?.nativeElement.classList.add('generalita')
        console.log(this.scelta)
        if (this.scelta === "lavoratore") {
            console.log("dovrebbe inserire img google")
            this.url = googleData.picture
            this.picProfile = googleData.picture
            //this.container?.nativeElement.classList.add('ambito')
        }

    }

 private generateUniqueString(email: string): string {
    let hash = 0;
    for (let i = 0; i < email.length; i++) {
      const char = email.charCodeAt(i);
      hash = ((hash << 5) - hash) + char;
      hash |= 0; // Convert to 32bit integer
    }

    let randomString = '';
    const possibleChars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    const hashString = hash.toString();

    for (let i = 0; i < hashString.length; i++) {
      const index = Math.abs(hashString.charCodeAt(i) % possibleChars.length);
      randomString += possibleChars[index];
    }

    return randomString;
  }


  backToGeneralita() {
    this.riepilogoDati=false
    this.container?.nativeElement.classList.remove('generalita')
    this.container?.nativeElement.classList.remove('ambito')
    this.ambitoForm.clearValidators();
    this.generalitaForm.clearValidators();
  }

}
