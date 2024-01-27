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

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(value= "http://localhost:4200",allowCredentials = "true")
@RequestMapping("/chat")
public class ChatController {

    @PostMapping("/inviaMessaggio")
    public boolean InviaMessaggio(@RequestBody Messaggio messaggio){



        DBManager.getInstance().getMessaggioDao().saveOrUpdate(messaggio);

        return true;
    }

    @PostMapping("/creaChat")
    public boolean creaChat(@RequestBody Chat chat){

         return DBManager.getInstance().getChatDao().saveOrUpdate(chat);
    }

    @PostMapping("/getMessaggi")
        public List<Messaggio> getMessaggi (@RequestBody Chat chat){

            return DBManager.getInstance().getMessaggioDao().findByForeignKeyChat(chat.getAnnuncio().getId(),
                    chat.getCliente().getUsername(), chat.getLavoratore().getUsername() );

        }

    @PostMapping("/getProposta")
    public Proposta getProposta(@RequestBody Chat chat){

        return DBManager.getInstance().getPropostaDao().findByChat(chat.getAnnuncio().getId(),
                chat.getCliente().getUsername(), chat.getLavoratore().getUsername());

    }

    @PostMapping("/setProposta")

    public boolean setProposta(@RequestBody Proposta proposta){

        return DBManager.getInstance().getPropostaDao().saveOrUpdate(proposta);

    }
}

