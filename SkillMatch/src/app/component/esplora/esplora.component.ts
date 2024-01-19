import { AfterContentChecked, Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';

@Component({
  selector: 'app-esplora',
  templateUrl: './esplora.component.html',
  styleUrl: './esplora.component.css'
})
export class EsploraComponent implements OnInit, OnDestroy, AfterContentChecked {

  ambito : string
  annunci : any
    constructor(private service: ServizioAnnunciService, private route: ActivatedRoute, private router: Router){

    }
  ngAfterContentChecked(): void {
    
   
    if(this.service.buttonSearchClickedBool){

      
      this.annunci=this.service.getAnnunciByAmbitoEZona();
      this.service.buttonSearchClickedBool=false
    }
  }
  
  ngOnInit(): void { 
    
    
    
    console.log(this.annunci)
  }
  ngOnDestroy(): void {
    this.service.setSelectAmbito('')
    this.service.setSelectZona('')
    this.annunci = []
  }
  
}
