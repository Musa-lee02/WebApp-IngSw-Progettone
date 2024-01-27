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

    LoginController decodifica = new LoginController();
    //TODO da testare
    @GetMapping("/getLavoratoreByUsername")
    public Lavoratore getLavoratoreByEmail(@RequestParam String token){

        System.out.println(token);
        return DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(decodifica.getUserByToken(token).getUsername());
    }

    //TODO da testare
    @GetMapping("getLavoratorePunteggio")
    public int getLavoratorePunteggio(@RequestBody Lavoratore lavoratore){
        return DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(lavoratore.getUsername()).getPunteggio();
    }

    @GetMapping("/getLavoratoreByIdAnnuncio")
    public List<Lavoratore> getLavoratoriByIdAnnuncio(@RequestParam long id){

        System.out.println(id);
        for (Lavoratore lavoratore : DBManager.getInstance().getLavoratoreDao().getLavoratori(id)){

            System.out.println(lavoratore.getUsername());
        }
        return DBManager.getInstance().getLavoratoreDao().getLavoratori(id);
    }



}
