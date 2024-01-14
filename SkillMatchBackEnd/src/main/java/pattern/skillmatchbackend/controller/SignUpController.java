package pattern.skillmatchbackend.controller;

/*import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pattern.skillmatchbackend.model.Lavoratore;
import pattern.skillmatchbackend.model.ValidationService;
import pattern.skillmatchbackend.persistenza.DBManager;
import pattern.skillmatchbackend.persistenza.dao.LavoratoreDao;
import pattern.skillmatchbackend.persistenza.dao.postgres.LavoratoreDaoPostgres;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/lavoratore/signup")
public class SignUpController {

    @PostMapping("/passo1")
    public ResponseEntity<String> registerStep1(@RequestBody Lavoratore lavoratore, HttpSession session) {

        if (DBManager.getInstance().getLavoratoreDao().isEmailTaken(lavoratore.getEmail())) {
            return ResponseEntity.badRequest().body("Email già in uso");
        }

        if (DBManager.getInstance().getLavoratoreDao().isUsernameTaken(lavoratore.getUsername())) {
            return ResponseEntity.badRequest().body("Username già in uso");
        }


        if(ValidationService.validatePasswordUpperLetter(lavoratore.getPassword())) {
            return ResponseEntity.badRequest().body("Password non valida (deve contenere almeno una lettera maiuscola)");
        }

        if(ValidationService.validatePasswordNumber(lavoratore.getPassword())) {
            return ResponseEntity.badRequest().body("Password non valida (deve contenere almeno un numero)");
        }

        if(ValidationService.validatePasswordSpecialChar(lavoratore.getPassword())) {
            return ResponseEntity.badRequest().body("Password non valida (deve contenere almeno un carattere speciale)");
        }

        if(ValidationService.validatePasswordLength(lavoratore.getPassword())) {
            return ResponseEntity.badRequest().body("Password non valida (deve contenere almeno 8 caratteri)");
        }

        session.setAttribute("lavoratore1", lavoratore);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("checkExistence")
    public boolean checkExistenceGoogleAccount(@RequestBody Lavoratore lavoratore) {
        boolean presente = DBManager.getInstance().getLavoratoreDao().isEmailTaken(lavoratore.getEmail());
        if (presente)
            return true;
        return false;
    }

    }*/
