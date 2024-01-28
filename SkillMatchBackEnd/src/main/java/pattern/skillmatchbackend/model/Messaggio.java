package pattern.skillmatchbackend.model;

import java.sql.Timestamp;

public class Messaggio {

    private Long id;
    private String contenuto;
    private Timestamp data;

    private boolean inviato;
    private boolean isLavoratore;
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

    public boolean isInviato() {
        return inviato;
    }

    public void setInviato(boolean inviato) {
        this.inviato = inviato;
    }

    public boolean isLavoratore() {
        return isLavoratore;
    }

    public void setLavoratore(boolean isLavoratore){this.isLavoratore=isLavoratore;}

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    @Override
    public String toString() {
        return "Messaggio{" +
                ", contenuto='" + contenuto + '\'' +
                ", data=" + data +
                ", letto=" + inviato +
                ", isLavoratore=" + isLavoratore +
                '}';
    }
}
