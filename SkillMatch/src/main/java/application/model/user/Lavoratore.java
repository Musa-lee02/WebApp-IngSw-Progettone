package application.model.user;

import application.model.Proposta;

import java.util.LinkedList;
import java.util.List;

public class Lavoratore extends Utente{

    private List<Proposta> proposte = new LinkedList<>();


    public List<Proposta> getProposte() {
        return proposte;
    }

    public void setProposte(List<Proposta> proposte) {
        this.proposte = proposte;
    }
}
