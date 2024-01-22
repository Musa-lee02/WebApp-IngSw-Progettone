package pattern.skillmatchbackend.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pattern.skillmatchbackend.data.service.ImageServiceImpl;
import pattern.skillmatchbackend.data.service.interf.ImageService;
import pattern.skillmatchbackend.model.*;
import pattern.skillmatchbackend.model.email.EmailSender;
import pattern.skillmatchbackend.persistenza.DBManager;
import pattern.skillmatchbackend.persistenza.dao.LavoratoreDao;
import pattern.skillmatchbackend.persistenza.dao.postgres.LavoratoreDaoPostgres;

import java.util.Objects;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/signup")
public class SignUpController {

    private final ImageServiceImpl imageService = new ImageServiceImpl();

    @PostMapping("/passo1")
    public ResponseEntity<String> registerStep1(@RequestBody Utente utente, HttpSession session) {

        if (DBManager.getInstance().getUtenteDao().isEmailTaken(utente.getEmail())) {
            return ResponseEntity.badRequest().body("Email già in uso");
        }

        if (DBManager.getInstance().getUtenteDao().isUsernameTaken(utente.getUsername())) {
            return ResponseEntity.badRequest().body("Username già in uso");
        }


        if(ValidationService.validatePasswordUpperLetter(utente.getPassword())) {
            return ResponseEntity.badRequest().body("Password non valida (deve contenere almeno una lettera maiuscola)");
        }

        if(ValidationService.validatePasswordNumber(utente.getPassword())) {
            return ResponseEntity.badRequest().body("Password non valida (deve contenere almeno un numero)");
        }

        if(ValidationService.validatePasswordSpecialChar(utente.getPassword())) {
            return ResponseEntity.badRequest().body("Password non valida (deve contenere almeno un carattere speciale)");
        }

        if(ValidationService.validatePasswordLength(utente.getPassword())) {
            return ResponseEntity.badRequest().body("Password non valida (deve contenere almeno 8 caratteri)");
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/google/checkExistence")
    public boolean checkExistenceGoogleAccount(@RequestBody Utente utente) {
        boolean presente = DBManager.getInstance().getUtenteDao().isEmailTaken(utente.getEmail());
       return presente;
    }

    //TODO da testare con DB (senza DB le immagini funzionano)
    @PostMapping("/completeRegistration/Lavoratore")
    public boolean completeRegistrationLavoratore(@RequestPart("lavoratore") Lavoratore lavoratore, @RequestPart("img") MultipartFile img) {

        for (Ambito a : lavoratore.getAmbiti()) {
            System.out.println("id:" + a.getId() + "nome:" + a.getNome());
        }

        if(imageService.insertNewLavoratoreAccountAndImage(lavoratore, img)){
            //DBManager.getInstance().getLavoratoreDao().saveOrUpdate(lavoratore);

            EmailSender emailSender = new EmailSender();
            TokenManager tokenManager = new TokenManager();
            String token = tokenManager.creaToken(lavoratore.getUsername());
            emailSender.confermaLink(lavoratore, "http://localhost:4200/ConfermaAccount?token=" + token);

            return true;
        }else{ return false; }
    }

    @PostMapping("/completeRegistration/Cliente")
    public boolean completeRegistrationCliente(@RequestPart("cliente") Cliente cliente) {

        if(imageService.insertNewClienteAccountAndImage(cliente)) {
            //DBManager.getInstance().getClienteDao().saveOrUpdate(cliente);

            EmailSender emailSender = new EmailSender();
            TokenManager tokenManager = new TokenManager();
            String token = tokenManager.creaToken(cliente.getUsername());
            emailSender.confermaLink(cliente, "http://localhost:4200/ConfermaAccount?token=" + token);

            return true;
        }else{ return false; }
    }

}