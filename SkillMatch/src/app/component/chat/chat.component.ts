import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons';
import { ActivatedRoute } from '@angular/router';
import {ChatService} from "../../service/ChatService";


@Component({
  selector: 'app-effettua-annuncio',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']//,'../profilo/profilo.component.css']
})

export class ChatComponent implements OnInit{

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
  entita : string


  constructor(private service: ServizioAnnunciService, private route : ActivatedRoute, private chatService: ChatService){
    this.minDate = new Date();

    //this.minDate.setDate(this.minDate.getDate() + 1);
  }

  ngOnInit(): void {

    if(this.route.snapshot.paramMap.get('Entita')){

      this.entita=this.route.snapshot.paramMap.get('Entita')!;
      if(this.entita==="Cliente")
        this.entita=="Cliente";
      if(this.entita==="Lavoratore")
        this.entita=="Lavoratore";
    }

    this.ambitoForm=new FormGroup({
      nomeAnnuncio: new FormControl(null,Validators.required),
      zonaAnnuncio: new FormControl(null,Validators.required),
      ambitoAnnucnio: new FormControl(null,Validators.required),
      dataScadenza: new FormControl(null,Validators.required),
    })

    this.annunci=this.service.getAnnunci()

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

    console.log(username,id)
    return this.service.setChatByUsernameAndId(username, id);//cancella



  }

  getChat(){
    this.primoCaricamento=true

    return this.service.getChat()
  }

  getLavoratoriByIdAnnuncio(id: string){


    this.proposte=this.service.getLavoratoriByIdAnnuncio(id);

  }
  getAnnunciByUsernameLavoratore(){
    this.annunci=this.service.getAnnunciByUsernameLavoratore("maswso")
  }
}
