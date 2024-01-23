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
    public Boolean insertAnnuncioAndImage(Annuncio annuncio, MultipartFile img){

        //Le cose commentate servono tutte, solo che richiedono il backend

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
            String filePath = realPathToUploads + orgName;

            File dest = new File(filePath);
            img.transferTo(dest);

            a.getImage().setPath(orgName);

            DBManager.getInstance().getAnnuncioDao().saveOrUpdate(a);

            return true;
        }catch (Exception e){ return false; }

    }

    @Override
    public Boolean insertNewLavoratoreAccountAndImage(Lavoratore lavoratore, MultipartFile img){



        try {
            String realPathToUploads = System.getProperty("user.dir") + File.separator + relativePath;

            if (!new File(realPathToUploads).exists()) { //If the directory "image" is not existent
                new File(realPathToUploads).mkdir();     //Create a directory
            }

            String orgName = FileUtil.assignProgressiveName(img);
            String filePath = realPathToUploads + orgName;

            File dest = new File(filePath);
            img.transferTo(dest);

            lavoratore.getImgProfilo().setPath(orgName);


            DBManager.getInstance().getLavoratoreDao().saveOrUpdate(lavoratore);

            return true;
        }catch (Exception e){ return false; }

    }

    @Override
    public Boolean insertNewClienteAccountAndImage(Cliente cliente){

        try {
            String realPathToUploads = System.getProperty("user.dir") + File.separator + relativePath;

            if (!new File(realPathToUploads).exists()) { //If the directory "image" is not existent
                new File(realPathToUploads).mkdir();     //Create a directory
            }

            cliente.getImgProfilo().setPath("default.jpg");

            DBManager.getInstance().getClienteDao().saveOrUpdate(cliente);

            return true;
        }catch (Exception e){ return false; }

    }


}
