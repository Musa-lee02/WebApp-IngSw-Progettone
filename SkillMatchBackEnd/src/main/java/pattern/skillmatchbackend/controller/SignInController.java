package pattern.skillmatchbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pattern.skillmatchbackend.dto.LoginClienteDto;
import pattern.skillmatchbackend.dto.LoginLavoratoreDto;
import pattern.skillmatchbackend.model.Lavoratore;
import pattern.skillmatchbackend.model.TokenManager;
import pattern.skillmatchbackend.model.Utente;
import pattern.skillmatchbackend.persistenza.DBManager;

import static pattern.skillmatchbackend.config.PasswordCrypt.encode;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/retriveData")
public class SignInController {

    @PostMapping("/loginLavoratore")
    public ResponseEntity<?> loginLavoratore(@RequestBody Utente utente){
        if(DBManager.getInstance().getUtenteDao().checkLogin(utente.getUsername(), encode(utente.getPassword()))){

            LoginLavoratoreDto lavoratoreDto = new LoginLavoratoreDto();

            String AuthToken = TokenManager.getInstance().creaToken(utente.getUsername(),  100 * 60 * 60 * 24 * 30 * 6);

            lavoratoreDto.setToken(AuthToken);
            // So che questa è un'altra chiamata al db
            lavoratoreDto.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(utente.getUsername()));

            return ResponseEntity.ok().body(AuthToken);
        }else{
            return ResponseEntity.badRequest().body("Username o password errati");
        }
    }

    @PostMapping("/loginCliente")
    public ResponseEntity<?> loginCliente(@RequestBody Utente utente){
        if(DBManager.getInstance().getUtenteDao().checkLogin(utente.getUsername(), encode(utente.getPassword()))){

            LoginClienteDto clienteDto = new LoginClienteDto();

            String AuthToken = TokenManager.getInstance().creaToken(utente.getUsername(),  100 * 60 * 60 * 24 * 30 * 6);

            clienteDto.setToken(AuthToken);
            // So che questa è un'altra chiamata al db
            clienteDto.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(utente.getUsername()));

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
