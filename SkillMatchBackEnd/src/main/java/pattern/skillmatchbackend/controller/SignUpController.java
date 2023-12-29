package pattern.skillmatchbackend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pattern.skillmatchbackend.model.Cliente;
import pattern.skillmatchbackend.model.Utente;
import pattern.skillmatchbackend.persistenza.DBManager;
import pattern.skillmatchbackend.persistenza.dao.ClienteDao;

@RestController
public class SignUpController {

    @PostMapping("/user-signup")
    public String signup(@RequestBody Cliente cliente){

        DBManager.getInstance()


        Utente utente = DBManager.getInstance().getUtenteDao().findByPrimaryKey(username);

        Cliente cliente1 = new Cliente();




        ClienteDao clienteDao =


        return "OK";
    }

}
