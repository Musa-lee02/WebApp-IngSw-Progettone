package pattern.skillmatchbackend.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pattern.skillmatchbackend.config.PasswordCrypt;
import pattern.skillmatchbackend.data.service.ImageServiceImpl;
import pattern.skillmatchbackend.data.service.interf.ImageService;
import pattern.skillmatchbackend.model.*;
import pattern.skillmatchbackend.model.email.EmailSender;
import pattern.skillmatchbackend.persistenza.DBManager;
import pattern.skillmatchbackend.persistenza.dao.LavoratoreDao;
import pattern.skillmatchbackend.persistenza.dao.postgres.LavoratoreDaoPostgres;

import java.util.Objects;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/chat")
public class ChatController {

    @PostMapping("/inviaMessaggio")
    public boolean InviaMessaggio(@RequestBody Messaggio messaggio){


        messaggio.setId((long)2);
        System.out.println(messaggio);
        DBManager.getInstance().getMessaggioDao().saveOrUpdate(messaggio);

        return true;
    }

    @PostMapping("/creaChat")
    public boolean creaChat(@RequestBody Chat chat){

         return DBManager.getInstance().getChatDao().saveOrUpdate(chat);
    }




}

