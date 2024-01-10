import {BackEndService} from "../../service/BackEndService";

declare var google: any;
declare var window: any;
import {
  AfterViewChecked,
  Component,
  ComponentFactoryResolver,
  ComponentRef,
  ElementRef,
  Injector,
  NgZone,
  OnDestroy,
  OnInit,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons';
import { faGoogle } from '@fortawesome/free-brands-svg-icons';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service'
import { SceltaUtenteComponent } from './scelta-utente/scelta-utente.component';
import Swal from 'sweetalert2';



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



  generalitaForm : FormGroup
  credenzialiForm : FormGroup
  loginForm:FormGroup
  ambitoForm:FormGroup
  arrowLeft=faArrowLeft
  googleIcon=faGoogle
  riepilogoDati: boolean=false
  url=""

  constructor(private service: ServizioAnnunciService, private backEndService: BackEndService ){}


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
      dataNascita: new FormControl(null,Validators.required)

    })

    this.credenzialiForm=new FormGroup({

      username: new FormControl(null, Validators.required),
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null,Validators.required),
      confermaPassword: new FormControl(null,Validators.required)

    })

    this.loginForm=new FormGroup ({
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null,Validators.required),

    })

    this.ambitoForm=new FormGroup({
      foto: new FormControl(null,Validators.required),
      zona: new FormControl(null,Validators.required),
      ambito: new FormControl(null,Validators.required),
    })

    this.service.setDoingAccesso(true)
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
      reader.onload=(event:any)=>{
        this.url=event.target.result;
      }

    }

  }

  isLavoratore(){

    return this.service.isLavoratore()
  }


  onRiceviScelta(scelta: string){


    if(scelta==="cliente"){
      this.service.setlavoratoreBool(false)
    }
    else
      this.service.setlavoratoreBool(true);

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

    if(this.generalitaForm.valid && this.credenzialiForm.valid && this.ambitoForm.valid){

      this.container?.nativeElement.classList.add('emailConferma')
      this.riepilogoDati=true
      console.log(this.riepilogoDati)
    }

    if(this.generalitaForm.valid && this.isLavoratore()){
      this.container?.nativeElement.classList.add('ambito')
    }

    if(this.generalitaForm.valid && !this.isLavoratore()){
      this.container?.nativeElement.classList.add('emailConferma')
    }

    if(this.credenzialiForm.valid){
      this.container?.nativeElement.classList.add('generalita')

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
checkPassword(form : FormGroup):boolean {


    if(form.get("password")?.value===form.get("confermaPassword")?.value){
      return true;
    }
    else return false;
}

registrazioneGoogle(googleData : any){
    this.generalitaForm.patchValue({
      nome: googleData.given_name,
      cognome: googleData.family_name
    })
   this.container?.nativeElement.classList.add('generalita')
  if(this.generalitaForm.valid && this.isLavoratore()){
    this.ambitoForm.patchValue({
      foto: googleData.picture

    })
    console.log(this.ambitoForm.get("foto")?.value)
    this.container?.nativeElement.classList.add('ambito')
  }

}


}
