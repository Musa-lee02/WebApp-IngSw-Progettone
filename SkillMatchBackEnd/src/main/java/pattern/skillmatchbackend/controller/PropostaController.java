package pattern.skillmatchbackend.controller;

import org.springframework.web.bind.annotation.*;
import pattern.skillmatchbackend.model.Annuncio;


import pattern.skillmatchbackend.model.Proposta;
import pattern.skillmatchbackend.persistenza.DBManager;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/proposta")
public class PropostaController {

    @GetMapping("/accettata")
    public Proposta getPropostaAccettataByAnnuncio(@RequestParam Long idAnnuncio){
       Proposta proposta = DBManager.getInstance().getPropostaDao().findByAnnuncioFinalizzato(idAnnuncio);
        System.out.println(proposta);
        return proposta;

    }



}
