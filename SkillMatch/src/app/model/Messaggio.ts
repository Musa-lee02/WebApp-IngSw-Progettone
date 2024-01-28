import { Chat } from "./Chat";

export interface Messaggio{


    contenuto: string;
    data: Date;
    inviato: boolean,
    isLavoratore: boolean;
    chat: Chat


}
