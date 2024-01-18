import { Component, OnInit } from '@angular/core';
import { ServizioAnnunciService } from '../../../service/servizio-annunci.service';
import { faArrowDown } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-recensioni',
  templateUrl: './recensioni.component.html',
  styleUrl: './recensioni.component.css'
})
export class RecensioniComponent implements OnInit {

  constructor (private service : ServizioAnnunciService){}
  starImg: string = "../../assets/star.png";
  annunci:any
  propostaAccettata: any
  ArrowDown=faArrowDown
  starsClicked: boolean = false

  ngOnInit(): void {
    this.annunci=this.service.getAnnunci();
  }

  setPropostaAccettata(id :string){

    this.propostaAccettata=this.service.getPropostaAccettataByid(id)
    
  }

   
  getPropostaAccettataImg(){

    console.log(this.propostaAccettata)
    if(this.propostaAccettata){

      return this.propostaAccettata.img
    }
  }
  getPropostaAccettataNome(){

    
    if(this.propostaAccettata){

      return this.propostaAccettata.username
    }
  }

  setStarsClicked(voto : number){

    if(this.starsClicked){
      this.starsClicked=false
      voto=0
      console.log(voto)
    }
    else
    this.starsClicked=true
    console.log(voto)
  }
  isStarsClicked(){

    return this.starsClicked
  }

  
}
