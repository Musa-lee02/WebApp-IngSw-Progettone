import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ServizioAnnunciService } from '../../../service/servizio-annunci.service';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons';



type Annunci ={

  
  img: string
  descrizione: string
  ambito: string
  titolo: string
  zona:string

}
@Component({
  selector: 'app-form-annuncio',
  templateUrl: './form-annuncio.component.html',
  styleUrls: ['./form-annuncio.component.css']  
})


export class FormAnnuncioComponent {

  ambiti : any
  province : any
  minDate: Date;
  url = '../../assets/imagedefault.avif'
  nuovoAnnuncioForm:FormGroup
  arrowLeft=faArrowLeft
  @ViewChild('container') container: ElementRef | undefined;
  cardAnnuncio: any
  
  constructor(private service: ServizioAnnunciService){
    this.minDate = new Date();

    //this.minDate.setDate(this.minDate.getDate() + 1);
  }

  ngOnInit(): void {
    this.service.setRouterUrl("/Annuncio")
    
    this.nuovoAnnuncioForm=new FormGroup({
      titolo: new FormControl(null,Validators.required),
      zona: new FormControl(null,Validators.required),
      ambito: new FormControl(null,Validators.required),
      dataScadenza: new FormControl(null,Validators.required),
      img: new FormControl,
      descrizione: new FormControl,

    })

    this.province=this.service.getProvince()
    this.ambiti=this.service.getAmbiti()
  }

  onSubmit(): void{
    
    this.cardAnnuncio=[]
   

    let cardAnnuncio : Annunci={

     
      img: this.url,
      titolo:this.nuovoAnnuncioForm.value.titolo ,
      descrizione:this.nuovoAnnuncioForm.value.descrizione,
      ambito: this.nuovoAnnuncioForm.value.ambito,
      zona: this.nuovoAnnuncioForm.value.zona,

    }

    this.cardAnnuncio.push(cardAnnuncio)

    this.container?.nativeElement.classList.add("anteprimaAnnuncioActive")
  }
  eliminaAnteprima(){

    console.log("ciao")
    this.container?.nativeElement.classList.remove("anteprimaAnnuncioActive")

  }
  onSelectFile(e: any): void {

    if(e.target.files){
      var reader = new FileReader();
      reader.readAsDataURL(e.target.files[0]);
      reader.onload=(event:any)=>{
        this.url=event.target.result;
      }

    }

  }
  clickArrow() : void{

  }

}
