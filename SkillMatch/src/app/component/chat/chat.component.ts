import { Component, ElementRef, ViewChild } from '@angular/core';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-effettua-annuncio',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']//,'../profilo/profilo.component.css']
})

export class ChatComponent{

  @ViewChild('rightPart') dashboard :ElementRef
 
  minDate: Date;

  url = 'https://www.felicinabiorci.com/wp/wp-content/uploads/2019/01/diet-food-macro-111130.jpg'

  province: String[] = ['Cosenza', 'Reggio Calabria', 'Vibo Valentia', 'Catanzaro', 'Crotone',
              'Napoli', 'Salerno', 'Avellino', 'Benevento', 'Caserta', 'Potenza', 'Matera' ]

  ambiti: String[] = ['Cucina', 'Sport', 'Musica', 'Arte', 'Scienza', 'Informatica', 'Letteratura', 'Cinema', 'Teatro', 'Moda', 'Altro']

  annunci:any 
  proposte:any
  lavoratore:any
  ambitoForm:FormGroup
  arrowLeft=faArrowLeft
  primoCaricamento:boolean=true
  
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

    this.annunci=this.service.getAnnunci()
    this.proposte=this.service.getProposte()


  }

  onSubmit(): void{

  }
  onSelectFile(e: Event): void {
  }
  clickArrow() : void{

  }

  visualizzaChatResponsive(){

    console.log("swss")
    this.dashboard.nativeElement.classList.add('visualizzaChat')

  }
  rimuoviChat(){
    this.dashboard.nativeElement.classList.remove('visualizzaChat')
  }
  
  isAutenticato(){
    return this.service.isAutenticato()
  }


  setChatByUsernameAndId(username : string, id : string){

    
    return this.service.setChatByUsernameAndId(username, id);//cancella

  }

  getChat(){
    this.primoCaricamento=true
    console.log(this.primoCaricamento)
    return this.service.getChat()
  }
}
