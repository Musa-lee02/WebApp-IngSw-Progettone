import { Annuncio } from "./Annuncio";
import { Lavoratore } from "./Lavoratore";

export interface Proposta{

    titolo: string;
    descrizione: string;
    stato: string;
    prezzoLavoro: number | null;
    annuncioRelativo: Annuncio;
    lavoratore: Lavoratore;
    dataLavoro: Date;
}
