package pattern.skillmatchbackend.controller;

import org.springframework.web.bind.annotation.*;
import pattern.skillmatchbackend.model.Cliente;
import pattern.skillmatchbackend.model.Lavoratore;
import pattern.skillmatchbackend.model.Notifica;
import pattern.skillmatchbackend.persistenza.DBManager;

import java.util.List;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/ambito")
public class NotificaController {

    @PostMapping("/getNotificheByUsernameCliente")
    public List<Notifica> getAnnunciByUsernameCliente(@RequestBody Cliente cliente){

        return DBManager.getInstance().getNotificaDao().findByForeignKeyCliente(cliente.getUsername());

    }

    @PostMapping("/getNotificheByUsernameLavoratoe")
    public List<Notifica> getAnnunciByUsernamelavoratore(@RequestBody Lavoratore lavoratore){

        return DBManager.getInstance().getNotificaDao().findByForeignKeyCliente(lavoratore.getUsername());

    }
////
}
