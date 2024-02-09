import { Ambito } from "./Ambito";
import { Cliente } from "./Cliente";
import { Proposta } from "./Proposta";
import {Province} from "./Province";

export interface Annuncio{

    id: number ;
    titolo: string;
    descrizione: string;
    dataDiScadenza: Date;
    provinciaAnnuncio: Province | string;
    image: string;
    cliente: Cliente;
    ambito: Ambito;
    proposta: Proposta;
    consigliato: boolean
    starClicked: boolean

}
