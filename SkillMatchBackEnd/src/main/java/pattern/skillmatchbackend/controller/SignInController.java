package pattern.skillmatchbackend.controller;

import org.springframework.web.bind.annotation.*;
import pattern.skillmatchbackend.model.Lavoratore;
import pattern.skillmatchbackend.persistenza.DBManager;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/lavoratore/signin")

public class SignInController {

    @GetMapping("/infoProfilo")
    public Lavoratore getLavoratore(@RequestBody Lavoratore lavoratore) {
        return DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(lavoratore.getUsername());

    }
}
