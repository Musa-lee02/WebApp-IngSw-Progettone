import { AfterViewChecked, AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons';
import Swal from 'sweetalert2';
import { faPencil } from '@fortawesome/free-solid-svg-icons';




 
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

  annunci:any 
  proposte:any
  ambitoForm:FormGroup
  arrowLeft=faArrowLeft
  minDate = new Date();


  constructor(private service: ServizioAnnunciService){

    //this.minDate.setDate(this.minDate.getDate() + 1);
  }
  ngAfterViewInit(): void {

  }
  ngOnInit(): void {


    this.service.setRouterUrl("/Annuncio")
    
    this.ambitoForm=new FormGroup({
      foto: new FormControl(null,Validators.required),
      provincia: new FormControl(null,Validators.required),
      ambito: new FormControl(null,Validators.required),
      
    })

    this.annunci=this.service.getAnnunci()
    this.proposte=this.service.getProposte()

    console.log(this.annunci)
    console.log(this.proposte)
  }

  ngOnDestroy(): void {
    if (this.ambitoForm.valid) {
      Swal.fire("Annuncio creato con successo")
    }
  }

  ngAfterViewChecked(): void {
   
  }  

  onSubmit(): void{
    

  }
  onSelectFile(e: Event): void {
  }
  clickArrow() : void{
    console.log("click")
  }
  isAutenticato(){
    return this.service.isAutenticato()
  }
}