import { Chat } from "./Chat";

export interface Messaggio{


    contenuto: string;
    data: Date;
    letto: boolean;
    isLavoratore: boolean;
    chat: Chat


}
