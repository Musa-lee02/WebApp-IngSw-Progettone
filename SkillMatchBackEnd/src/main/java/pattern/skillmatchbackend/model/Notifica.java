package pattern.skillmatchbackend.model;

import java.sql.Timestamp;

public class Notifica {

    private long id;
    private String contenuto;
    private Timestamp data;
    private boolean visualizzato;
    private boolean chi;
    private Utente utente;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public boolean isVisualizzato() {
        return visualizzato;
    }

    public void setVisualizzato(boolean visualizzato) {
        this.visualizzato = visualizzato;
    }

    public boolean isChi() {
        return chi;
    }

    public void setChi(boolean chi) {
        this.chi = chi;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
