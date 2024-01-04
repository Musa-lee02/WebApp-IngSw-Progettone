package pattern.skillmatchbackend.model;

public class Chat {

    private Cliente cliente;
    private Lavoratore lavoratore;

    private Annuncio annuncio;


    Cliente getCliente() {
        return cliente;
    }

    void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    Lavoratore getLavoratore() {
        return lavoratore;
    }

    void setLavoratore(Lavoratore lavoratore) {
        this.lavoratore = lavoratore;
    }

    Annuncio getAnnuncio() {
        return annuncio;
    }

    void setAnnuncio(Annuncio annuncio) {
        this.annuncio = annuncio;
    }

}
