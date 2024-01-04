package pattern.skillmatchbackend.model;



import pattern.skillmatchbackend.model.Image;


import java.sql.Date;

import java.util.LinkedList;
import java.util.List;

public class Annuncio {
    private Long idAnnuncio;
    private String titolo;
    private String descrizione;
    private Date dataDiScadenza;
    private String provinciaAnnuncio;
    private Cliente cliente;
    private Ambito ambito;
    private Image image = new Image();
    private List<Proposta> proposteRicevute;

    public Long getIdAnnuncio() {
        return idAnnuncio;
    }

    public void setIdAnnuncio(Long idAnnuncio) {
        this.idAnnuncio = idAnnuncio;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Proposta> getProposteRicevute() {
        return proposteRicevute;
    }

    public void setProposteRicevute(List<Proposta> proposteRicevute) {
        this.proposteRicevute = proposteRicevute;
    }
}

