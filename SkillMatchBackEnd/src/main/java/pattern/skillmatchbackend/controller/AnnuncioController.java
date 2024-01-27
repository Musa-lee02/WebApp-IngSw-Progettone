package pattern.skillmatchbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pattern.skillmatchbackend.model.Ambito;
import org.springframework.web.multipart.MultipartFile;
import pattern.skillmatchbackend.data.service.ImageServiceImpl;
import pattern.skillmatchbackend.model.Annuncio;
import pattern.skillmatchbackend.model.Cliente;
import pattern.skillmatchbackend.model.TokenManager;
import pattern.skillmatchbackend.persistenza.DBManager;


import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/annuncio")
public class AnnuncioController {

    private final ImageServiceImpl imageService = new ImageServiceImpl();

    //TODO da testare
    @GetMapping("/getAnnunci")
    public List<Annuncio> getAnnunci(){
        System.out.println("dd");
        return DBManager.getInstance().getAnnuncioDao().findAll();
    }

    //Funziona
    @PostMapping("/insertNewAnnuncio")
    public ResponseEntity<Boolean> insertNewAnnuncio(@RequestPart("annuncio") Annuncio annuncio, @RequestPart(value = "img", required = false) MultipartFile img, @RequestPart(value = "token") String token){
        System.out.println("token is:" + token);
        System.out.println("token is:" + TokenManager.verificaToken(token));

        return ResponseEntity.ok(imageService.insertAnnuncioAndImage(annuncio, img, token));
    }

    //TODO da testare
    @PostMapping("/modifyAnnuncio")
    public void modifyAnnuncio(@RequestBody Annuncio annuncio){
        DBManager.getInstance().getAnnuncioDao().saveOrUpdate(annuncio);
    }

    //non so se si possono mettere 2 parametri requestbody e non so come generare http per 2 parametri
    @GetMapping("/getAnnunciByAmbitoEZona")
    public List<Annuncio> getAnnunciByAmbitoEZona(@RequestParam String ambito, @RequestParam String provincia){


        System.out.println(provincia);

        List<Annuncio> annunci =  DBManager.getInstance().getAnnuncioDao().annunciPerMe(ambito,provincia);

        if(annunci == null)
            return DBManager.getInstance().getAnnuncioDao().findAll();

        return annunci;

    }

    @PostMapping("/getAnnunciWithToken")
    public List<Annuncio> getAnnunciWithToken(String token){
        System.out.println(token);
        System.out.println(TokenManager.verificaToken(token));
        return DBManager.getInstance().getAnnuncioDao().findByForeignKeyCliente(TokenManager.verificaToken(token));
    }

    // In teoria andrebbe fatto col token
    @PostMapping("/getAnnunciByUsernameCliente")
    public List<Annuncio> getAnnunciByUsernameCliente(@RequestBody Cliente cliente){
        return DBManager.getInstance().getAnnuncioDao().findByForeignKeyCliente(cliente.getUsername());
    }

////

}
