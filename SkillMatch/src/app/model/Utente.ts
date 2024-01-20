
export interface Utente{
  username: string
  email: string
  password : string
  nome: string
  cognome: string
  dataNascita: Date

  provincia: string
  imgProfilo: File
  registrato: boolean
  dataRegistrazione:Date
}


export interface UtenteCredenziali{
  username: string
  email: string
  password: string
}
