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

    @GetMapping("/getLAVORATOREByIdAnnuncio")
    public Lavoratore getLAVORATOREByIdAnnuncio(@RequestParam long id){
        //System.out.println(id);
        Lavoratore lav = DBManager.getInstance().getLavoratoreDao().getLavoratoreByIdAnnuncio(id);
        //System.out.println(lav.getUsername());
        return DBManager.getInstance().getLavoratoreDao().getLavoratoreByIdAnnuncio(id);
    }



}
