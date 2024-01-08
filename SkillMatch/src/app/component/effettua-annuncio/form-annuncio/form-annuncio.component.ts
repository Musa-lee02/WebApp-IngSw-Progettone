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

  province: String[] = ['Cosenza', 'Reggio Calabria', 'Vibo Valentia', 'Catanzaro', 'Crotone',
              'Napoli', 'Salerno', 'Avellino', 'Benevento', 'Caserta', 'Potenza', 'Matera' ]

  ambiti: String[] = ['Cucina', 'Sport', 'Musica', 'Arte', 'Scienza', 'Informatica', 'Letteratura', 'Cinema', 'Teatro', 'Moda', 'Altro']

  minDate: Date;
  url = 'https://www.felicinabiorci.com/wp/wp-content/uploads/2019/01/diet-food-macro-111130.jpg'
  ambitoForm:FormGroup
  arrowLeft=faArrowLeft
  
  constructor(private service: ServizioAnnunciService){
    this.minDate = new Date();

    //this.minDate.setDate(this.minDate.getDate() + 1);
  }

  ngOnInit(): void {
    this.service.setRouterUrl("/Annuncio")
    
    this.ambitoForm=new FormGroup({
      nomeAnnuncio: new FormControl(null,Validators.required),
      zonaAnnuncio: new FormControl(null,Validators.required),
      ambitoAnnucnio: new FormControl(null,Validators.required),
      dataScadenza: new FormControl(null,Validators.required),
    })
  }

  onSubmit(): void{

  }
  onSelectFile(e: Event): void {
  }
  clickArrow() : void{

  }

}
