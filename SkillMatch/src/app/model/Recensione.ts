import { Cliente } from "./Cliente";
import { Lavoratore } from "./Lavoratore";

export interface Recensione{

    idRecensione: number;
    titolo: string;
    descrizione: string;
    punteggio: number;
    cliente: Cliente;
    lavoratore: Lavoratore;

}
