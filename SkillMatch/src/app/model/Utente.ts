
export interface Utente{
  username: string
  email: string
  password : string
  nome: string
  cognome: string
  dataNascita: any
  provincia: string
  imgProfilo: File
  registrato: boolean
  dataRegistrazione: any
}


export interface UtenteCredenziali{
  username: string
  email: string
  password: string
}
