package pattern.skillmatchbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pattern.skillmatchbackend.model.Lavoratore;
import pattern.skillmatchbackend.persistenza.DBManager;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/lavoratore")
public class LavoratoreController {

    //TODO da testare
    @GetMapping("/getLavoratoreByEmail")
    public Lavoratore getLavoratoreByEmail(Lavoratore lavoratore){
        return DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(lavoratore.getUsername());
    }




}
