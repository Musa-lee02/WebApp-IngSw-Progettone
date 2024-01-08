import { Injectable, OnInit } from '@angular/core';


type Annunci ={
  
  img: string
  descrizione: string
  ambito: string
  titolo: string
  id:string

}

type Proposte={
  nome:string
  img: string
  descrizione:string
  id:string
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
    titolo:"pincher da mangiare",
    descrizione:"The purina is a small breed of dog, originating from Germany. The breed's earliest ancestors may have included the German Pinscher mixed with Italian greyhounds and dachshunds.",
    ambito:"Edilizia",
    id:"12"
  },
  {
    img:'https://material.angular.io/assets/img/examples/shiba2.jpg',
    titolo:"Shiba inu da vendere",
    descrizione:"   The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from Japan."+
      "A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was originally"+
      "bred for hunting.",
    ambito:"Tecnologia",
    id:"13"
  },
  {
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    titolo:"pincher da mangiare",
    descrizione:"The purina is a small breed of dog, originating from Germany. The breed's earliest ancestors may have included the German Pinscher mixed with Italian greyhounds and dachshunds.",
    ambito:"Edilizia",
    id:"12"
  },
  {
    img:'https://material.angular.io/assets/img/examples/shiba2.jpg',
    titolo:"Shiba inu da vendere",
    descrizione:"   The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from Japan."+
      "A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was originally"+
      "bred for hunting.",
    ambito:"Tecnologia",
    id:"13"
  },
  {
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    titolo:"pincher da mangiare",
    descrizione:"The purina is a small breed of dog, originating from Germany. The breed's earliest ancestors may have included the German Pinscher mixed with Italian greyhounds and dachshunds.",
    ambito:"Edilizia",
    id:"12"
  },
  {
    img:'https://material.angular.io/assets/img/examples/shiba2.jpg',
    titolo:"Shiba inu da vendere",
    descrizione:"   The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from Japan."+
      "A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was originally"+
      "bred for hunting.",
    ambito:"Tecnologia",
    id:"13"
  },
  {
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    titolo:"pincher da mangiare",
    descrizione:"The purina is a small breed of dog, originating from Germany. The breed's earliest ancestors may have included the German Pinscher mixed with Italian greyhounds and dachshunds.",
    ambito:"Edilizia",
    id:"12"
  },
  {
    img:'https://material.angular.io/assets/img/examples/shiba2.jpg',
    titolo:"Shiba inu da vendere",
    descrizione:"   The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from Japan."+
      "A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was originally"+
      "bred for hunting.",
    ambito:"Tecnologia",
    id:"13"
  },
  {
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    titolo:"pincher da mangiare",
    descrizione:"The purina is a small breed of dog, originating from Germany. The breed's earliest ancestors may have included the German Pinscher mixed with Italian greyhounds and dachshunds.",
    ambito:"Edilizia",
    id:"12"
  },
  {
    img:'https://material.angular.io/assets/img/examples/shiba2.jpg',
    titolo:"Shiba inu da vendere",
    descrizione:"   The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from Japan."+
      "A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was originally"+
      "bred for hunting.",
    ambito:"Tecnologia",
    id:"13"
  },
  {
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    titolo:"pincher da mangiare",
    descrizione:"The purina is a small breed of dog, originating from Germany. The breed's earliest ancestors m",
    ambito:"Edilizia",
    id:"12"
  },
  {
    img:'https://material.angular.io/assets/img/examples/shiba2.jpg',
    titolo:"Shiba inu da vendere",
    descrizione:"   The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from Japan."+
      "A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was originally"+
      "bred for hunting.",
    ambito:"Tecnologia",
    id:"13"
  }

  ]

  proposte : Proposte[]=[{

    nome:"pippo",
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    descrizione:"cecedcc",
    id:"13"
    
  },
  {
    nome:"giacom",
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    descrizione:"ceceaxdc",
    id:"12"
  },
  {
    nome:"mll",
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    descrizione:"cecedcc",
    id:"12"
  },
  {
    nome:"luigio",
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    descrizione:"cecedcc",
    id:"13"
  },
  {
    nome:"maswso",
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    descrizione:"cecedcc",
    id:"13"
  },
]

ambiti: String[] = ['Cucina', 'Sport', 'Musica', 'Arte', 'Scienza', 'Informatica', 'Letteratura', 'Cinema', 'Teatro', 'Moda', 'Altro']

  province: String[] = ['Cosenza', 'Reggio Calabria', 'Vibo Valentia', 'Catanzaro', 'Crotone',
              'Napoli', 'Salerno', 'Avellino', 'Benevento', 'Caserta', 'Potenza', 'Matera' ]

  

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
  getAmbiti(){
    return this.ambiti;
  }
  getProvince(){
    return this.province;
  }

  getProposte(){
    return this.proposte
  }
  getProposteById(id: string){

    let proposteFiltrate:Proposte[]=[]
    for(const proposta of this.proposte){

      if(proposta.id===id){
      
        proposteFiltrate.push(proposta)
      }
    }
    return proposteFiltrate;

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
