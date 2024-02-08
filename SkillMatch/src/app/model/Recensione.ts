import { Cliente } from "./Cliente";
import { Lavoratore } from "./Lavoratore";
import { Ambito } from "./Ambito";
import { Proposta } from "./Proposta";
import {Province} from "./Province";


export interface Recensione{

    idRecensione: number;
    titolo: string;
    descrizione: string;
    punteggio: number;
    cliente: Cliente;
    lavoratore: Lavoratore;

}
