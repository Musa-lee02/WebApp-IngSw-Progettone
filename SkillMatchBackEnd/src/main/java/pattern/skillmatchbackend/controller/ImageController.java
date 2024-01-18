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

    //TODO controllare se avere la classe 'Image' è utile

    private final String relativePath = "src/main/resources/image/";


    //TODO da testare
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


    //TODO da testare
    @PostMapping("/utenteAccountImage")
    public ResponseEntity<Boolean> insertUtenteAccountImage(@RequestBody Utente utente, MultipartFile img){

        Utente u = DBManager.getInstance().getUtenteDao().findByPrimaryKey(utente.getUsername()); // Check if the user is in DB

        try {
            String realPathToUploads = System.getProperty("user.dir") + File.separator + relativePath;

            if (!new File(realPathToUploads).exists()) { //If the directory "image" is not existent
                new File(realPathToUploads).mkdir();     //Create a directory
            }

            if(u.getImgProfilo().getPath() != null) {                                  //If the User has already a photo
                String oldFilePath = realPathToUploads + u.getImgProfilo().getPath();  //"\\"+
                File fileToDelete = new File(oldFilePath);                  //Go to the path the old photo
                if (fileToDelete.exists()) {                                //Check if the photo exists
                    if (fileToDelete.delete()) {                            //delete old photo
                        System.out.println("The file has been deleted successfully");
                    }
                }
            }

            String orgName = FileUtil.assignProgressiveName(img);
            String filePath = realPathToUploads + orgName; //"\\"+

            File dest = new File(filePath);
            img.transferTo(dest);

            u.getImgProfilo().setPath(orgName);

            DBManager.getInstance().getUtenteDao().saveOrUpdate(u);

            return ResponseEntity.ok(true);
        }catch (Exception e){ return ResponseEntity.ok(false); }

    }


    //TODO da testare
    @PostMapping("/annuncioImage")
    public ResponseEntity<Boolean> insertAnnuncioImage(@RequestBody Annuncio annuncio, MultipartFile img){

        Annuncio a = DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(annuncio.getId()); // Check if annuncio is in DB

        try {
            String realPathToUploads = System.getProperty("user.dir") + File.separator + relativePath;

            if (!new File(realPathToUploads).exists()) { //If the directory "image" is not existent
                new File(realPathToUploads).mkdir();     //Create a directory
            }

            if(a.getImage().getPath() != null) {                                  //If the User has already a photo
                String oldFilePath = realPathToUploads + a.getImage().getPath();  //"\\"+
                File fileToDelete = new File(oldFilePath);                  //Go to the path the old photo
                if (fileToDelete.exists()) {                                //Check if the photo exists
                    if (fileToDelete.delete()) {                            //delete old photo
                        System.out.println("The file has been deleted successfully");
                    }
                }
            }

            String orgName = FileUtil.assignProgressiveName(img);
            String filePath = realPathToUploads + orgName; //"\\"+

            File dest = new File(filePath);
            img.transferTo(dest);

            a.getImage().setPath(orgName);

            DBManager.getInstance().getAnnuncioDao().saveOrUpdate(a);

            return ResponseEntity.ok(true);
        }catch (Exception e){ return ResponseEntity.ok(false); }

    }


    //TODO da testare
    @PostMapping("/deleteUtenteAccountImage")
    public ResponseEntity<Boolean> deleteUtenteAccountImage(@RequestBody Utente utente, MultipartFile img) {

        Utente u = DBManager.getInstance().getUtenteDao().findByPrimaryKey(utente.getUsername()); // Check if the user is in DB
        String newPath =  relativePath + u.getImgProfilo().getPath();

        File file = new File(newPath);

        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("File deleted successfully");
                u.getImgProfilo().setPath(null); // Non so se nel db questo è un campo NOT NULL, non dovrebbe ma lo scrivo lo stesso just in case
                DBManager.getInstance().getUtenteDao().saveOrUpdate(u);
                return ResponseEntity.ok(true);
            } else {
                System.out.println("Impossible to eliminate the file");
                return ResponseEntity.ok(false);
            }
        } else {
            System.out.println("The file is non existent or isn't in a valid file type");
            return ResponseEntity.ok(false);
        }
    }

}