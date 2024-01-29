import { AfterViewChecked, AfterViewInit, Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons';
import Swal from 'sweetalert2';
import { faPencil } from '@fortawesome/free-solid-svg-icons';
import { BackEndService } from '../../service/BackEndService';

@Component({
  selector: 'app-effettua-annuncio',
  templateUrl: './effettua-annuncio.component.html',
  styleUrls: ['./effettua-annuncio.component.css']//,'../profilo/profilo.component.css']
})

export class EffettuaAnnuncioComponent implements OnInit, AfterViewInit, AfterViewChecked, OnDestroy{

  url = 'https://www.felicinabiorci.com/wp/wp-content/uploads/2019/01/diet-food-macro-111130.jpg'

  faPencili=faPencil

  province: String[] = ['Cosenza', 'Reggio Calabria', 'Vibo Valentia', 'Catanzaro', 'Crotone',
              'Napoli', 'Salerno', 'Avellino', 'Benevento', 'Caserta', 'Potenza', 'Matera' ]

  ambiti: String[] = ['Cucina', 'Sport', 'Musica', 'Arte', 'Scienza', 'Informatica', 'Letteratura', 'Cinema', 'Teatro', 'Moda', 'Altro']

  @ViewChild('container') container : ElementRef

  annunci:any
  proposte:any
  ambitoForm:FormGroup
  arrowLeft=faArrowLeft
  minDate = new Date();
  annuncioScelto: any
  isModifica: boolean=false;

  constructor(private service: ServizioAnnunciService, private backEndService: BackEndService){

    //this.minDate.setDate(this.minDate.getDate() + 1);
  }
  ngAfterViewInit(): void {

  }
  ngOnInit(): void {




    this.ambitoForm=new FormGroup({
      foto: new FormControl(null,Validators.required),
      provincia: new FormControl(null,Validators.required),
      ambito: new FormControl(null,Validators.required),

    })

    this.backEndService.getAnnunciWithToken().subscribe(
      response => {
        this.annunci = response
    }, (error) => {

    });



  }

  ngOnDestroy(): void {
    if (this.ambitoForm.valid) {
      Swal.fire("Annuncio creato con successo")
    }
  }

  ngAfterViewChecked(): void {

  }

  setAnnuncioScelto(annuncio: any){

    this.annuncioScelto=annuncio
    this.isModifica=true
    this.container.nativeElement.classList.add("annuncioSceltoActiveContainer")
    this.container.nativeElement.classList.remove("matita")
    this.container.nativeElement.classList.remove("linguettaRovesciata")


  }
  crea(){
    this.container.nativeElement.classList.add("matita")
    this.container.nativeElement.classList.remove("annuncioSceltoActiveContainer")
    this.container.nativeElement.classList.remove("linguettaRovesciata")
  }
  chiudiScheda(){

    this.container.nativeElement.classList.add("linguettaRovesciata")
    this.container.nativeElement.classList.remove("matita")
    this.container.nativeElement.classList.remove("annuncioSceltoActiveContainer")

  }
  onSelectFile(e: Event): void {
  }
  clickArrow() : void{

  }

}
