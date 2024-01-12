import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';

@Component({
  selector: 'app-esplora',
  templateUrl: './esplora.component.html',
  styleUrl: './esplora.component.css'
})
export class EsploraComponent implements OnInit, OnDestroy {

  ambito : string
  annunci : any
    constructor(private service: ServizioAnnunciService, private route: ActivatedRoute){

    }
  
  ngOnInit(): void { 
    
    if(this.route.snapshot.paramMap.get('ambito')){

      this.ambito=this.route.snapshot.paramMap.get('ambito')!;
      this.annunci=this.service.getAnnunciByAmbitoEZona();
    }
    this.annunci=this.service.getAnnunciByAmbitoEZona();
  }
  ngOnDestroy(): void {
    this.service.setSelectAmbito('')
    this.service.setSelectZona('')
    this.annunci = []
  }
  
}
