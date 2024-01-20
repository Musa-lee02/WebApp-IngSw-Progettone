package pattern.skillmatchbackend.controller;

import org.springframework.web.bind.annotation.*;
import pattern.skillmatchbackend.model.Lavoratore;
import pattern.skillmatchbackend.model.Utente;
import pattern.skillmatchbackend.persistenza.DBManager;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/lavoratore")
public class LavoratoreController {

    //TODO da testare
    @GetMapping("/getLavoratoreByEmail")
    public Lavoratore getLavoratoreByEmail(@RequestBody Lavoratore lavoratore){
        return DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(lavoratore.getUsername());
    }

    //TODO da testare
    @GetMapping("getLavoratorePunteggio")
    public int getLavoratorePunteggio(@RequestBody Lavoratore lavoratore){
        return DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(lavoratore.getUsername()).getPunteggio();
    }




}
