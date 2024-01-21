package pattern.skillmatchbackend.config;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordCrypt {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encode(String password) {
        return encoder.encode(password);
    }

    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
