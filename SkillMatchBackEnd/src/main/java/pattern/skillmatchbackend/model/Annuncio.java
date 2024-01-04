package pattern.skillmatchbackend.model;

import java.sql.Date;
import java.util.List;

public class Annuncio {
    private Long idAnnuncio;
    private String titolo;
    private String descrizione;
    private Date data_di_scadenza;
    private String provinciaAnnuncio;
    private Cliente cliente;

    private List<Ambiti> ambiti;


}
