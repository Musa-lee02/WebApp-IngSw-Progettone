import { Cliente } from "./Cliente";
import { Lavoratore } from "./Lavoratore";

export interface Recensione{

    idRecensione: number;
    titolo: string;
    descrizione: string;
    punteggio: number;
    recensore: Cliente;
    recensito: Lavoratore;

}