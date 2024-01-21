package pattern.skillmatchbackend.model;


import java.sql.Date;

public class Proposta {

    private Date dataLavoro;
    private String descrizione;
    private String stato;
    private String statoLavoro;
    private Float prezzoLavoro;
    private Annuncio annuncioRelativo;
    private Lavoratore lavoratore;

    public Date getDataLavoro() {
        return dataLavoro;
    }

    public void setDataLavoro(Date dataLavoro) {
        this.dataLavoro = dataLavoro;
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

    public String getStatoLavoro() {
        return statoLavoro;
    }

    public void setStatoLavoro(String statoLavoro) {
        this.statoLavoro = statoLavoro;
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