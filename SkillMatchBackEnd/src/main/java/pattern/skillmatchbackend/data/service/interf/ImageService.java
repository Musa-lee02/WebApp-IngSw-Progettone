package pattern.skillmatchbackend.data.service.interf;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import pattern.skillmatchbackend.model.Annuncio;
import pattern.skillmatchbackend.model.Cliente;
import pattern.skillmatchbackend.model.Lavoratore;

public interface ImageService {
    Boolean insertAnnuncioAndImage(Annuncio annuncio, MultipartFile img, String token);

    Boolean insertNewLavoratoreAccountAndImage(Lavoratore lavoratore, MultipartFile img);

    Boolean insertNewClienteAccountAndImage(Cliente cliente);


}
