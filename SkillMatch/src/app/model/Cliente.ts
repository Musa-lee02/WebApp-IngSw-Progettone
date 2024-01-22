import {Utente} from "./Utente";

export class Cliente implements Utente{
    username: string;
    email: string;
    password: string;
    nome: string;
    cognome: string;
    dataNascita: Date;
    provincia: string;
    imgProfilo: File;
    registrato: boolean;
    dataRegistrazione: Date;



}
