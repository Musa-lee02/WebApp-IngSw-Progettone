package pattern.skillmatchbackend.model;


import java.util.LinkedList;
import java.util.List;

public class Cliente extends Utente {

    private long id;
    private List<Recensione> recensioni = new LinkedList<>();
    private List<TransazionePagamento> transazionePagamento = new LinkedList<>();
    private List<Notifica> notifiche = new LinkedList<>();
    private List<Chat> chats = new LinkedList<>();
    private List<Annuncio> annunci = new LinkedList<>();

    public Cliente(Utente utente) {
        super(utente);
    }

    public Cliente(){}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(List<Recensione> recensioni) {
        this.recensioni = recensioni;
    }

    public List<TransazionePagamento> getTransazionePagamento() {
        return transazionePagamento;
    }

    public void setTransazionePagamento(List<TransazionePagamento> transazionePagamento) {
        this.transazionePagamento = transazionePagamento;
    }

    public List<Notifica> getNotifiche() {
        return notifiche;
    }

    public void setNotifiche(List<Notifica> notifiche) {
        this.notifiche = notifiche;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }


    public List<Annuncio> getAnnunci() {
        return annunci;
    }

    public void setAnnunci(List<Annuncio> annunci) {
        this.annunci = annunci;
    }


}
