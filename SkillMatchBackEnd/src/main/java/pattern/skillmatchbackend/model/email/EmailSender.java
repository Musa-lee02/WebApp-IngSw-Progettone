package pattern.skillmatchbackend.model.email;
import org.apache.catalina.Authenticator;
import org.apache.catalina.Session;
import org.apache.logging.log4j.message.Message;

import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    public boolean sendEmail(EmailConfig emailConfig, EmailContent emailContent){

        // Indirizzo del mittente e destinatario
        String from = emailConfig.getSenderAddress();
        String to = emailContent.to();

        // Proprietà per la configurazione del server di posta
        Properties properties = emailConfig.getProperties();

        // Autenticazione
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, emailConfig.getPassword());
            }
        });

        try {
            // Creazione del messaggio
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(emailContent.subject());
            message.setText(emailContent.body());

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

}


 */