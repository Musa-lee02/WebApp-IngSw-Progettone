package pattern.skillmatchbackend.model;

import java.util.LinkedList;
import java.util.List;

public class Chat {

    private Cliente cliente;
    private Lavoratore lavoratore;

    private Annuncio annuncio;


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


}
