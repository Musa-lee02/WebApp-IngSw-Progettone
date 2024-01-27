import { Ambito } from "./Ambito";
import { Cliente } from "./Cliente";
import { Proposta } from "./Proposta";

export interface Annuncio{

   id: number ;
    titolo: string;
    descrizione: string;
    dataDiScadenza: Date;
    provinciaAnnuncio: string;
    image: string;
    cliente: Cliente;
    ambito: Ambito;
    proposta: Proposta;

}
