package pattern.skillmatchbackend.model;

public class Recensione{
    private Long idRecensione;
    private String titolo;

    private String descrizione;

    private int punteggio;

    private Cliente recensore;

    private Lavoratore recensito;


    public Long getIdRecensione() {
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

    public Cliente getRecensore() {
        return recensore;
    }

    public void setRecensore(Cliente recensore) {
        this.recensore = recensore;
    }

    public Lavoratore getRecensito() {
        return recensito;
    }

    public void setRecensito(Lavoratore recensito) {
        this.recensito = recensito;
    }
}