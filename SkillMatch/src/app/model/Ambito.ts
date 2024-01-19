import { Annuncio } from "./Annuncio";
import { Lavoratore } from "./Lavoratore";

export interface Ambito{
    idAmbito: number | null;
    nome: string;
    icona: string;
    annunci: Annuncio[];
    lavoratori: Lavoratore[];
}