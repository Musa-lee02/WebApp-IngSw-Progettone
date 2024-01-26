import {Province} from "./Province";


export interface Utente{
  username: string
  email: string
  password : string
  nome: string
  cognome: string
  dataNascita: Date
  provincia: string
  imgProfilo: File | string | undefined
  registrato: boolean
  dataRegistrazione: Date
}


export interface UtenteCredenziali{
  username: string
  password: string
}

export interface AuthToken{

  token: string
}
