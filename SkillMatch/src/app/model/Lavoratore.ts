import {Utente} from "./Utente";
import {Ambito} from "./Ambito";

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
    ambiti: Ambito[];
    notifica_email: boolean;
    punteggio: number;
  }


