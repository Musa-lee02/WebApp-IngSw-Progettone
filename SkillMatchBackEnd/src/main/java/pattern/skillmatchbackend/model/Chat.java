package pattern.skillmatchbackend.model;

import java.util.LinkedList;
import java.util.List;

public class Chat {

    private Cliente cliente;
    private Lavoratore lavoratore;

    private Annuncio annuncio;

    private List<Messaggio> messaggi = new LinkedList<>();

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Lavoratore getLavoratore() {
        return lavoratore;
    }

    public void setLavoratore(Lavoratore lavoratore) {
        this.lavoratore = lavoratore;
    }

    public Annuncio getAnnuncio() {
        return annuncio;
    }

    public void setAnnuncio(Annuncio annuncio) {
        this.annuncio = annuncio;
    }

    public List<Messaggio> getMessaggi() {
        return messaggi;
    }

    public void setMessaggi(List<Messaggio> messaggi) {
        this.messaggi = messaggi;
    }
}
