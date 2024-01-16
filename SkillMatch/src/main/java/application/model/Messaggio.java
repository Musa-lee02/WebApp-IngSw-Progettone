package application.model;

import application.model.contenuto.Contenuto;

import java.sql.Date;
import java.sql.Time;

public class Messaggio {

    private Long idMessaggio;
    private Contenuto contenuto;
    private Date dataInvio;
    private Time oraInvio;
    private boolean who;
    private Proposta proposta;

    public Long getIdMessaggio() {
        return idMessaggio;
    }

    public void setIdMessaggio(Long idMessaggio) {
        this.idMessaggio = idMessaggio;
    }

    public Contenuto getContenuto() {
        return contenuto;
    }

    public void setContenuto(Contenuto contenuto) {
        this.contenuto = contenuto;
    }

    public Date getDataInvio() {
        return dataInvio;
    }

    public void setDataInvio(Date dataInvio) {
        this.dataInvio = dataInvio;
    }

    public Time getOraInvio() {
        return oraInvio;
    }

    public void setOraInvio(Time oraInvio) {
        this.oraInvio = oraInvio;
    }

    public boolean isWho() {
        return who;
    }

    public void setWho(boolean who) {
        this.who = who;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }
}
