package pattern.skillmatchbackend.controller;

import org.springframework.web.bind.annotation.*;
import pattern.skillmatchbackend.model.*;
import pattern.skillmatchbackend.persistenza.DBManager;

import java.util.List;

@RestController
@CrossOrigin(value= "http://localhost:4200",allowCredentials = "true")
@RequestMapping("/chat")
public class ChatController {

    @PostMapping("/inviaMessaggio")
    public boolean InviaMessaggio(@RequestBody Messaggio messaggio){

        System.out.println(messaggio.isLavoratore()+" "+messaggio.isInviato());
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

    @PostMapping("/accettaProposta")
    public boolean accettaProposta(@RequestBody Proposta proposta){
        return DBManager.getInstance().getPropostaDao().saveOrUpdate(proposta);
    }
}

