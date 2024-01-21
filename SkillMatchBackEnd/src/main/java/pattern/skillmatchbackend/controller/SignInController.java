package pattern.skillmatchbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pattern.skillmatchbackend.model.Lavoratore;
import pattern.skillmatchbackend.model.TokenManager;
import pattern.skillmatchbackend.model.Utente;
import pattern.skillmatchbackend.persistenza.DBManager;

import static pattern.skillmatchbackend.config.PasswordCrypt.encode;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/retriveData")
public class SignInController {




    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Utente utente){
        if(DBManager.getInstance().getUtenteDao().checkLogin(utente.getUsername(), encode(utente.getPassword()))){

            String AuthToken = TokenManager.getInstance().creaToken(utente.getUsername(),  100 * 60 * 60 * 24 * 30 * 6);
            return ResponseEntity.ok().body(AuthToken);
        }else{
            return ResponseEntity.badRequest().body("Username o password errati");
        }


    }






    //TODO ho scritto la stessa classe in Lavoratore controller, perché in teoria andrebbe lì, però può andare bene pure qui. Non so se ancora dovevi finire di scrivere qualcosa in questa funzione
    @GetMapping("/infoProfilo")
    public Lavoratore getLavoratore(@RequestBody Lavoratore lavoratore) {
        return DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(lavoratore.getUsername());

    }
}
