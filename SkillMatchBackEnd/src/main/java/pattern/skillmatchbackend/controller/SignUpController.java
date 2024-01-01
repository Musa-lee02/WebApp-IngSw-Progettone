package pattern.skillmatchbackend.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pattern.skillmatchbackend.model.Cliente;
import pattern.skillmatchbackend.model.Utente;
import pattern.skillmatchbackend.persistenza.DBManager;
import pattern.skillmatchbackend.persistenza.dao.ClienteDao;

@RestController
public class SignUpController {

    @PostMapping("/Cliente/signup/passo1")
    public ResponseEntity<?> registerStep1(@RequestBody Cliente cliente, HttpSession session) {
        ClienteDao clienteDao = DBManager.getInstance().getClienteDao();
        Cliente cliente1 = clienteDao.findByUsername(cliente.getUsername());
        System.out.println("cliente1: " + cliente1);
        if (cliente1 != null) {
            return ResponseEntity.badRequest().body("Username già in uso");
        }
        //clienteDao.save(cliente);
        return ResponseEntity.ok().body("Cliente registrato");
    }


}
