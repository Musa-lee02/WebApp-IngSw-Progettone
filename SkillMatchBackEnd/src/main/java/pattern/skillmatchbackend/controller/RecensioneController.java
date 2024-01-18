package pattern.skillmatchbackend.controller;

import org.springframework.web.bind.annotation.*;
import pattern.skillmatchbackend.model.Lavoratore;
import pattern.skillmatchbackend.model.Recensione;
import pattern.skillmatchbackend.persistenza.DBManager;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/recensione")
public class RecensioneController {


    //TODO da testare
    @GetMapping("/getRecensioniLavoratore")
    public List<Recensione> getRecensioniLavoratore(@RequestBody Lavoratore lavoratore){
        return DBManager.getInstance().getRecensioneDao().findByForeignKeyLavoratore(lavoratore.getUsername());
    }


    //TODO da testare
    @PostMapping("/insertRecensione")
    public void insertRecensione(@RequestBody Recensione recensione){
        DBManager.getInstance().getRecensioneDao().saveOrUpdate(recensione);
    }

}
