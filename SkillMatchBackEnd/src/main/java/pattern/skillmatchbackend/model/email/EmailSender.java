package pattern.skillmatchbackend.model.email;
import pattern.skillmatchbackend.model.Annuncio;
import pattern.skillmatchbackend.model.Utente;

import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {


    private boolean sendEmail(EmailConfig emailConfig, EmailContent emailContent){

        // Indirizzo del mittente e destinatario
        String from = emailConfig.getSenderAddress();
        String to = emailContent.getTo();

        // Proprietà per la configurazione del server di posta
        Properties properties = emailConfig.getProperties();

        // Autenticazione

        Session session = Session.getInstance(properties, new Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(from, emailConfig.getPassword());

            }
        });

        try {
            // Creazione del messaggio
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(emailContent.getSubject());
            message.setText(emailContent.getBody());

            // Invio del messaggio
            Transport.send(message);

            System.out.println("Email inviata con successo!");

            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Errore nell'invio dell'email: " + e.getMessage());
            return false;
        }

    }

    public boolean recuperaPassword(Utente utente, String link) {

        return sendEmail(new GmailConfig(),new EmailContentRecuperaPassword(utente,link));

    }

    public boolean confermaLink(Utente utente,String link){
        return sendEmail(new GmailConfig(),new EmailContentConfermaRegistrazione(utente,link));
    }

    public boolean annuncioRelativo(Utente utente, Annuncio annuncio) {
        return sendEmail(new GmailConfig(),new EmailContentAnnuncioRelativo(utente,annuncio));
    }

}
