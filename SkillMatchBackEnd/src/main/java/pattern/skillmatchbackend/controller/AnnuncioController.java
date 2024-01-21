package pattern.skillmatchbackend.controller;

import org.springframework.web.bind.annotation.*;
import pattern.skillmatchbackend.model.Ambito;
import pattern.skillmatchbackend.model.Annuncio;
import pattern.skillmatchbackend.model.Cliente;
import pattern.skillmatchbackend.persistenza.DBManager;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/annuncio")
public class AnnuncioController {

    //TODO da testare
    @GetMapping("/getAnnunciAll")
    public List<Annuncio> getAnnunciAll(){
        return DBManager.getInstance().getAnnuncioDao().findAll();
    }

    //TODO da testare
    @PostMapping("/insertNewAnnuncio")
    public void insertNewAnnuncio(@RequestBody Annuncio annuncio){
        DBManager.getInstance().getAnnuncioDao().saveOrUpdate(annuncio);
    }

    //non so se si possono mettere 2 parametri requestbody e non so come generare http per 2 parametri
    @PostMapping("/getAnnunciByAmbitoEZona")
    public List<Annuncio> getAnnunciByAmbitoEZona(@RequestBody Ambito ambito,@RequestBody String provincia){

        System.out.println(ambito.getNome());
        System.out.println(provincia);

        List<Annuncio> annunci =  DBManager.getInstance().getAnnuncioDao().annunciPerMe(ambito.getNome(),provincia);

        if(annunci == null)
            return DBManager.getInstance().getAnnuncioDao().findAll();

        return annunci;

    }

    @PostMapping("/getAnnunciByUsernameCliente")
    public List<Annuncio> getAnnunciByUsernameCliente(@RequestBody  Cliente cliente){

        return DBManager.getInstance().getAnnuncioDao().findByForeignKeyCliente(cliente.getUsername());
    }

////

}
