export interface LavoratoreSignUp{
  username: string;
  email : string;
  password: string;
  confermaPassword: string;
  nome: string;
  cognome: string;
  dataNascita: string;
  fotoProfilo : string
  zonaCompotenza: string;
  ambitoDiLavoro: string;
  descrizione: string;


}

export interface LavoratoreSignUpGoogle{
  id: string;
  email: string;
}

export interface Lavoratore{
  username: string;
  password: string;
  confermaPassword: string;
  email: string;
}
