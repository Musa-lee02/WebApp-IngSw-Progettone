import { Injectable, OnInit } from '@angular/core';
import { CardsVetrinaComponent } from '../component/cards-vetrina/cards-vetrina.component';


type Annunci ={

  username:string
  img: string
  descrizione: string
  ambito: string
  titolo: string
  id:string
  stato: string
  zona:string
  dataDiScadenza: Date

}

type Proposte={
  username:string
  img: string
  descrizione:string
  id:string
  stato: string
}

type Proposta={

  descrizione:string
  dataScadenza:string
  stato:string
  prezzo:number
  idAnnuncio:string
  usernameLavoratore:string

}


type Messaggio={

  contenuto:string
  data:string
  idChat: Chat
  inviato:boolean

}

type Chat={

  idAnnuncio: string
  interlocutore1: string 
  interlocutore2:string

}

type Lavoratore={
  username:string
  img:string

}
@Injectable({
  providedIn: 'root'
})

export class ServizioAnnunciService implements OnInit {

  selectedAmbito: string;
  selectedZona: string;

  buttonSearchClickedBool:boolean=false;
  doingAccesso: boolean;
  private lavoratoreBool :boolean=false;
  private autenticato: boolean=false;
  private skipAutentication :boolean;
  currentImage : string = "../../../assets/default.jpg";

  url:string;
  chatAttuale: Chat ;


  usernameUtente="utente1" 
  annunci : Annunci[]=[{
    username: 'utente1',
    img:'../assets/soffione-doccia-rotto.png',
    titolo:"soffione Rotto",
    descrizione:"descrizione...",
    ambito:"Cucina",
    id:"12",
    stato: "accettata", 
    zona:"Cosenza",
    dataDiScadenza:new Date("2023/11/10")
  
  },
  {
    username: 'utente1',
    img:'../assets/imagedefault.avif',
    titolo:"Logo per azienda",
    descrizione:" Avrei bisogno di un logo per la mia startup che sto creando",
    ambito:"Tecnologia",
    id:"13",
    stato: "rifiutata",
    zona:"Vibo Valentia",
    dataDiScadenza:new Date("2023/11/10")
  },
  {
    username: 'utente2',
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    titolo:"pincher da mangiare",
    descrizione:"The purina is a small breed of dog, originating from Germany. The breed's earliest ancestors may have included the German Pinscher mixed with Italian greyhounds and dachshunds.",
    ambito:"Edilizia",
    id:"12",
    stato: "rifiutata",
    zona:"Reggio Calabria",
    dataDiScadenza:new Date("2023/11/10")
  },


  {
    username: 'utente1',
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    titolo:"pincher da mangiare",
    descrizione:"The purina is a small breed of dog, originating from Germany. The breed's earliest ancestors may have included the German Pinscher mixed with Italian greyhounds and dachshunds.",
    ambito:"Edilizia",
    id:"12",
    stato: "rifiutata",
    zona:"Vibo Valentia",
    dataDiScadenza:new Date("2023/11/10")
  },
  {
    username: 'utente3',
    img:'https://material.angular.io/assets/img/examples/shiba2.jpg',
    titolo:"Shiba inu da vendere",
    descrizione:"   The Shiba Inu is the prettiest ACCALAPPIA-CANI of the six original and distinct spitz breeds of dog from Japan."+
      "A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was originally"+
      "bred for hunting.",
    ambito:"Tecnologia",
    id:"13",
    stato: "InCorso",
    zona:"Reggio Calabria",
    dataDiScadenza:new Date("2023/11/10")
  },

  ]

  lavoratori: Lavoratore[]=[{
      username: "pippo",
      img:"https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg"
    },

    {
      username:"mario",
      img:"../assets/user.jpg"
    },
    {
      username:"giacom",
      img:"https://www.centrodogtrainer.it/wp-content/uploads/2020/11/addestramento-golden-retriever-1920x960.jpg"
    },
    {
      username:"luigio",
      img:"https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg"
    },
    {
      username:"mll",
      img:"'https://material.angular.io/assets/img/examples/shiba2.jpg'"
    }
  
  ]

  proposta: Proposta[]=[{

    descrizione:"incontro in data 24/12/2023 al prezzo stabilito nella voce sotto",
    dataScadenza:"24/12/2023",
    stato:"Inviata",
    prezzo:100,
    idAnnuncio:"13",
    usernameLavoratore:"mario"
    
  }]
  proposte : Proposte[]=[{


    username:'mario',
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    descrizione:"cecedcc",
    id:"13",
    stato:"InCorso"
  },
  {
    username:'giacom',
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    descrizione:"ceceaxdc",
    id:"12",
    stato:"accettata"
  },
  {
    username:'mll',
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    descrizione:"cecedcc",
    id:"12",
    stato:"rifiutata"
  },
  {
    username:'luigio',
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    descrizione:"cecedcc",
    id:"13",
    stato:"inCorso"
  },
  {
    username:'mario',
    img:'../assets/user.jpg',
    descrizione:"cecedcc",
    id:"13",
    stato: "inCorso"
  },
]

ambiti: string[] = ['Cucina', 'Tecnologia', 'Edilizia', 'Elettronica', 'Meccanica', 'Informatica', 'Altro']

  province: string[] = ['Cosenza', 'Reggio Calabria', 'Vibo Valentia', 'Catanzaro', 'Crotone',
              'Napoli', 'Salerno', 'Avellino', 'Benevento', 'Caserta', 'Potenza', 'Matera' ]

  


