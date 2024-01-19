package pattern.skillmatchbackend.controller;

import org.springframework.web.bind.annotation.*;

import pattern.skillmatchbackend.model.Cliente;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/data")
public class DataController {




    @PostMapping("/cliente/signup")
    public void insetCliente(@RequestBody Cliente cliente){
        System.out.println("ciao");
    }



}