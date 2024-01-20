import {BackEndService} from "../../service/BackEndService";
import {DatiRegistrazioneService} from "../../service/DatiRegistrazioneService";

declare var google: any;
declare var window: any;
import { AfterViewChecked, Component, ComponentFactoryResolver, ComponentRef, ElementRef, NgZone, OnDestroy, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons';
import { faGoogle } from '@fortawesome/free-brands-svg-icons';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service'
import Swal from 'sweetalert2';
import {HttpErrorResponse} from "@angular/common/http";
import {Utente} from "../../model/Utente";
import {Lavoratore} from "../../model/Lavoratore";
import {elementSelectors} from "@angular/cdk/schematics";
import {Cliente} from "../../model/Cliente";



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

  ambiti: string[] = ['Cucina', 'Tecnologia', 'Edilizia', 'Elettronica', 'Meccanica', 'Informatica', 'Altro']

  province: string[] = ['Cosenza', 'Reggio Calabria', 'Vibo Valentia', 'Catanzaro', 'Crotone',
    'Napoli', 'Salerno', 'Avellino', 'Benevento', 'Caserta', 'Potenza', 'Matera' ]

  cliente : Cliente
  utente : Utente
  lavoratore : Lavoratore


  generalitaForm : FormGroup
  credenzialiForm : FormGroup
  loginForm:FormGroup
  ambitoForm:FormGroup
  arrowLeft=faArrowLeft
  googleIcon=faGoogle
  riepilogoDati: boolean=false
  url=""
  scelta : string

  constructor(private service: ServizioAnnunciService, private backEndService: BackEndService, private datiRegistrazione: DatiRegistrazioneService) {
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


  ngOnInit(): void {
      window['accediComponentRef'] = this;
      window['backEndServiceRef'] = this.backEndService;




    this.generalitaForm= new FormGroup({
      nome: new FormControl(null, Validators.required),
      cognome: new FormControl(null, Validators.required),
      dataNascita: new FormControl(null, Validators.required),
      provincia: new FormControl(null, Validators.required)

    })

    this.credenzialiForm=new FormGroup({

      username: new FormControl(null, Validators.required),
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null,[Validators.required,
        Validators.minLength(8),
        Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+-=]).{8,}$/)
      ]),
      confermaPassword: new FormControl(null,[Validators.required,
        Validators.minLength(8),
        Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+-=]).{8,}$/)
      ])

    }, { validators: this.passwordMatchValidators });

    this.loginForm=new FormGroup ({
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null,Validators.required),

    })

    this.ambitoForm=new FormGroup({
      foto: new FormControl(),
      zona: new FormControl(null,Validators.required),
      ambito: new FormControl(null,Validators.required),
    })

    this.service.setDoingAccesso(true)

    this.province=this.service.getProvince()
    this.ambiti=this.service.getAmbiti()
  }

  passwordMatchValidators(control: AbstractControl) {
    const password = control.get('password')?.value;
    const ripetiPassword = control.get('confermaPassword')?.value;

    return password === ripetiPassword ? null : { mismatch: true };
  }

  ngOnDestroy(): void {


    if(this.credenzialiForm.valid && this.generalitaForm.valid && this.ambitoForm.valid){
      Swal.fire("Ricora di confermare l'email se vuoi pubblicare o proporti per un annuncio")
      this.service.setAutenticato(true)
    }
  }



  onSelectFile(e:any){
    if(e.target.files){
      var reader = new FileReader();
      reader.readAsDataURL(e.target.files[0]);
      reader.onload = (event: any) => {
        this.url = event.target.result;
        //this.datiRegistrazione.setImmagineProfilo(e.target.files[0])
        this.lavoratore.imgProfilo=e.target.files[0]
      }

    }

  }

  onRiceviScelta(scelta: string){
    this.scelta=scelta
    this.componentScelta?.nativeElement.classList.add('remove')

  }
  clickArrow(){

    console.log(this.generalitaForm)
    this.container?.nativeElement.classList.remove('generalita')
    this.container?.nativeElement.classList.remove('ambito')
    this.ambitoForm.clearValidators();
    this.generalitaForm.clearValidators();

    this.generalitaForm.reset();
    console.log(this.generalitaForm)
  }

  doingAccesso(){

    return this.service.doingAccesso

  }

  skipAutentication(){

    return this.service.getSkipAutentication()
  }


  onSubmit(){


    if (this.generalitaForm.valid && this.scelta==="lavoratore") {
      if (this.scelta === "lavoratore") {
        //this.datiRegistrazione.setNome(this.generalitaForm.get("nome")?.value)
        //this.datiRegistrazione.setCognome(this.generalitaForm.get("cognome")?.value)
        //this.datiRegistrazione.setDataDiNascita(this.generalitaForm.get("dataNascita")?.value)
        this.container?.nativeElement.classList.add('ambito')
        //his.datiRegistrazione.setZonaDiCompetenza(this.ambitoForm.get("zona")?.value)
        //this.datiRegistrazione.setAmbiti(this.ambitoForm.get("ambito")?.value
      }
      else{
        this.container?.nativeElement.classList.add('emailConferma')
      }

    if(this.generalitaForm.valid && this.credenzialiForm.valid){
      if(this.ambitoForm.valid){
        this.lavoratore.provincia_lavoro=this.ambitoForm.get("zona")?.value
        this.lavoratore=this.ambitoForm.get("ambito")?.value
        this.lavoratore.imgProfilo=this.ambitoForm.get("foto")?.value
        this.lavoratore.username=this.credenzialiForm.get("username")?.value
        this.lavoratore.email=this.credenzialiForm.get("email")?.value
        this.lavoratore.password=this.credenzialiForm.get("password")?.value
        this.lavoratore.nome=this.generalitaForm.get("nome")?.value
        this.lavoratore.cognome=this.generalitaForm.get("cognome")?.value
        this.lavoratore.dataNascita=this.generalitaForm.get("dataNascita")?.value
        this.lavoratore.provincia=this.ambitoForm.get("zona")?.value
      }

      else{
        this.cliente.username=this.credenzialiForm.get("username")?.value
        this.cliente.email=this.credenzialiForm.get("email")?.value
        this.cliente.password=this.credenzialiForm.get("password")?.value
        this.cliente.nome=this.generalitaForm.get("nome")?.value
        this.cliente.cognome=this.generalitaForm.get("cognome")?.value
        this.cliente.dataNascita=this.generalitaForm.get("dataNascita")?.value
        this.cliente.provincia=this.ambitoForm.get("zona")?.value
      }




      this.container?.nativeElement.classList.add('emailConferma')
      this.riepilogoDati=true
      console.log(this.riepilogoDati)
    }



    }
    if (this.credenzialiForm.valid) {
      const utente = this.credenzialiForm.value
      this.backEndService.postCheckRegistrationCredential(utente).subscribe(
        (response) => {
          //console.log(response.message)
          //this.datiRegistrazione.setUsername(this.credenzialiForm.get("username")?.value)
          //this.datiRegistrazione.setEmail(this.credenzialiForm.get("email")?.value)

          this.container?.nativeElement.classList.add('generalita')


        }, (error: HttpErrorResponse) => {
          console.log(error)

          if (error.error === "Email già in uso")
            alert("Email già in uso")

          else if (error.error === "Username già in uso")
            alert("Username già in uso")

          else if (error.error === "Password non valida (deve contenere almeno una lettera maiuscola)")
            Swal.fire("Password non valida (deve contenere almeno una lettera maiuscola)")

          else if (error.error === "Password non valida (deve contenere almeno un numero)")
            Swal.fire("Password non valida (deve contenere almeno un numero)")

          else if (error.error === "Password non valida (deve contenere almeno 8 caratteri)")
            Swal.fire("Password non valida (deve contenere almeno 8 caratteri)")

          else if (error.error === "Password non valida (deve contenere almeno un carattere speciale)")
            Swal.fire("Password non valida (deve contenere almeno un carattere speciale)")

          else{
            Swal.fire("Errore generico")
          }


        })

      //this.container?.nativeElement.classList.add('generalita')

    }*/


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
    this.generalitaForm.patchValue({
      nome: googleData.given_name,
      cognome: googleData.family_name
    })
    this.container?.nativeElement.classList.add('generalita')
    if (this.generalitaForm.valid && this.scelta === "lavoratore") {
      this.ambitoForm.patchValue({
        foto: googleData.picture

      })
      this.container?.nativeElement.classList.add('ambito')
    }

  }

  backToGeneralita() {
    this.removeActive()
    this.container?.nativeElement.classList.add('generalita')
  }
}
