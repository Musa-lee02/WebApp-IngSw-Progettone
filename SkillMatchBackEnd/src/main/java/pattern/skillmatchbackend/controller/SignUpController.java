package pattern.skillmatchbackend.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pattern.skillmatchbackend.model.Lavoratore;
import pattern.skillmatchbackend.model.ValidationService;

@RestController
@RequestMapping("/lavoratore/signup")
public class SignUpController {

    @PostMapping("/passo1")
    public ResponseEntity<?> registerStep1(@RequestBody Lavoratore lavoratore, HttpSession session) {
        if(ValidationService.validateEmail(lavoratore.getEmail())) {
            return ResponseEntity.badRequest().body("Email non valida");
        }

        if(isEmailTaken(lavoratore.getEmail())) {
            return ResponseEntity.badRequest().body("Email già in uso");
        }

        if(isUsernameTaken(lavoratore.getUsername())) {
            return ResponseEntity.badRequest().body("Username già in uso");
        }

        if(ValidationService.validatePasswordUpperLetter(lavoratore.getPassword())) {
            return ResponseEntity.badRequest().body("Password non valida");
        }

        if(ValidationService.validatePasswordNumber(lavoratore.getPassword())) {
            return ResponseEntity.badRequest().body("Password non valida");
        }

        if(ValidationService.validatePasswordSpecialChar(lavoratore.getPassword())) {
            return ResponseEntity.badRequest().body("Password non valida");
        }

        if(ValidationService.validatePasswordLength(lavoratore.getPassword())) {
            return ResponseEntity.badRequest().body("Password non valida");
        }

        session.setAttribute("lavoratore1", lavoratore);
        return ResponseEntity.ok().build();
    }

     @PostMapping("/passo2")
    public ResponseEntity<?> registerStep2(@RequestBody Lavoratore lavoratore, HttpSession session) {

        session.setAttribute("lavoratore2", lavoratore);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/passo3")
    public ResponseEntity<?> registerStep3(HttpSession session) {
        Lavoratore lavoratore = (Lavoratore) session.getAttribute("lavoratore1");

        String email = lavoratore.getEmail();

        //String confirmationToken = tokenService.generateConfirmationToken(email);

        //String confirmationUrl = "http://localhost:8080/lavoratore/signup/confirm?token=" + confirmationToken;

        return null;
    }





    private boolean isEmailTaken(String email) {
        if (email.equals("abc@gmail.com")){
            return true;
        } else {
            return false;
        }
    }

    private boolean isUsernameTaken(String username) {
        if (username.equals("abc")){
            return true;
        } else {
            return false;
        }
    }
}

