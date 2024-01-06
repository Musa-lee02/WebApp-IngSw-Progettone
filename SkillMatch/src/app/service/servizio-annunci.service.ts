import { Injectable, OnInit } from '@angular/core';


type Annunci ={
  
  img: string
  descrizione: string
  ambito: string
  titolo: string
  id:string

}

type Proposte={
  username:string
  img: string
  descrizione:string
  id:string
}

type Messaggio={

  contenuto:string
  data:string
  idChat: Chat
  inviato:boolean

}

type Chat={

  idAnnuncio: string
  usernameUtente: string 
  usernameLavoratore:string

}
@Injectable({
  providedIn: 'root'
})

export class ServizioAnnunciService implements OnInit {


  doingAccesso: boolean=false;
  private lavoratoreBool :boolean=false;
  private autenticato: boolean=false;

  url:string;
  chatAttuale: Chat ;

  usernameUtente="io" 
  annunci : Annunci[]=[{
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    titolo:"pincher da mangiare",
    descrizione:"cecedcc",
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


    username:"pippo",
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    descrizione:"cecedcc",
    id:"13"
    
  },
  {
    username:"giacom",
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    descrizione:"ceceaxdc",
    id:"12"
  },
  {
    username:"mll",
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    descrizione:"cecedcc",
    id:"12"
  },
  {
    username:"luigio",
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    descrizione:"cecedcc",
    id:"13"
  },
  {
    username:"maswso",
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    descrizione:"cecedcc",
    id:"13"
  },
]

  chat1:Chat={
    idAnnuncio:"13",
    usernameUtente:"io",
    usernameLavoratore:"maswso",
  }
  chat2:Chat={
    idAnnuncio:"12",
    usernameUtente:"io",
    usernameLavoratore:"giacom",
  }
  chat3:Chat={
    idAnnuncio:"12",
    usernameUtente:"io",
    usernameLavoratore:"giacom",
  }
  chat4:Chat={
    idAnnuncio:"12",
    usernameUtente:"io",
    usernameLavoratore:"giacom",
  }

  chatTotali : Chat[]=[

    this.chat1,this.chat2,this.chat3
  ]
  messaggi : Messaggio[]=[{
    contenuto:"a pucchiac",
    data:"27/07/96",
    idChat:this.chat1,
    inviato:true

  },
  {
    contenuto:"a uallera",
    data:"27/07/96",
    idChat:this.chat1,
    inviato:false
  },
  {

    contenuto:"i treni",
    data:"27/09/96",
    idChat:this.chat2,
    inviato:true
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

  getMessaggiByChat(){
    
    let chat: Messaggio[]=[]
    for(const messaggio of this.messaggi){

      if(messaggio.idChat===this.chatAttuale){
      
        chat.push(messaggio)
      }
    }
    return chat
    
  }

  setChatByUsernameAndId(usernameLavoratore : string,  idAnnuncio : string){

    console.log(usernameLavoratore+"username", idAnnuncio+"id")
    for(const chat of this.chatTotali){

      if(chat.idAnnuncio === idAnnuncio &&  chat.usernameLavoratore===usernameLavoratore
         && chat.usernameUtente===this.usernameUtente){

          this.chatAttuale=chat
          return
      }
    }
    this.chatAttuale=this.chat4


    /*this.chatTotali.push(new Chat(idAnnuncio, usernameLavoratore,this.usernameUtente))*/
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
