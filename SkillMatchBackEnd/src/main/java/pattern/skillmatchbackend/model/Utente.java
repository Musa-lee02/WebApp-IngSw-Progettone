package pattern.skillmatchbackend.model;

public abstract class Utente {

    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String email;
    private String indirizzo;
    private String numero_civico;
    private String citta;
    private String cap;

    private String imgUrl;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setNumero_civico(String numero_civico) {
        this.numero_civico = numero_civico;
    }

    public String getNumero_civico() {
        return numero_civico;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCitta() {
        return citta;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getCap() {
        return cap;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
