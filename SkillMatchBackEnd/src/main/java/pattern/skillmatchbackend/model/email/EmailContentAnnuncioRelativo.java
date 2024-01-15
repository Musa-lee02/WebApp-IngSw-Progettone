package pattern.skillmatchbackend.model.email;

import pattern.skillmatchbackend.model.Annuncio;
import pattern.skillmatchbackend.model.Utente;

public class EmailContentAnnuncioRelativo extends EmailContent {

    public EmailContentAnnuncioRelativo(Utente utente, Annuncio annuncio) {
        this.to = utente.getEmail();
        this.subject = "Nuovo annuncio di lavoro su SkillMatch";
        this.body = "Ciao "+utente.getNome()+" "+utente.getCognome()+",\n" +
                "\n" +
                "Volevamo farti sapere che è stato appena pubblicato un nuovo annuncio di lavoro nella tua zona e nel tuo settore su SkillMatch!\n" +
                "\n" +
                "Dettagli dell'Annuncio:\n" +
                "\n" +
                "Posizione: \n" +
                "Sede: [Città/Distretto]\n" +
                "Settore: [Settore]\n" +

                "Per saperne di più o candidarti:\n" +
                "\n" +
                "Accedi al tuo account SkillMatch.\n" +
                "Visita la sezione \"Annunci di Lavoro\".\n" +
                "Trova e clicca sull'annuncio di lavoro per tutti i dettagli.\n" +
                "Segui le istruzioni per candidarti.\n" +
                "\n" +
                "Grazie per essere parte della community SkillMatch.\n" +
                "\n" +
                "Cordiali saluti.";
    }

}
