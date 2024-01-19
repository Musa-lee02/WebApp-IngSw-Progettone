export interface Notifica {

    id: number;
    contenuto: string;
    data: Date;
    visualizzato: boolean;
    chi: boolean;
    utente: Utente;

    
}