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

    void setUsername(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }

    void setPassword(String password) {
        this.password = password;
    }

    String getPassword() {
        return password;
    }

    void setNome(String nome) {
        this.nome = nome;
    }

    String getNome() {
        return nome;
    }

    void setCognome(String cognome) {
        this.cognome = cognome;
    }

    String getCognome() {
        return cognome;
    }

    void setEmail(String email) {
        this.email = email;
    }

    String getEmail() {
        return email;
    }

    void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    String getIndirizzo() {
        return indirizzo;
    }

    void setNumero_civico(String numero_civico) {
        this.numero_civico = numero_civico;
    }

    String getNumero_civico() {
        return numero_civico;
    }

    void setCitta(String citta) {
        this.citta = citta;
    }

    String getCitta() {
        return citta;
    }

    void setCap(String cap) {
        this.cap = cap;
    }

    String getCap() {
        return cap;
    }
}
