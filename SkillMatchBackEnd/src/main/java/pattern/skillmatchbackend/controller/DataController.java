package pattern.skillmatchbackend.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pattern.skillmatchbackend.model.Cliente;
import pattern.skillmatchbackend.model.Lavoratore;
import pattern.skillmatchbackend.persistenza.DBManager;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/data")
public class DataController {


    /*@PostMapping("/cliente/signUp")
    public void insetCliente(@RequestBody Cliente cliente){
        System.out.println("ciao");
    }*/
    @PostMapping("/cliente/signUp")
    public ResponseEntity<String> registerStep1(@RequestBody String cliente , HttpSession session) {

        System.out.println("ciao");

        return ResponseEntity.ok().body("ok");
    }
}