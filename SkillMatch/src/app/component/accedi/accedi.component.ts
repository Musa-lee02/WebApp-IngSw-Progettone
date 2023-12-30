import { AfterViewChecked, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons';
import { faGoogle } from '@fortawesome/free-brands-svg-icons';



@Component({
  selector: 'app-accedi',
  templateUrl: './accedi.component.html',
  styleUrls: ['./accedi.component.css']
})
export class AccediComponent implements OnInit, AfterViewChecked {
  @ViewChild('container') container: ElementRef | undefined;
  @ViewChild('register') registerBtn: ElementRef | undefined;
  @ViewChild('login') loginBtn: ElementRef | undefined;


  generalitaForm : FormGroup
  credenzialiForm : FormGroup
  loginForm:FormGroup
  arrowLeft=faArrowLeft
  googleIcon=faGoogle

  constructor() {}
  ngAfterViewChecked(): void {


    if (this.loginForm.valid || this.credenzialiForm.valid){

      this.container?.nativeElement.classList.add('attivaBottone')
    }
    else{

      this.container?.nativeElement.classList.remove('attivaBottone')
    }

  }

  clickArrow(){
    
    this.container?.nativeElement.classList.remove('generalita')

  }
  onSubmit(){

    if(this.credenzialiForm.valid){
      this.container?.nativeElement.classList.add('generalita')
    }
  }

  ngOnInit(): void {
    this.generalitaForm= new FormGroup({
      nome: new FormControl(null, Validators.required),
      cognome: new FormControl(null, Validators.required),
      //dataNascita: new FormControl(null,Validators.required)

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


  }

  

  removeActive() {
    
        if (this.container) {
          console.log(this.container);
          this.container.nativeElement.classList.remove('active');
        }
      
    }
  
  addActive() {
    if (this.container) {
      console.log(this.container);
      this.container.nativeElement.classList.add('active');
  
  }
}
}