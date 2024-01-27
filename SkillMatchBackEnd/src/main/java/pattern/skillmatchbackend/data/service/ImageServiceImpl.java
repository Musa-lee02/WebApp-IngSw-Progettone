package pattern.skillmatchbackend.data.service;

import org.springframework.web.multipart.MultipartFile;
import pattern.skillmatchbackend.config.FileUtil;
import pattern.skillmatchbackend.config.PasswordCrypt;
import pattern.skillmatchbackend.data.service.interf.ImageService;
import pattern.skillmatchbackend.model.*;
import pattern.skillmatchbackend.persistenza.DBManager;

import java.io.File;

public class ImageServiceImpl implements ImageService {

    private final String relativePath = "SkillMatchBackEnd/src/main/resources/image/";

    @Override
    public Boolean insertAnnuncioAndImage(Annuncio annuncio, MultipartFile img, String token){

        Cliente cliente = DBManager.getInstance().getClienteDao().findByPrimaryKey(TokenManager.verificaToken(token));

        if(cliente != null) {

                if(img != null) {
                    try {
                        String realPathToUploads = System.getProperty("user.dir") + File.separator + relativePath;

                        if (!new File(realPathToUploads).exists()) { //If the directory "image" is not existent
                            new File(realPathToUploads).mkdir();     //Create a directory
                        }

                        if (annuncio.getImage() != null) {                                  //If the User has already a photo
                            String oldFilePath = realPathToUploads + annuncio.getImage();  //"\\"+
                            File fileToDelete = new File(oldFilePath);                  //Go to the path the old photo
                            if (fileToDelete.exists()) {                                //Check if the photo exists
                                if (fileToDelete.delete()) {                            //delete old photo
                                    System.out.println("The file has been deleted successfully");
                                }
                            }
                        }

                        String orgName = FileUtil.assignProgressiveName(img);
                        String filePath = realPathToUploads + orgName;

                        File dest = new File(filePath);
                        img.transferTo(dest);

                        annuncio.setImage(orgName);
                    } catch (Exception e) { return false; }
                }else{ annuncio.setImage("imagedefault.avif"); }
                annuncio.setCliente(cliente);
                DBManager.getInstance().getAnnuncioDao().saveOrUpdate(annuncio);

                //TODO quando l'annuncio viene correttamente creato, deve essere inviata una email (o notifica?) al cliente che ha creato l'annuncio

                return true;
            } else{ return false; }

    }

    @Override
    public Boolean insertNewLavoratoreAccountAndImageGoogle(Lavoratore lavoratore){

        Lavoratore l = DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(lavoratore.getUsername()); // Check if lavoratore is in DB

        if(l == null) {

            try {

                if(lavoratore.getImgProfilo() == null) {
                    lavoratore.setImgProfilo("default.jpg");
                }

                String passC = lavoratore.getPassword();
                lavoratore.setPassword(PasswordCrypt.encode(passC));

                DBManager.getInstance().getLavoratoreDao().saveOrUpdate(lavoratore);

                return true;
            } catch (Exception e) { return false; }
        }else{ return false; }

    }


    @Override
    public Boolean insertNewLavoratoreAccountAndImage(Lavoratore lavoratore, MultipartFile img){

        Lavoratore l = DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(lavoratore.getUsername()); // Check if lavoratore is in DB

        if(l == null) {

            try {
                if(img != null) {
                    String realPathToUploads = System.getProperty("user.dir") + File.separator + relativePath;

                    if (!new File(realPathToUploads).exists()) { //If the directory "image" is not existent
                        new File(realPathToUploads).mkdir();     //Create a directory
                    }

                    String orgName = FileUtil.assignProgressiveName(img);
                    String filePath = realPathToUploads + orgName;

                    File dest = new File(filePath);
                    img.transferTo(dest);

                    lavoratore.setImgProfilo(orgName);
                }else{
                    lavoratore.setImgProfilo("default.jpg");
                }

                String passC = lavoratore.getPassword();
                lavoratore.setPassword(PasswordCrypt.encode(passC));


                DBManager.getInstance().getLavoratoreDao().saveOrUpdate(lavoratore);

                return true;
            } catch (Exception e) { return false; }
        }else{ return false; }

    }

    @Override
    public Boolean insertNewClienteAccountAndImage(Cliente cliente){

        Cliente c = DBManager.getInstance().getClienteDao().findByPrimaryKey(cliente.getUsername()); // Check if cliente is in DB

        if(c == null) {

            try {
                String realPathToUploads = System.getProperty("user.dir") + File.separator + relativePath;

                if (!new File(realPathToUploads).exists()) { //If the directory "image" is not existent
                    new File(realPathToUploads).mkdir();     //Create a directory
                }

                String passC = cliente.getPassword();
                cliente.setPassword(PasswordCrypt.encode(passC));
                cliente.setImgProfilo("default.jpg");

                DBManager.getInstance().getClienteDao().saveOrUpdate(cliente);

                return true;
            } catch (Exception e) { return false; }
        }else{ return false; }

    }


}
