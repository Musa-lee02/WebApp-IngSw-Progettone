package pattern.skillmatchbackend.model;


import java.util.LinkedList;
import java.util.List;

public class Lavoratore extends Utente{

    private String provinciaLavoro;

    private List<Ambito> ambito;

    private List<Proposta> proposte;
    private List<Annuncio> annunciPerMe;

    private List<Chat> chatList;

    private List<TransazionePagamento>  transazioni;

    private List<Recensione> recensioni;



    public List<Ambito> getAmbito() {
        return ambito;
    }

    public void setAmbito(List<Ambito> ambito) {
        this.ambito = ambito;
    }

    public List<Proposta> getProposte() {
        return proposte;
    }

    public void setProposte(List<Proposta> proposte) {
        this.proposte = proposte;
    }

    public List<Annuncio> getAnnunciPerMe() {
        return annunciPerMe;
    }

    public void setAnnunciPerMe(List<Annuncio> annunciPerMe) {
        this.annunciPerMe = annunciPerMe;
    }

    public List<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }

    public List<TransazionePagamento> getTransazioni() {
        return transazioni;
    }
    public void setTransazioni(List<TransazionePagamento> transazioni) {
        this.transazioni = transazioni;
    }

    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(List<Recensione> recensioniAvute) {
        this.recensioni = recensioniAvute;
    }


    public void setProvinciaServizio(String provinciaDiServizio) {
        this.provinciaLavoro = provinciaDiServizio;
    }

    public String getProvinciaServizio() {
        return provinciaLavoro;
    }
}