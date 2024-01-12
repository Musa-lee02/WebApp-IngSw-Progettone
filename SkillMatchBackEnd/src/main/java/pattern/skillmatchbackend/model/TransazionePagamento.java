package pattern.skillmatchbackend.model;

import java.sql.Date;
import java.sql.Timestamp;

public class TransazionePagamento {
    private Long id;
    private Timestamp dataTransazione;

    private float importo;

    private Cliente mittente;
    private Lavoratore destinatario;
    private String metodoPagamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDataTransazione() {
        return dataTransazione;
    }

    public void setDataTransazione(Timestamp dataTransazione) {
        this.dataTransazione = dataTransazione;
    }

    public float getImporto() {
            return importo;
        }

        public void setImporto(float importo) {
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