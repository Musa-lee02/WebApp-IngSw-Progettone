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



  states: string[] = [
    'Alabama',
    'Alaska',
    'Arizona',
    'Arkansas',
    'California',
    'Colorado',
    'Connecticut',
    'Delaware',
    'Florida',
    'Georgia',
    'Hawaii',
    'Idaho',
    'Illinois',
    'Indiana',
    'Iowa',
    'Kansas',
    'Kentucky',
    'Louisiana',
    'Maine',
    'Maryland',
    'Massachusetts',
    'Michigan',
    'Minnesota',
    'Mississippi',
    'Missouri',
    'Montana',
    'Nebraska',
    'Nevada',
    'New Hampshire',
    'New Jersey',
    'New Mexico',
    'New York',
    'North Carolina',
    'North Dakota',
    'Ohio',
    'Oklahoma',
    'Oregon',
    'Pennsylvania',
    'Rhode Island',
    'South Carolina',
    'South Dakota',
    'Tennessee',
    'Texas',
    'Utah',
    'Vermont',
    'Virginia',
    'Washington',
    'West Virginia',
    'Wisconsin',
    'Wyoming',
  ];

  
  generalitaForm : FormGroup
  credenzialiForm : FormGroup
  loginForm:FormGroup
  ambitoForm:FormGroup
  arrowLeft=faArrowLeft
  googleIcon=faGoogle
  url=""
  constructor() {}

  ngAfterViewChecked(): void {


    if (this.loginForm.valid || this.credenzialiForm.valid){

      this.container?.nativeElement.classList.add('attivaBottone')
    }
    else{

      this.container?.nativeElement.classList.remove('attivaBottone')
    }

  }

  ngOnInit(): void {

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
  clickArrow(){
    
    this.container?.nativeElement.classList.remove('generalita')
    this.container?.nativeElement.classList.remove('ambito')

  }
  onSubmit(){

    console.log(this.generalitaForm.valid +"+" +this.credenzialiForm.valid)
    if(this.credenzialiForm.valid){
      this.container?.nativeElement.classList.add('generalita')
      
    }

    if(this.generalitaForm.valid){
      this.container?.nativeElement.classList.add('ambito')
    }
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