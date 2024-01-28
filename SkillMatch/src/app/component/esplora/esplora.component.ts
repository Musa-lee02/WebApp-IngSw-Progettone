import {AfterContentChecked, Component, OnChanges, OnDestroy, OnInit, SimpleChanges} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import {AnnuncioService} from "../../service/AnnuncioService";

@Component({
  selector: 'app-esplora',
  templateUrl: './esplora.component.html',
  styleUrl: './esplora.component.css'
})
export class EsploraComponent implements OnInit, OnDestroy, AfterContentChecked, OnChanges {


  ambito: string
  zona: string
    constructor(public service: ServizioAnnunciService,
                private route: ActivatedRoute,
                private annunciService : AnnuncioService,
                private router: Router){

    }
  ngAfterContentChecked(): void {


    if(this.service.buttonSearchClickedBool){


      //this.annunci=this.service.getAnnunciByAmbitoEZona();
      this.service.buttonSearchClickedBool=false
    }
  }

  ngOnInit(): void {


  }

  ngOnChanges(changes: SimpleChanges): void {

    this.ambito=this.route.snapshot.paramMap.get("ambito")!
    this.zona=this.route.snapshot.paramMap.get("zona")!
    this.annunciService.getAnnunciByAmbitoEZona(this.ambito ,this.zona ).subscribe(data=>{

      //this.annunci=data
    })

  }
  ngOnDestroy(): void {

    //this.annunci = []
  }



}
