package pattern.skillmatchbackend.model;


import java.util.List;

public class Cliente extends Utente {

    private List<Chat> chatList;

    private List<Annuncio> annunciPubblicati;

    private List<Recensione> RecensioniScritte;

    private TokenRegistrazione tokenRegistrazione;

    private List<TransazionePagamento> transazioni;

    public List<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }

    public List<Annuncio> getAnnunci() {
        return annunciPubblicati;
    }

    public void setAnnunci(List<Annuncio> annunciPubblicati) {
        this.annunciPubblicati = annunciPubblicati;
    }

    public List<Recensione> getRecensioni() {
        return RecensioniScritte;
    }

    public void setRecensioni(List<Recensione> recensioniScritte) {
        RecensioniScritte = recensioniScritte;
    }

    public TokenRegistrazione getTokenRegistrazione() {
        return tokenRegistrazione;
    }

    public void setTokenRegistrazione(TokenRegistrazione tokenRegistrazione) {
        this.tokenRegistrazione = tokenRegistrazione;
    }

    public List<TransazionePagamento> getTransazioni() {
        return transazioni;
    }

    public void setTransazioni(List<TransazionePagamento> transazioni) {
        this.transazioni = transazioni;
    }













}
