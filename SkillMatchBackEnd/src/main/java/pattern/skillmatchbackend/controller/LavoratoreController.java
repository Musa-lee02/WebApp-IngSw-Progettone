package pattern.skillmatchbackend.controller;

import org.springframework.web.bind.annotation.*;
import pattern.skillmatchbackend.model.Lavoratore;
import pattern.skillmatchbackend.model.Utente;
import pattern.skillmatchbackend.persistenza.DBManager;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/lavoratore")
public class LavoratoreController {

    @GetMapping("getLavoratorePunteggio")
    public int getLavoratorePunteggio(@RequestBody Lavoratore lavoratore){
        return DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(lavoratore.getUsername()).getPunteggio();
    }

    @GetMapping("/getLavoratoreByIdAnnuncio")
    public List<Lavoratore> getLavoratoriByIdAnnuncio(@RequestParam long id){
        return DBManager.getInstance().getLavoratoreDao().getLavoratori(id);
    }



}
