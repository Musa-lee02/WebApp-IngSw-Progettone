package pattern.skillmatchbackend.model;

import java.sql.Date;
import java.util.List;

public class Annuncio {
    private Long idAnnuncio;
    private String titolo;
    private String descrizione;
    private Date dataDiScadenza;
    private String provinciaAnnuncio;
    private Cliente cliente;

    private List<Ambiti> ambiti;

    public Annuncio() {
    }

    public Annuncio(Long idAnnuncio, String titolo, String descrizione, Date dataDiScadenza, String provinciaAnnuncio, Cliente cliente, List<Ambiti> ambiti) {
        this.idAnnuncio = idAnnuncio;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.dataDiScadenza = dataDiScadenza;
        this.provinciaAnnuncio = provinciaAnnuncio;
        this.cliente = cliente;
        this.ambiti = ambiti;
    }

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

    public List<Ambiti> getAmbiti() {
        return ambiti;
    }

    public void setAmbiti(List<Ambiti> ambiti) {
        this.ambiti = ambiti;
    }











}
