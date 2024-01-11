package pattern.skillmatchbackend.model;


import java.util.LinkedList;
import java.util.List;

public class Lavoratore extends Utente {



    private long id;
    private List<Recensione> recensioni = new LinkedList<>();
    private List<TransazionePagamento> transazionePagamento = new LinkedList<>();
    private List<Notifica> notifiche = new LinkedList<>();
    private List<Chat> chats = new LinkedList<>();
    private Utente utente;
    private List<Proposta> proposte = new LinkedList<>();
    private List<Ambito> ambiti = new LinkedList<>();
    private List<Annuncio> annunciDisponibili = new LinkedList<>();



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

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public List<Proposta> getProposte() {
        return proposte;
    }

    public void setProposte(List<Proposta> proposte) {
        this.proposte = proposte;
    }

    public List<Ambito> getAmbiti() {
        return ambiti;
    }

    public void setAmbiti(List<Ambito> ambiti) {
        this.ambiti = ambiti;
    }

    public List<Annuncio> getAnnunciDisponibili() {
        return annunciDisponibili;
    }

    public void setAnnunciDisponibili(List<Annuncio> annunciDisponibili) {
        this.annunciDisponibili = annunciDisponibili;
    }
}