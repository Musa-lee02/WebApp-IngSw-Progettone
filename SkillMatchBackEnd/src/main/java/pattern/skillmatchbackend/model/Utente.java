package pattern.skillmatchbackend.model;

public abstract class Utente {

    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String email;
    private String indirizzo;
    private String numeroCivico;
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

    public void setNumeroCivico(String numero_civico) {
        this.numeroCivico = numero_civico;
    }

    public String getNumeroCivico() {
        return numeroCivico;
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
