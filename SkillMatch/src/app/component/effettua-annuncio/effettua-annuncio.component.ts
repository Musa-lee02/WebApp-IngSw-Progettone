import { Component } from '@angular/core';
import { ServizioAnnunciService } from '../../servizio-annunci.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-effettua-annuncio',
  templateUrl: './effettua-annuncio.component.html',
  styleUrls: ['./effettua-annuncio.component.css']//,'../profilo/profilo.component.css']
})

export class EffettuaAnnuncioComponent{

  url = 'https://www.felicinabiorci.com/wp/wp-content/uploads/2019/01/diet-food-macro-111130.jpg'

  province: String[] = ['Cosenza', 'Reggio Calabria', 'Vibo Valentia', 'Catanzaro', 'Crotone',
              'Napoli', 'Salerno', 'Avellino', 'Benevento', 'Caserta', 'Potenza', 'Matera' ]

  ambiti: String[] = ['Cucina', 'Sport', 'Musica', 'Arte', 'Scienza', 'Informatica', 'Letteratura', 'Cinema', 'Teatro', 'Moda', 'Altro']

  ambitoForm:FormGroup
  arrowLeft=faArrowLeft
  
  constructor(private service: ServizioAnnunciService){

  }

  ngOnInit(): void {
    this.ambitoForm=new FormGroup({
      nomeAnnuncio: new FormControl(null,Validators.required),
      zonaAnnuncio: new FormControl(null,Validators.required),
      ambitoAnnucnio: new FormControl(null,Validators.required),
    })
  }
  onSubmit(): void{

  }
  onSelectFile(e: Event): void {
  }
  clickArrow() : void{

  }
  
  isAutenticato(){
    return this.service.isAutenticato()
  }
}