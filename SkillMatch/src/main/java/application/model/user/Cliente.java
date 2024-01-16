package application.model.user;

import application.model.Annuncio;

import java.util.LinkedList;
import java.util.List;

public class Cliente extends  Utente{

    private List<Annuncio> annunci = new LinkedList<>();

    public List<Annuncio> getAnnunci() {
        return annunci;
    }

    public void setAnnunci(List<Annuncio> annunci) {
        this.annunci = annunci;
    }
}
