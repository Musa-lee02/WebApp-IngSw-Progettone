import {Utente} from "./Utente";

export interface LavoratoreSignUp{
    username: string;
    password: string;
    confermaPassword: string;
    email: string;
  }

  export interface LavoratoreSignUpGoogle{
    id: string;
    email: string;
  }

  export interface Lavoratore extends Utente {
    provincia_lavoro: string;
    ambiti: string[];
    notifica_email: boolean;
    punteggio: number;
  }


