package pattern.skillmatchbackend.controller;

import org.springframework.web.bind.annotation.*;
import pattern.skillmatchbackend.model.Lavoratore;
import pattern.skillmatchbackend.model.Proposta;
import pattern.skillmatchbackend.persistenza.DBManager;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/proposta")
public class PropostaController {

    /*
    //TODO da testare
    @PostMapping("setStatoProposta")
    public void setStatoProposta(@RequestBody Proposta proposta){
        proposta.setStato("qui_andrebbe_lo_stato"); //Oppure lo stato si potrebbe mettere come paramentro, oppure si potrebbe modificare dal frontend e salvare direttamente nel db
        DBManager.getInstance().getPropostaDao().saveOrUpdate(proposta);
    }
     */

    /*
    //TODO da testare
    @GetMapping("getStatoProposta")
    public String getStatoProposta(@RequestBody Lavoratore lavoratore){
        return DBManager.getInstance().getPropostaDao().findByForeignKeyLavoratore(lavoratore.getUsername()).getStatus();
    }
     */

    //non so se si possono mettere 2 parametri requestbody e non so come generare http per 2 parametri
    @PostMapping("/getPropostaByAnnuncioEUsernameLavoratore")
    public Proposta getPropostaByAnnuncioEUsernameLavoratore(@RequestBody Annuncio annuncio, @RequestBody Lavoratore lavoratore){

        return DBManager.getInstance().getPropostaDao().findByAnnuncioEUsernameLavoratore(annuncio.getId(),lavoratore.getUsername());

    }

    ///

}
