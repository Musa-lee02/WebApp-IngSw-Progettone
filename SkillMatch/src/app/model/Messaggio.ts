import { Chat } from "./Chat";

export interface Messaggio{

    id: number;
    contenuto: string;
    data: Date;
    letto: boolean;
    isLavoratore: boolean;
    chat: Chat;

    
}
