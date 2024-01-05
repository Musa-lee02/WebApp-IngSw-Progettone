package pattern.skillmatchbackend.model;



import java.util.LinkedList;
import java.util.List;

public class Proposta {

    private String titolo;

    private String durata;

    private String stato;
    private Float prezzoLavoro;
    private Annuncio annuncioRelativo;
    private Lavoratore lavoratore;

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public String getDurata() {
        return durata;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Float getPrezzoLavoro() {
        return prezzoLavoro;
    }

    public void setPrezzoLavoro(Float prezzoLavoro) {
        this.prezzoLavoro = prezzoLavoro;
    }

    public Annuncio getAnnuncioRelativo() {
        return annuncioRelativo;
    }

    public void setAnnuncioRelativo(Annuncio annuncioRelativo) {
        this.annuncioRelativo = annuncioRelativo;
    }

    public Lavoratore getLavoratore() {
        return lavoratore;
    }

    public void setLavoratore(Lavoratore lavoratore) {
        this.lavoratore = lavoratore;
    }
}