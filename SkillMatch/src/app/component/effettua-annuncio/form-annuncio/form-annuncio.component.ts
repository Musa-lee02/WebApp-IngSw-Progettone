import { AfterViewChecked, Component, ElementRef, Input, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ServizioAnnunciService } from '../../../service/servizio-annunci.service';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons';
import {BackEndService} from "../../../service/BackEndService";
import { Annuncio } from '../../../model/Annuncio';
import { Ambito } from '../../../model/Ambito';
import {Province} from "../../../model/Province";
import {HttpClient} from "@angular/common/http";



type Annunci ={


  img: string
  descrizione: string
  ambito: string
  titolo: string
  provinciaAnnuncio:string
  dataDiScadenza: Date

}
@Component({
  selector: 'app-form-annuncio',
  templateUrl: './form-annuncio.component.html',
  styleUrls: ['./form-annuncio.component.css']
})


export class FormAnnuncioComponent implements AfterViewChecked{

  ambiti : Ambito[]
  province : Province[]
  minDate: Date;
  url = '../../assets/imagedefault.avif'
  nuovoAnnuncioForm:FormGroup
  arrowLeft=faArrowLeft
  @ViewChild('container') container: ElementRef | undefined;
  @Input('annuncio') annuncioScelto: any
  @Input ('isModifica') isModifica: boolean

  cardAnnuncio: any

  annuncio: Annuncio
  image!: File

  constructor(private service: ServizioAnnunciService, private backEndService: BackEndService, private httpClient : HttpClient){
    this.minDate = new Date();

    //this.minDate.setDate(this.minDate.getDate() + 1);
  }
  ngAfterViewChecked(): void {

  }


  ngOnInit(): void {

    this.nuovoAnnuncioForm=new FormGroup({
      titolo: new FormControl(null,Validators.required),
      provinciaAnnuncio: new FormControl(null,Validators.required),
      ambito: new FormControl(null,Validators.required),
      dataScadenza: new FormControl(null,Validators.required),
      img: new FormControl,
      descrizione: new FormControl,

    })


    this.backEndService.getAmbiti().subscribe(
        data => {
          this.ambiti = data
          console.log("tutti gli ambiti sono: ")
          this.ambiti.forEach((ambito, index) => {

          });
        }
    )

    this.httpClient.get<Province[]>('http://mobilio.altervista.org').subscribe( data =>
        {
          console.log(data)
          this.province=data
        }
    )
  }

  onSubmit(){

    this.cardAnnuncio=[]
    let cardAnnuncio : Annunci={


      img: this.url,
      titolo:this.nuovoAnnuncioForm.value.titolo ,
      descrizione:this.nuovoAnnuncioForm.value.descrizione,
      ambito: this.nuovoAnnuncioForm.value.ambito,
      provinciaAnnuncio: this.nuovoAnnuncioForm.value.provinciaAnnuncio,
      dataDiScadenza: this.nuovoAnnuncioForm.value.dataScadenza

    }
    this.cardAnnuncio.push(cardAnnuncio)
    console.log(cardAnnuncio)
    this.container?.nativeElement.classList.add("anteprimaAnnuncioActive")
  }

  inserisciAnnuncio(): void{

    if(this.nuovoAnnuncioForm.valid){
      const ambito: Ambito = {
        id: this.nuovoAnnuncioForm.value.ambito.id,
        nome: this.nuovoAnnuncioForm.value.ambito.nome
      };
      const annuncio: Annuncio = this.nuovoAnnuncioForm.value


      annuncio.provinciaAnnuncio = (annuncio.provinciaAnnuncio as unknown as Province).nome;

      annuncio.ambito = ambito

      console.log("annuncio.provinciaAnnuncio"+annuncio.provinciaAnnuncio)


      this.backEndService.insertAnnuncio(annuncio, this.image).subscribe(
          (response) => {
            console.log("response è: " + response)
            console.log("Ok.")
          },
          (error) => {
            console.log("error è: " + error)
            console.log("errore.")
          });




    }


  }
  eliminaAnteprima(){

    console.log("ciao")
    this.container?.nativeElement.classList.remove("anteprimaAnnuncioActive")

  }
  onSelectFile(e: any): void {

    if(e.target.files){
      this.image = e.target.files[0]
    }

  }
  clickArrow() : void{

  }

}
