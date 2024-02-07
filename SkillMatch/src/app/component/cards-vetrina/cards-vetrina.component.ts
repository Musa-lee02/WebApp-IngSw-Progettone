import { AfterContentChecked, AfterContentInit, AfterViewChecked, Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import {ActivatedRoute, Router} from '@angular/router';
import { faStar } from '@fortawesome/free-solid-svg-icons';
import { library } from '@fortawesome/fontawesome-svg-core';
import {AnnuncioService} from "../../service/AnnuncioService";
import {Annuncio} from "../../model/Annuncio";
import { BackEndService } from '../../service/BackEndService';
import {Province} from "../../model/Province";
import {Lavoratore} from "../../model/Lavoratore";
import {Cliente} from "../../model/Cliente";
import {Ambito} from "../../model/Ambito";


library.add(faStar);

@Component({
  selector: 'app-cards-vetrina',
  templateUrl: './cards-vetrina.component.html',
  styleUrl: './cards-vetrina.component.css'
})
export class CardsVetrinaComponent implements OnInit, AfterViewChecked, AfterContentInit{

  @Input('annunci') annunci : Annuncio[];

  ambito: string;
  starImg: string = "../../assets/star.jpg";
  isHome:boolean=false;
  annunciConsigliati: Annuncio[] = [];
  constructor(private route : ActivatedRoute,
              private servizioAnnunci: ServizioAnnunciService,
              private annuncioService: AnnuncioService,
              private backEndService: BackEndService,
              private router: Router
  ){}

  sortAnnunciConsigliati(){
    const utenteLogged = localStorage.getItem("utente");
    const utenteL: Lavoratore | Cliente = JSON.parse(utenteLogged!);
    const provincia = utenteL.provincia;

    // Ottenere gli ambiti dell'utente, se presenti
    if ('ambiti' in utenteL) { // se l'utente Ã¨ un lavoratore
      const ambitiUtente  = utenteL.ambiti.map((ambito: any) => ambito.nome);
      // Ordinare gli annunci in base agli ambiti in comune con l'utente
      for (let i = 0; i < ambitiUtente.length; i++) {
        const index = this.annunci.findIndex(a => a.ambito.nome === ambitiUtente[i]);

        if (index !== -1) {
          const tipo = this.annunci[index].ambito.nome;

          for(let j = index; j < this.annunci.length; j++){
            if (this.annunci[j].ambito.nome === tipo) {
              const annuncioRimosso = this.annunci.splice(j, 1)[0];
              annuncioRimosso.consigliato = true;
              this.annunci.unshift(annuncioRimosso);
              this.annunciConsigliati.unshift(annuncioRimosso);
            }
          }
        }
      }return
    }
    let index = this.annunci.findIndex(a => a.provinciaAnnuncio === provincia);
    // Se trovi l'indice, sposta l'elemento corrispondente all'inizio dell'array
    if (index !== -1) {
      for(let i = 0; i < this.annunci.length; i++){
        if (this.annunci[i].provinciaAnnuncio === provincia) {
          const annuncioRimosso = this.annunci.splice(i, 1)[0];
          annuncioRimosso.consigliato = true;
          this.annunci.unshift(annuncioRimosso);
          this.annunciConsigliati.unshift(annuncioRimosso);
        }
      }
    }
  }

  ambitiDisponibili(ambiti : Ambito[], annunci : Annuncio[]){
    let ambitiDisponibili : Ambito[] = [];
    for(let i = 0; i < ambiti.length; i++){
      if(annunci.findIndex(a => a.ambito.nome === ambiti[i].nome) !== -1){
        ambitiDisponibili.push(ambiti[i]);
      }
    }
    return ambitiDisponibili;

  }
  ngAfterContentChecked(): void {

  }
  getSizeAnnunci(){
    return this.annunci.length
  }


  ngAfterViewChecked(): void {
  }

  ngOnInit(): void {
    if(this.router.url==="/Home"){
      this.isHome=true
      //this.sortAnnunciConsigliati()
    }
    console.log("OnInit: " + this.annunci)
  }
  getProvincia(provincia : Province | string){

    return (provincia as Province).nome;
  }

  initAnnunci(){
    if(this.route.snapshot.paramMap.get('ambito')){

      this.ambito=this.route.snapshot.paramMap.get('ambito')!;
      //this.annunci=this.servizioAnnunci.getAnnunciByAmbitoEZona(); //TODO

    }
    else {
      this.backEndService.getAllAnnunci().subscribe(
        response => {
          this.annunci = response

        }, (error) => {
          console.log("errore. da modificare(?)")
        });

    }
  }

  inviaCandidatura(annuncio: Annuncio){

    this.annuncioService.inviaCandidatura(annuncio)
  }

  ngAfterContentInit(): void {

  }

}
