package application.model;

import application.model.user.Lavoratore;

public class Proposta {

    private Long idProposta;
    private String titolo;
    private String descrizione;
    private String stato;
    private Float prezzoLavoro;
    private Annuncio annuncioRelativo;
    private Lavoratore lavoratore;

    public Long getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(Long idProposta) {
        this.idProposta = idProposta;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
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
