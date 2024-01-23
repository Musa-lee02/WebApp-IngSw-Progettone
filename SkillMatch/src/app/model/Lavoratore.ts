import {Utente} from "./Utente";
import {Ambito} from "./Ambito";
import {Province} from "./Province";

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
    provinciaLavoro: string;
    ambiti: Ambito[];
    notificaEmail: boolean;
    punteggio: number;
  }


