
import {Utente} from "./Utente";

export interface Lavoratore extends Utente{
  zona:string
  ambiti:string[]

}
/*export interface LavoratoreSignUp{
    username: string;
    password: string;
    confermaPassword: string;
    email: string;
  }

  export interface LavoratoreSignUpGoogle{
    id: string;
    email: string;
  }
  */



