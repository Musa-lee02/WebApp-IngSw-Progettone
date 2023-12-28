package pattern.skillmatchbackend.model;

import java.sql.Date;

public class TransazionePagamento {
    private Long idTransazione;

    private Date dataTransazione;

    private Double importo;

    private Cliente mittente;
    private Lavoratore destinatario;
    private String metodoPagamento;

}
