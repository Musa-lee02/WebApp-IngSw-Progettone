package pattern.skillmatchbackend.model;


import java.util.LinkedList;
import java.util.List;

public class Lavoratore extends Utente{

    private String 

    private List<Ambito> ambito;

    private List<Proposta> proposte;
    private List<Annuncio> annunciPerMe;

    private List<Chat> chatList;

    private List<TransazionePagamento>  transazioni;

    private List<Recensione> recensioniAvute;

    private List<TokenRegistrazione> tokenRegistrazione;



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
}