import { Annuncio } from "./Annuncio";
import { Cliente } from "./Cliente";
import { Lavoratore } from "./Lavoratore";
import { Messaggio } from "./Messaggio";

export interface Chat{

    cliente: Cliente;
    lavoratore: Lavoratore;
    annuncio: Annuncio;
    messaggi: Messaggio[];
}
