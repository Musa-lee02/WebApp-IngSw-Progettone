package pattern.skillmatchbackend.model.email;

import java.util.Properties;

public class GmailConfig implements EmailConfig{

    private Properties properties = new Properties();
    private String senderAddress ="skillmatch2024@gmail.com";
    private String password = "opps jjky isus dgbs";


    public GmailConfig() {
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
    }


    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public String getSenderAddress() {
        return senderAddress;
    }

    @Override
    public String getPassword() {
        return password;
    }


}
