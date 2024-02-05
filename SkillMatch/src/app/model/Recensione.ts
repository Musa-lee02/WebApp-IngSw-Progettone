import { Cliente } from "./Cliente";
import { Lavoratore } from "./Lavoratore";
import { Ambito } from "./Ambito";
import { Proposta } from "./Proposta";
import {Province} from "./Province";


export interface Recensione{

  id: number
  titolo: string
  descrizione: string
  voto: number
  usrCliente: string
  usrLavoratore: string

}
