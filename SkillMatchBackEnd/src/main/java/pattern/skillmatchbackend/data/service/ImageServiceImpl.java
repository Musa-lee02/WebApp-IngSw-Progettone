package pattern.skillmatchbackend.data.service;

import org.springframework.web.multipart.MultipartFile;
import pattern.skillmatchbackend.config.FileUtil;
import pattern.skillmatchbackend.data.service.interf.ImageService;
import pattern.skillmatchbackend.model.Annuncio;
import pattern.skillmatchbackend.model.Cliente;
import pattern.skillmatchbackend.model.Lavoratore;
import pattern.skillmatchbackend.model.Utente;
import pattern.skillmatchbackend.persistenza.DBManager;

import java.io.File;

public class ImageServiceImpl implements ImageService {

    private final String relativePath = "SkillMatchBackEnd/src/main/resources/image/";

    @Override
    public Boolean insertAnnuncioAndImage(Annuncio a, MultipartFile img){

        Annuncio annuncio = DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(a.getId()); // Check if annuncio is in DB

        if(annuncio == null) {

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

                DBManager.getInstance().getAnnuncioDao().saveOrUpdate(annuncio);

                return true;
            } catch (Exception e) { return false; }
        }else{ return false; }

    }

    @Override
    public Boolean insertNewLavoratoreAccountAndImage(Lavoratore l, MultipartFile img){

        Lavoratore lavoratore = DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(l.getUsername()); // Check if lavoratore is in DB

        if(lavoratore == null) {

            try {
                String realPathToUploads = System.getProperty("user.dir") + File.separator + relativePath;

                if (!new File(realPathToUploads).exists()) { //If the directory "image" is not existent
                    new File(realPathToUploads).mkdir();     //Create a directory
                }

                String orgName = FileUtil.assignProgressiveName(img);
                String filePath = realPathToUploads + orgName;

                File dest = new File(filePath);
                img.transferTo(dest);

                lavoratore.setImgProfilo(orgName);

                DBManager.getInstance().getLavoratoreDao().saveOrUpdate(lavoratore);

                return true;
            } catch (Exception e) { return false; }
        }else{ return false; }

    }

    @Override
    public Boolean insertNewClienteAccountAndImage(Cliente c){

        Cliente cliente = DBManager.getInstance().getClienteDao().findByPrimaryKey(c.getUsername()); // Check if cliente is in DB

        if(cliente == null) {

            try {
                String realPathToUploads = System.getProperty("user.dir") + File.separator + relativePath;

                if (!new File(realPathToUploads).exists()) { //If the directory "image" is not existent
                    new File(realPathToUploads).mkdir();     //Create a directory
                }

                cliente.setImgProfilo("default.jpg");

                DBManager.getInstance().getClienteDao().saveOrUpdate(cliente);

                return true;
            } catch (Exception e) { return false; }
        }else{ return false; }

    }


}
