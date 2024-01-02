package application.model;

import application.model.image.Image;
import application.model.user.Cliente;

import java.sql.Date;

public class Annuncio {
    private Long idAnnuncio;
    private String titolo;
    private String descrizione;
    private Date dataDiScadenza;
    private String provinciaAnnuncio;
    private Cliente cliente;
    private Image image = new Image();

    public Annuncio() {
    }

    public Long getIdAnnuncio() {
        return this.idAnnuncio;
    }

    public void setIdAnnuncio(Long idAnnuncio) {
        this.idAnnuncio = idAnnuncio;
    }

    public String getTitolo() {
        return this.titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Date getData_di_scadenza() {
        return this.dataDiScadenza;
    }

    public void setData_di_scadenza(Date dataDiScadenza) {
        this.dataDiScadenza = dataDiScadenza;;
    }

    public String getProvinciaAnnuncio() {
        return this.provinciaAnnuncio;
    }

    public void setProvinciaAnnuncio(String provinciaAnnuncio) {
        this.provinciaAnnuncio = provinciaAnnuncio;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
