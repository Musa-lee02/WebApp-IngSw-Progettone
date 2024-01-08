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

    private boolean who;

    private Chat chat;

    public Messaggio() {
    }

    public Messaggio(Long idMessaggio, String contenuto, Date dataInvio, Time oraInvio, boolean letto, Utente mittente, Chat chat) {
        this.idMessaggio = idMessaggio;
        this.contenuto = contenuto;
        this.dataInvio = dataInvio;
        this.oraInvio = oraInvio;
        this.letto = letto;
        this.mittente = mittente;
        this.chat = chat;
    }

    public Long getIdMessaggio() {
        return idMessaggio;
    }

    public void setIdMessaggio(Long idMessaggio) {
        this.idMessaggio = idMessaggio;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public Date getDataInvio() {
        return dataInvio;
    }

    public void setDataInvio(Date dataInvio) {
        this.dataInvio = dataInvio;
    }

    public Time getOraInvio() {
        return oraInvio;
    }

    public void setOraInvio(Time oraInvio) {
        this.oraInvio = oraInvio;
    }

    public boolean isLetto() {
        return letto;
    }

    public void setLetto(boolean letto) {
        this.letto = letto;
    }

    public Utente getMittente() {
        return mittente;
    }

    public void setMittente(Utente mittente) {
        this.mittente = mittente;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public boolean isWho() {
        return who;
    }

public void setWho(boolean who) {
        this.who = who;
    }

}
