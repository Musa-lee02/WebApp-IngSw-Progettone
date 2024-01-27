package pattern.skillmatchbackend.model;



import pattern.skillmatchbackend.model.Image;


import java.sql.Date;

import java.util.LinkedList;
import java.util.List;

public class Annuncio {

    private Long id;
    private String titolo;
    private String descrizione;
    private Date dataDiScadenza;
    private String provinciaAnnuncio;
    private String image;
    private Cliente cliente;
    private Ambito ambito;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDataDiScadenza() {
        return dataDiScadenza;
    }

    public void setDataDiScadenza(Date dataDiScadenza) {
        this.dataDiScadenza = dataDiScadenza;
    }

    public String getProvinciaAnnuncio() {
        return provinciaAnnuncio;
    }

    public void setProvinciaAnnuncio(String provinciaAnnuncio) {
        this.provinciaAnnuncio = provinciaAnnuncio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Ambito getAmbito() {
        return ambito;
    }

    public void setAmbito(Ambito ambito) {
        this.ambito = ambito;
    }

}

