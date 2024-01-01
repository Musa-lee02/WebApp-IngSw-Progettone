import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { faArrowDown } from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent {

  constructor(private router: Router){}
  
  arrowIcon= faArrowDown
  title = 'SkillMatch';
  sizeAnnunci:number=0;
  isHome:boolean=false;
  linkHome:String="http://localhost:4200/Home"
  
  getRange(sizeAnnunci: number){
    return this.sizeAnnunci
  }

    
  ngAfterViewChecked(): void {
    
    console.log(this.router.url)
    if(this.router.url==="/Home"){
      this.isHome=false
    }
    else{
      this.isHome=true
    }
  }

}
