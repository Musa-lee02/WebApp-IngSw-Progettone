package pattern.skillmatchbackend.config;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordCrypt {
    private static final BCrypt encoder = new BCrypt();

    public static String encode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
    }
    //$2a$10$96O0VqY7GqeS4PLsThY7rusKPv2Co/rDxeXHTEENxYhLiab6qQIK2
    //$2a$10$96O0VqY7GqeS4PLsThY7rusKPv2Co/rDxeXHTEENxYhLiab6qQIK2
}
