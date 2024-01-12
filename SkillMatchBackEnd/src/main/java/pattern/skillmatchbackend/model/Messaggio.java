package pattern.skillmatchbackend.model;

import java.sql.Timestamp;

public class Messaggio {

    private Long id;
    private String contenuto;
    private Timestamp data;
    private boolean letto;
    private boolean chi;
    private Chat chat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public boolean isLetto() {
        return letto;
    }

    public void setLetto(boolean letto) {
        this.letto = letto;
    }

    public boolean isChi() {
        return chi;
    }

    public void setChi(boolean chi) {
        this.chi = chi;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
