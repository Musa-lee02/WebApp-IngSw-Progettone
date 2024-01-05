import { Injectable, OnInit } from '@angular/core';


type Annunci ={

  img: string
  descrizione: string
  ambito: string

}
@Injectable({
  providedIn: 'root'
})

export class ServizioAnnunciService implements OnInit {


  doingAccesso: boolean=false;
  private lavoratoreBool :boolean=false;
  private autenticato: boolean=false;

  url:string;



  annunci : Annunci[]=[{
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    descrizione:"cecedcc",
    ambito:"Edilizia",
  },
    {
      img:'https://material.angular.io/assets/img/examples/shiba2.jpg',
      descrizione:"   The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from Japan."+
        "A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was originally"+
        "bred for hunting.",
      ambito:"Tecnologia"
    }
  ]


  constructor() { }
  ngOnInit(): void {
    console.log(this.autenticato)
    this.autenticato=false

  }


  setRouterUrl(url : string){
    this.url=url
  }

  getRouterUrl(){
    return this.url;
  }

  annunciGetSize(){
    return this.annunci.length
  }
  getAnnunci(){

    return this.annunci;
  }
  getAnnunciByAmbito(ambito: string) : Annunci[]{

    let inHome=true
    let annunciFiltrati:Annunci[]=[]
    for(const annuncio of this.annunci){

      if(annuncio.ambito===ambito){
        inHome=false;

        annunciFiltrati.push(annuncio)
      }
    }

    if(annunciFiltrati.length>0){
      return annunciFiltrati
    }


    return []
  }

  isLavoratore(){
   
    return this.lavoratoreBool;
  }
  setlavoratoreBool(bool: boolean){

    this.lavoratoreBool=bool;

  }

  setAutenticato(bool: boolean){
   
    this.autenticato=bool;
    
  }
  isAutenticato(){
    
    return this.autenticato
  }

}
