import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ServizioAnnunciService } from '../../../service/servizio-annunci.service';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons';

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
  
  constructor(private service: ServizioAnnunciService){
    this.minDate = new Date();

    //this.minDate.setDate(this.minDate.getDate() + 1);
  }

  ngOnInit(): void {
    this.service.setRouterUrl("/Annuncio")
    
    this.nuovoAnnuncioForm=new FormGroup({
      titoloAnnuncio: new FormControl(null,Validators.required),
      zonaAnnuncio: new FormControl(null,Validators.required),
      ambitoAnnucnio: new FormControl(null,Validators.required),
      dataScadenza: new FormControl(null,Validators.required),

    })

    this.province=this.service.getProvince()
    this.ambiti=this.service.getAmbiti()
  }

  onSubmit(): void{
    console.log(this.nuovoAnnuncioForm.valid)
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
