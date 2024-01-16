package application.model;

import application.model.user.Cliente;
import application.model.user.Lavoratore;

import java.sql.Date;

public class Transazione {
    private Long idTransazione;

    private Date dataTransazione;

    private Double importo;

    private Cliente mittente;
    private Lavoratore destinatario;
    private String metodoPagamento;

    public Long getIdTransazione() {
        return idTransazione;
    }

    public void setIdTransazione(Long idTransazione) {
        this.idTransazione = idTransazione;
    }

    public Date getDataTransazione() {
        return dataTransazione;
    }

    public void setDataTransazione(Date dataTransazione) {
        this.dataTransazione = dataTransazione;
    }

    public Double getImporto() {
        return importo;
    }

    public void setImporto(Double importo) {
        this.importo = importo;
    }

    public Cliente getMittente() {
        return mittente;
    }

    public void setMittente(Cliente mittente) {
        this.mittente = mittente;
    }

    public Lavoratore getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Lavoratore destinatario) {
        this.destinatario = destinatario;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }
}
