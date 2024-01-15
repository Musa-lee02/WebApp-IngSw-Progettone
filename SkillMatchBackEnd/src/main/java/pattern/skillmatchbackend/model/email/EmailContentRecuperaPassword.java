package pattern.skillmatchbackend.model.email;

import pattern.skillmatchbackend.model.Utente;

public class EmailContentRecuperaPassword extends EmailContent {

    public EmailContentRecuperaPassword(Utente utente,String link) {

        this.to = utente.getEmail();
        this.subject = "Procedura di Recupero Password Skillmatch";
        this.body = "Gentile "+utente.getNome()+" "+utente.getCognome()+",\n"+
            "Abbiamo ricevuto una richiesta di recupero password per il tuo account Skillmatch associato all'indirizzo email: "+utente.getEmail() +
            ".\nPer reimpostare la tua password, clicca sul seguente link: "+link+
                "\nSe non hai richiesto questo recupero, ignora semplicemente questa email. Nessuna azione sarà intrapresa e la tua password rimarrà invariata. " +
            "\nPer garantire la sicurezza del tuo account, ti consigliamo di scegliere una password forte e di non condividerla con nessuno. " +
            "\nGrazie per la tua fiducia in Skillmatch." +
            "\nCordiali saluti, " +
            "\nIl Team Skillmatch";

    }
}
