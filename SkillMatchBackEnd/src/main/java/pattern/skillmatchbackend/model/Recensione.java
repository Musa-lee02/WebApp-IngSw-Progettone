package pattern.skillmatchbackend.model;

public class Recensione{
    private Long idRecensione;
    private String titolo;

    private String descrizione;

    private int punteggio;

    private Cliente cliente;

    private Lavoratore lavoratore;


    public Long getIdRecensione() {
        System.out.println("RecensioneId: " + idRecensione);
        return idRecensione;
    }

    public void setIdRecensione(Long idRecensione) {
        this.idRecensione = idRecensione;
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

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

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
}