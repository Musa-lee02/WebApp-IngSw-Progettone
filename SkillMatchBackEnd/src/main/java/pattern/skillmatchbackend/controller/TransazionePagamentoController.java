package pattern.skillmatchbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pattern.skillmatchbackend.model.Cliente;
import pattern.skillmatchbackend.model.Lavoratore;
import pattern.skillmatchbackend.model.TransazionePagamento;
import pattern.skillmatchbackend.persistenza.DBManager;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/transazionepagamento")
public class TransazionePagamentoController {

    @PostMapping("/doTransazionePagamento")
    public void doTransazionePagamento(TransazionePagamento transazionePagamento){
        DBManager.getInstance().getTransazionePagamentoDao().saveOrUpdate(transazionePagamento);
    }

}
