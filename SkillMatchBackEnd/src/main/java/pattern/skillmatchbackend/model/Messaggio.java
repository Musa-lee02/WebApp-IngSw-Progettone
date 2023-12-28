package pattern.skillmatchbackend.model;

import java.sql.Date;
import java.sql.Time;

public class Messaggio {

    private Long idMessaggio;
    private String contenuto;

    private Date dataInvio;
    private Time oraInvio;
    boolean letto;
    private Utente mittente;

    private Chat chat;




}
