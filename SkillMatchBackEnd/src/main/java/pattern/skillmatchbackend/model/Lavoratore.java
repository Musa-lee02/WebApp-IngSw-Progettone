package pattern.skillmatchbackend.model;


import pattern.skillmatchbackend.model.email.EmailSender;

import java.util.LinkedList;
import java.util.List;

public class Lavoratore extends Utente implements Observer {


    protected String provinciaLavoro;
    private boolean notifica_email;
    private int punteggio;
    private List<Recensione> recensioni = new LinkedList<>();
    private List<TransazionePagamento> transazionePagamento = new LinkedList<>();
    private List<Notifica> notifiche = new LinkedList<>();
    private List<Chat> chats = new LinkedList<>();
    private List<Proposta> proposte = new LinkedList<>();
    private List<Ambito> ambiti = new LinkedList<>();
    private List<Annuncio> annunciDisponibili = new LinkedList<>();

    public Lavoratore(Utente utente) {
        super(utente);
    }

    public Lavoratore(){}


    public String getProvinciaLavoro() {
        return provinciaLavoro;
    }

    public void setProvinciaLavoro(String provinciaLavoro) {
        this.provinciaLavoro = provinciaLavoro;
    }

    public boolean isNotifica_email() {
        return notifica_email;
    }

    public void setNotifica_email(boolean notifica_email) {
        this.notifica_email = notifica_email;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
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

    @Override
    public void update(Annuncio annuncio) {
        if(annuncio.getProvinciaAnnuncio().equals(provincia) && ambiti.contains(annuncio.getAmbito())){
            new EmailSender().annuncioRelativo(this,annuncio);
        }
    }
}