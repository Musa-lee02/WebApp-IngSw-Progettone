package pattern.skillmatchbackend.controller;

import org.springframework.web.bind.annotation.*;
import pattern.skillmatchbackend.model.Lavoratore;
import pattern.skillmatchbackend.model.Recensione;
import pattern.skillmatchbackend.model.TokenManager;
import pattern.skillmatchbackend.persistenza.DBManager;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/recensione")
public class RecensioneController {


    //TODO da testare
    @GetMapping("/getAnnunciWithToken/{token}")
    public List<Recensione> getRecensioniLavoratore(@PathVariable("token") String token){
        System.out.println("get RecensioneLavoratore: " + TokenManager.verificaToken(token));
        for (  Recensione recensione: DBManager.getInstance().getRecensioneDao().findByForeignKeyLavoratore(TokenManager.verificaToken(token))
             ) {
            System.out.println(recensione.getTitolo());

        }
        return DBManager.getInstance().getRecensioneDao().findByForeignKeyLavoratore(TokenManager.verificaToken(token));
    }


    //TODO da testare
    @PostMapping("/insertRecensione")
    public void insertRecensione(@RequestBody Recensione recensione){
        System.out.println("Recensione controller: " + recensione.getTitolo());
        DBManager.getInstance().getRecensioneDao().saveOrUpdate(recensione);
    }

}
