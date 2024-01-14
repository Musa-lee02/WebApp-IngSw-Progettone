package pattern.skillmatchbackend.model.email;

import pattern.skillmatchbackend.model.Utente;

public class EmailContentConfermaRegistrazione extends  EmailContent{

    public EmailContentConfermaRegistrazione(Utente utente,String link){

        this.to = utente.getEmail();
        this.subject = "Conferma Indirizzo Email - Skillmatch";
        this.body = "Grazie per aver scelto Skillmatch! Per completare il processo di registrazione e garantire la sicurezza del tuo account, ti chiediamo gentilmente di confermare il tuo indirizzo email.\n" +
                "\n" +
                "Clicca sul seguente link per confermare il tuo indirizzo email: "+link+"\n" +
                "\n" +
                "Se non hai effettuato la registrazione su Skillmatch o non hai richiesto questa conferma, ti preghiamo di ignorare questa email. Nessuna azione sarà intrapresa e il tuo account rimarrà intatto.\n" +
                "\n" +
                "Grazie per la tua collaborazione nell'assicurare l'integrità del nostro servizio.\n" +
                "\n" +
                "Cordiali saluti,\n" +
                "\n" +
                "Il Team Skillmatch";
    }

}
