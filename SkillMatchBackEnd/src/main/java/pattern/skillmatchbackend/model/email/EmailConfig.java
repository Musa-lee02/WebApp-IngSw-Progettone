package pattern.skillmatchbackend.model.email;

import java.util.Properties;

public interface EmailConfig {

    public Properties getProperties();
    public String getSenderAddress();
    public String getPassword();


}