  chat1:Chat={
    idAnnuncio:"13",
    interlocutore1:"utente1",
    interlocutore2:"mario",
  }
  chat2:Chat={
    idAnnuncio:"12",
    interlocutore1:"untente2",
    interlocutore2:"giacom",
  }
  chat3:Chat={
    idAnnuncio:"12",
    interlocutore1:"untente3",
    interlocutore2:"giacom",
  }
  chat4:Chat={
    idAnnuncio:"12",
    interlocutore1:"utente1",
    interlocutore2:"mario",
  }

  chatTotali : Chat[]=[

    this.chat1,this.chat2,this.chat3
  ]
  messaggi : Messaggio[]=[{
    contenuto:"ciao come va",
    data:"27/07/96",
    idChat:this.chat1,
    inviato:true

  },
  {
    contenuto:"tutto bene",
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
 
  constructor( ) { }
  ngOnInit(): void {
    this.autenticato=false

  }

  setSelectAmbito(ambito: string){
    this.selectedAmbito=ambito
  }
  setSelectZona(zona: string){
    this.selectedZona=zona
  }

  getSelectAmbito(){
    return this.selectedAmbito
  }
  getSelectZona(){
    return this.selectedZona
  }

  isAmbitoValid(): boolean {
    // Verifica se l'ambito è presente nella lista degli ambiti
    return this.ambiti.includes(this.selectedAmbito);
  }
  isZoneValid(): boolean {
    // Verifica se la zona è presente nella lista delle province
    return this.province.includes(this.selectedZona);
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
  setPicProfile(url : string){
    this.currentImage=url
  }
  getPicProfile(){

    return this.currentImage
  }

  getProposte(){
    return this.proposte
  }

  getLavoratoriByIdAnnuncio(id: string){

    let proposteFiltrate=this.getProposteById(id)
    let lavoratoriFiltarti : Lavoratore[]=[]
    for(const lavoratore of this.lavoratori){
      for(let i =0; i<proposteFiltrate.length; i++){
        if(proposteFiltrate[i].username===lavoratore.username){
          lavoratoriFiltarti.push(lavoratore)
          proposteFiltrate.splice(i,1);
        }
      }
    }
  
    return lavoratoriFiltarti
  }
  getPropostaVera(chat : Chat){

    for(const proposta of this.proposta){

      if(proposta.idAnnuncio===chat.idAnnuncio && (proposta.usernameLavoratore===chat.interlocutore1 || chat.interlocutore2)){
        return proposta
      }
    }
    return
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
   getPropostaAccettataByid(id: string){

    for(const proposta of this.proposte){

      if(proposta.id===id){
      
        proposta.stato==="accettata"
        return proposta
      }
    }
    return null

  }
  getDestinatarioCard(chat :Chat){ //DA CAMBIARE

    this.setlavoratoreBool(false)
    
    for(const lavoratore of this.lavoratori){

      
      if(chat.interlocutore1 === lavoratore.username || chat.interlocutore2 === lavoratore.username ){

            return lavoratore; 
        }
    }
    return
}
  getProposteLavoratore(username: string){
    
    let proposteFiltrate : Proposte[]=[]
    for(const proposta of this.proposte){
      if(proposta.username ===username){
        proposteFiltrate.push(proposta)
      }
    }
    return proposteFiltrate
  }

  getAnnunciByUsernameLavoratore(username: string){

      let proposteFiltrate=this.getProposteLavoratore(username)
      
      let annunciFiltrati: Annunci[]=[]
      for( let i=0; i<this.annunci.length; i++){
        for( let j=0; j<proposteFiltrate.length;j++)
        {
         
          if(proposteFiltrate[j].id === this.annunci[i].id)
          {
            annunciFiltrati.push(this.annunci[i])
            
          }
        }
      }
      
      return annunciFiltrati
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
  buttonSearchClicked(){


    this.buttonSearchClickedBool=true
    return this.getAnnunciByAmbitoEZona()

  }
  getAnnunciByAmbitoEZona() : Annunci[]{
      
    
    if (this.buttonSearchClickedBool){

    
    let inHome=true
    let annunciFiltrati:Annunci[]=[]
   
  
    for(const annuncio of this.annunci){

      if(annuncio.ambito=== this.selectedAmbito && annuncio.zona === this.selectedZona){
        inHome=false;
        
        annunciFiltrati.push(annuncio)
      }
    }

    if(annunciFiltrati.length>0){
      console.log(annunciFiltrati)
      return annunciFiltrati
    }
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

  setChatByUsernameAndId(usernameDestinatario : string,  idAnnuncio : string){

    console.log(usernameDestinatario+"username", idAnnuncio+"id")
    for(const chat of this.chatTotali){

      
      if(chat.idAnnuncio === idAnnuncio &&  ((chat.interlocutore1===usernameDestinatario
         && chat.interlocutore2===this.usernameUtente)||chat.interlocutore1===this.usernameUtente
         && chat.interlocutore2===usernameDestinatario)){

          this.chatAttuale=chat
          
          return
      }
    }
    this.chatAttuale=this.chat4


    /*this.chatTotali.push(new Chat(idAnnuncio, usernameLavoratore,this.usernameUtente))*/
  }

  getChat(){

    
    return this.chatAttuale
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
  setDoingAccesso(bool : boolean){
    this.doingAccesso=bool;
  }
  isAutenticato(){
    
    return this.autenticato
  }

  setSkipAutentication(bool: boolean){

   
    this.skipAutentication =bool

  }
  getSkipAutentication(){
    return this.skipAutentication
  }

}
