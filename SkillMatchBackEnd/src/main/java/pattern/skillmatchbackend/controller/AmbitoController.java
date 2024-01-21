package pattern.skillmatchbackend.controller;

import org.springframework.web.bind.annotation.*;
import pattern.skillmatchbackend.model.Ambito;
import pattern.skillmatchbackend.persistenza.DBManager;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/ambito")
public class AmbitoController {

    @GetMapping("/getAmbiti")
    public List<Ambito> getAmbiti(){
        return DBManager.getInstance().getAmbitoDao().findAll();
    }


}