package pattern.skillmatchbackend.model;


import java.sql.Date;

public class Proposta {

    private Date data_lavoro;
    private String descrizione;
    private String stato;
    private String stato_lavoro;
    private Float prezzoLavoro;
    private Annuncio annuncioRelativo;
    private Lavoratore lavoratore;

    public Date getData_lavoro() {
        return data_lavoro;
    }

    public void setData_lavoro(Date data_lavoro) {
        this.data_lavoro = data_lavoro;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getStato_lavoro() {
        return stato_lavoro;
    }

    public void setStato_lavoro(String stato_lavoro) {
        this.stato_lavoro = stato_lavoro;
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