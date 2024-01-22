package pattern.skillmatchbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pattern.skillmatchbackend.data.service.ImageServiceImpl;
import pattern.skillmatchbackend.model.Annuncio;
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
        return DBManager.getInstance().getAnnuncioDao().findAll();
    }

    //TODO da testare
    @PostMapping("/insertNewAnnuncio")
    public ResponseEntity<Boolean> insertNewAnnuncio(@RequestPart("annuncio") Annuncio annuncio, @RequestPart("img") MultipartFile img){

        return ResponseEntity.ok(imageService.insertAnnuncioAndImage(annuncio, img));

        //DBManager.getInstance().getAnnuncioDao().saveOrUpdate(annuncio);
    }

    //TODO da testare
    @PostMapping("/modifyAnnuncio")
    public void modifyAnnuncio(@RequestBody Annuncio annuncio){
        DBManager.getInstance().getAnnuncioDao().saveOrUpdate(annuncio);
    }



}
