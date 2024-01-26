package pattern.skillmatchbackend.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pattern.skillmatchbackend.model.Utente;
import pattern.skillmatchbackend.persistenza.DBManager;

import pattern.skillmatchbackend.config.PasswordCrypt;

import java.util.Base64;

@RestController
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class LoginController {
    private class AuthToken{
        String token;
        Utente utente;

        public Utente getUtente() {
            return utente;
        }
        public void setUtente(Utente utente) {
            this.utente = utente;
        }
        public String getToken() {
            return token;
        }
        public void setToken(String token) {
            this.token = token;
        }
    }
    @PostMapping("/login")
    public AuthToken login(@RequestBody Utente utente, HttpServletRequest req) throws Exception{

        String username = utente.getUsername();
        String password = utente.getPassword();
        System.out.println(username);
        System.out.println(password);
        String concat = username + ":" + password;
        String token = codificaBase64(concat);
        utente = getUserByToken(token);
        if (utente != null){
            System.out.println("sto settando il token");
            HttpSession session = req.getSession();
            session.setAttribute("user", utente);
            AuthToken auth = new AuthToken();
            auth.setToken(token);
            auth.setUtente(utente);
            System.out.println("xxx");
            return auth;
        }
        return null;
    }

    @PostMapping("/logout")
    public boolean logout(@RequestBody Utente utente, HttpServletRequest req) throws Exception{
        return true;
    }

    @PostMapping("/isAuthenticated")
    public boolean isAuthenticated(HttpServletRequest req){
        System.out.println("Chiama");
        String auth = req.getHeader("Authorization");
        System.out.println(auth);
        if (auth != null){
            String token = auth.substring("Basic ".length());
            return getUserByToken(token) != null;
        }else{
            return false;
        }
    }

    public Utente getUserByToken(String token){
        if (token != null) {
            String decod = decodificaBase64(token);
            String username = decod.split(":")[0];
            CharSequence password = decod.split(":")[1];
            Utente utente = DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(username);
            if (utente != null) {
                System.out.println(" hashed password chiamata in getUserByToken " + utente.getPassword());
                System.out.println(" clear password chiamata in getUserByToken " + password);
                if (PasswordCrypt.matches(password, utente.getPassword())) {
                    System.out.println("utente restituito");

                    return utente;
                }
            }
        }
        return null;
    }

    private String codificaBase64(String value){
        return Base64.getEncoder().encodeToString(value.getBytes());
    }

    private String decodificaBase64(String value){
        return new String(Base64.getDecoder().decode(value.getBytes()));
    }
}
