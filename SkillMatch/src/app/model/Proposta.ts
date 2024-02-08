import { Annuncio } from "./Annuncio";
import { Lavoratore } from "./Lavoratore";


export interface Proposta{

    descrizione: string;
    stato: string;
    statoLavoro:  string | undefined;
    prezzoLavoro: number;
    annuncioRelativo: Annuncio;
    lavoratore: Lavoratore;
    dataLavoro: Date;


}
