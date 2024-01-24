package pattern.skillmatchbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pattern.skillmatchbackend.dto.LoginClienteDto;
import pattern.skillmatchbackend.dto.LoginLavoratoreDto;
import pattern.skillmatchbackend.model.*;
import pattern.skillmatchbackend.persistenza.DBManager;

import static pattern.skillmatchbackend.config.PasswordCrypt.encode;
import static pattern.skillmatchbackend.config.PasswordCrypt.matches;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/retriveData")
public class SignInController {

    @PostMapping("/loginLavoratore")
    public ResponseEntity<?> loginLavoratore(@RequestBody UtenteCredenziali utenteCredenziali){
        Lavoratore lavoratore = DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(utenteCredenziali.getUsername());

        if(lavoratore != null && matches(utenteCredenziali.getPassword(), lavoratore.getPassword())){

            LoginLavoratoreDto lavoratoreDto = new LoginLavoratoreDto();

            String AuthToken = TokenManager.getInstance().creaToken(utenteCredenziali.getUsername(),  100 * 60 * 60 * 24 * 30 * 6);

            lavoratoreDto.setToken(AuthToken);
            lavoratoreDto.setLavoratore(lavoratore);

            return ResponseEntity.ok().body(lavoratoreDto);
        }else{
            return ResponseEntity.badRequest().body("Username o password errati");
        }
    }

    @PostMapping("/loginCliente")
    public ResponseEntity<?> loginCliente(@RequestBody UtenteCredenziali utenteCredenziali){
        Cliente cliente = DBManager.getInstance().getClienteDao().findByPrimaryKey(utenteCredenziali.getUsername());

        if(cliente != null && matches(utenteCredenziali.getPassword(), cliente.getPassword())){

            LoginClienteDto clienteDto = new LoginClienteDto();

            String AuthToken = TokenManager.getInstance().creaToken(utenteCredenziali.getUsername(),  100 * 60 * 60 * 24 * 30 * 6);

            clienteDto.setToken(AuthToken);
            clienteDto.setCliente(cliente);

            return ResponseEntity.ok().body(clienteDto);
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
