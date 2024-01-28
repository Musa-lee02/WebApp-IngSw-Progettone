import { AfterContentChecked, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faArrowDown } from '@fortawesome/free-solid-svg-icons';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import {BackEndService} from "../../service/BackEndService";
import { Lavoratore } from '../../model/Lavoratore';
import { Cliente } from '../../model/Cliente';


@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent implements AfterContentChecked{

  logoImg:string = "../../assets/Skill.png"

  constructor(private router: Router, private service: ServizioAnnunciService, private backEndService: BackEndService){}


  arrowIcon= faArrowDown
  title = 'SkillMatch';
  sizeAnnunci:number=0;
  isHome:boolean=false;
  isEsplora:boolean=false;
  entita: string | null


  linkHome:String="http://localhost:4200/Home"

  ngAfterViewChecked(): void {
  }


  ngAfterContentChecked(): void {

    if(this.router.url==="/Home"){
      this.isHome=true
    }

    else{
      this.isHome=false
    }
    this.entita=localStorage.getItem("scelta")

  }




  getRange(sizeAnnunci: number){
    return this.sizeAnnunci
  }


  switch(){
    if(this.entita==='lavoratore')
    {
      localStorage.setItem("scelta","cliente")
      this.entita='cliente'
    }
    else{
        localStorage.setItem("scelta","lavoratore")
        this.entita='lavoratore'
    }
  }

  isAutenticato(){

    return this.backEndService.isAuthenticated()
  }
  _isEsplora(){
    return this.isEsplora
  }
  doLogout(){


    this.backEndService.removeToken()

  }

  getPicProfile(){
    return this.getUtente().imgProfilo;
  }


  getUtente() : Lavoratore | Cliente{
    var utenteLogged = localStorage.getItem("utente");
    return JSON.parse(utenteLogged!);
  }


}
