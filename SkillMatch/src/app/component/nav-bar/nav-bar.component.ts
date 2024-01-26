import { AfterContentChecked, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faArrowDown } from '@fortawesome/free-solid-svg-icons';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import {BackEndService} from "../../service/BackEndService";


@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent implements OnInit, AfterContentChecked{

  logoImg:string = "../../assets/Skill.png"

  constructor(private router: Router, private service: ServizioAnnunciService, private backEndService: BackEndService){}


  arrowIcon= faArrowDown
  title = 'SkillMatch';
  sizeAnnunci:number=0;
  isHome:boolean=false;
  isEsplora:boolean=false;


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

  }
  ngOnInit(): void {

  }
  setSfondo(){

    this.getRouterUrl()
  }

  getRouterUrl(){

    return this.service.getRouterUrl()
  }

  getRange(sizeAnnunci: number){
    return this.sizeAnnunci
  }

  setLavoratore(bool: boolean){

    this.service.setlavoratoreBool(bool);

  }
  setDoingAccesso(bool:boolean){
    this.service.setAutenticato(false)
    this.service.setDoingAccesso(bool);
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
  skipAutentication(bool: boolean){

    this.service.setSkipAutentication(bool)
  }

  getPicProfile(){

  }


}
