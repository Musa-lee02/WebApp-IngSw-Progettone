package pattern.skillmatchbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;
import pattern.skillmatchbackend.config.FileUtil;
import pattern.skillmatchbackend.model.Annuncio;
import pattern.skillmatchbackend.model.Utente;
import pattern.skillmatchbackend.persistenza.DBManager;

import java.io.File;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/images")
public class ImageController {

    private final String relativePath = "SkillMatchBackEnd/src/main/resources/image/";

    //Testato e funzionante
    @GetMapping("/{imageName}")
    public ResponseEntity<Resource> getImageById(@PathVariable("imageName") String imageName) {

        String fullPath = relativePath + imageName;

        Resource resource = new FileSystemResource(fullPath);

        if (resource.exists()){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resource);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}

