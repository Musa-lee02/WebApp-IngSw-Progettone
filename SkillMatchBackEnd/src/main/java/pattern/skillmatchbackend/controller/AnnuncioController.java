package pattern.skillmatchbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import pattern.skillmatchbackend.model.Ambito;
import org.springframework.web.multipart.MultipartFile;
import pattern.skillmatchbackend.data.service.ImageServiceImpl;
import pattern.skillmatchbackend.model.Annuncio;
import pattern.skillmatchbackend.model.Cliente;
import pattern.skillmatchbackend.model.TokenManager;
import pattern.skillmatchbackend.persistenza.DBManager;


import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200" )
@RequestMapping("/annuncio")
public class AnnuncioController {

    private final ImageServiceImpl imageService = new ImageServiceImpl();

    //Funziona
    @GetMapping("/getAnnunci")
    public List<Annuncio> getAnnunci(){
        System.out.println(DBManager.getInstance().getAnnuncioDao().findAll().size());
        return DBManager.getInstance().getAnnuncioDao().findAll();
    }

    //Funziona
    @PostMapping("/insertNewAnnuncio")
    public ResponseEntity<Boolean> insertNewAnnuncio(@RequestPart("annuncio") Annuncio annuncio, @RequestPart(value = "img", required = false) MultipartFile img, @RequestPart(value = "token") String token){
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

        System.out.println(ambito+provincia);

        List<Annuncio> annunci = new LinkedList<>();

        if(ambito.equals("all") && provincia.equals("all"))
            annunci=DBManager.getInstance().getAnnuncioDao().findAll();
        else if (ambito.equals("all"))
            annunci=DBManager.getInstance().getAnnuncioDao().getAnnunciByProvincia(provincia);

        else if (provincia.equals("all"))
            annunci=DBManager.getInstance().getAnnuncioDao().getAnnunciByAmbito(ambito);
        else
            annunci=DBManager.getInstance().getAnnuncioDao().getAnnunciByAmbitoAndProvincia(ambito,provincia);


        /*if(annunci.isEmpty())
            return DBManager.getInstance().getAnnuncioDao().findAll();*/

        return annunci;

    }

    //Funziona
    @GetMapping("/getAnnunciWithToken/{token}") // devo vedere nel front come si chiama la func
    public List<Annuncio> getAnnunciWithToken(@PathVariable("token") String token){

        return DBManager.getInstance().getAnnuncioDao().findByForeignKeyCliente(TokenManager.verificaToken(token));
    }

    @GetMapping("/getAnnunciWithTokenLavoratore/{token}")  // devo vedere nel front come si chiama la func

    public List<Annuncio> getAnnunciWithTokenLavoratore(@PathVariable("token") String token){


        for(Annuncio annuncio : DBManager.getInstance().getAnnuncioDao().findByLavoratore(TokenManager.verificaToken(token))){
            System.out.println(annuncio.getId());
        }
        return DBManager.getInstance().getAnnuncioDao().findByLavoratore(TokenManager.verificaToken(token));
    }

    @GetMapping("/getAnnunciWithChat/{token}")

    public List<Annuncio> getAnnunciWithChat(@PathVariable("token") String token){

        return DBManager.getInstance().getAnnuncioDao().getAnnunciWithChat(TokenManager.verificaToken(token));
    }

    // In teoria andrebbe fatto col token
    @PostMapping("/getAnnunciByUsernameCliente")
    public List<Annuncio> getAnnunciByUsernameCliente(@RequestBody Cliente cliente){
        return DBManager.getInstance().getAnnuncioDao().findByForeignKeyCliente(cliente.getUsername());
    }

    @GetMapping("/getAnnunciFinalizzati/{token}")
    public List<Annuncio> getAnnunciFinalizzati(@PathVariable("token") String token){

        for(Annuncio annuncio : DBManager.getInstance().getAnnuncioDao().getAnnunciFinalizzati(TokenManager.verificaToken(token)))
        {
            System.out.println(annuncio);
        }
        return DBManager.getInstance().getAnnuncioDao().getAnnunciFinalizzati(TokenManager.verificaToken(token));
    }







////

}
