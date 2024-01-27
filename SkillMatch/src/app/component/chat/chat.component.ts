import {AfterContentChecked, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons';
import { ActivatedRoute } from '@angular/router';
import {ChatService} from "../../service/ChatService";
import {BackEndService} from "../../service/BackEndService";
import {Chat} from "../../model/Chat";
import {Cliente} from "../../model/Cliente";
import {Lavoratore} from "../../model/Lavoratore";
import {Annuncio} from "../../model/Annuncio";



@Component({
  selector: 'app-effettua-annuncio',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']//,'../profilo/profilo.component.css']
})

export class ChatComponent implements OnInit, AfterContentChecked{

  @ViewChild('rightPart') dashboard :ElementRef

  minDate: Date;

  url = 'https://www.felicinabiorci.com/wp/wp-content/uploads/2019/01/diet-food-macro-111130.jpg'

  province: String[] = ['Cosenza', 'Reggio Calabria', 'Vibo Valentia', 'Catanzaro', 'Crotone',
    'Napoli', 'Salerno', 'Avellino', 'Benevento', 'Caserta', 'Potenza', 'Matera' ]

  ambiti: String[] = ['Cucina', 'Sport', 'Musica', 'Arte', 'Scienza', 'Informatica', 'Letteratura', 'Cinema', 'Teatro', 'Moda', 'Altro']

  annunci:Annuncio[]
  proposte:any
  lavoratore:any
  ambitoForm:FormGroup
  arrowLeft=faArrowLeft
  primoCaricamento:boolean=true
  entita : string
  annunciCaricati=false

    chat: Chat


  constructor(private service: ServizioAnnunciService, private route : ActivatedRoute, private chatService: ChatService, private backEndService : BackEndService){
    this.minDate = new Date();

    //this.minDate.setDate(this.minDate.getDate() + 1);
  }

  ngOnInit(): void {


   this.entita= localStorage.getItem("scelta")!

    this.ambitoForm=new FormGroup({
      nomeAnnuncio: new FormControl(null,Validators.required),
      zonaAnnuncio: new FormControl(null,Validators.required),
      ambitoAnnucnio: new FormControl(null,Validators.required),
      dataScadenza: new FormControl(null,Validators.required),
    })


    if (this.entita==="cliente") {
      this.backEndService.getAnnunciWithChat().subscribe(
          response => {
            console.log(response)
            this.annunci = response
          }, (error) => {
            console.log()
          });
    }
    if (this.entita==="lavoratore") {
      this.backEndService.getAnnunciWithToken().subscribe(
          response => {
            console.log(response)
            this.annunci = response
          }, (error) => {
            console.log()
          });
    }



  }
    ngAfterContentChecked(): void {
        if(this.annunci && !this.annunciCaricati) {
            for (let annuncio of this.annunci) {

                this.getLavoratoriByIdAnnuncio(annuncio.id)
            }
            this.annunciCaricati=true
        }

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


  setChatByUsernameAndId(destinatario : Lavoratore | Cliente, annuncio : Annuncio){

    if(localStorage.getItem("scelta")==="cliente") {
        this.chat = {
            annuncio: annuncio,
            cliente: JSON.parse(localStorage.getItem("utente")!),
            lavoratore: <Lavoratore>destinatario

        }
    }else{
        this.chat = {
            annuncio: annuncio,
            cliente: destinatario,
            lavoratore : JSON.parse(localStorage.getItem("utente")!)

        }
    }




  }

  getChat(){
    this.primoCaricamento=true
    return this.chat
  }

  getLavoratoriByIdAnnuncio(id: number){

    console.log(id)
    this.chatService.getLavoratoriByIdAnnuncio(id).subscribe(data =>{

      this.proposte=data;
    })

  }
  getAnnunciByUsernameLavoratore(){

    //this.annunci=this.service.getAnnunciByUsernameLavoratore("maswso")

  }


}
