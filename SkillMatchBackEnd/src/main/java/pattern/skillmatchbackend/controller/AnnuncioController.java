package pattern.skillmatchbackend.controller;

import org.springframework.web.bind.annotation.*;
import pattern.skillmatchbackend.model.Annuncio;
import pattern.skillmatchbackend.persistenza.DBManager;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/annuncio")
public class AnnuncioController {

    //TODO da testare
    @GetMapping("/getAnnunci")
    public List<Annuncio> getAnnunci(){
        return DBManager.getInstance().getAnnuncioDao().findAll();
    }

    //TODO da testare
    @PostMapping("/insertNewAnnuncio")
    public void insertNewAnnuncio(@RequestBody Annuncio annuncio){
        DBManager.getInstance().getAnnuncioDao().saveOrUpdate(annuncio);
    }

    //TODO manca il controller "annunciPerMe" che si trova in AnnuncioDaoPostgres

}
