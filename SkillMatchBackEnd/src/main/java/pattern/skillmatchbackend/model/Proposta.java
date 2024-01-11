package pattern.skillmatchbackend.model;


public class Proposta {

    private String titolo;

    private String descrizione;

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

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
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