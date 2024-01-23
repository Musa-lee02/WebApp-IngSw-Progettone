package pattern.skillmatchbackend.model;

import java.sql.Date;

public class  Utente {

    protected String username;
    protected String password;
    protected String email;
    protected String nome;
    protected String cognome;
    protected String provincia;
    protected Image imgProfilo;
    protected boolean registrato;
    protected Date dataRegistrazione;

    public Utente() {
    }

    public Utente(Utente utente) {
        this.username = utente.username;
        this.password = utente.password;
        this.email = utente.email;
        this.nome = utente.nome;
        this.cognome = utente.cognome;
        this.provincia = utente.provincia;
        this.imgProfilo = new Image(imgProfilo.getPath());
        this.registrato = utente.registrato;
        this.dataRegistrazione = utente.dataRegistrazione;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }


    public Image getImgProfilo() {
        return imgProfilo;
    }

    public void setImgProfilo(Image imgProfilo) {
        this.imgProfilo = imgProfilo;
    }

    public boolean isRegistrato() {
        return registrato;
    }

    public void setRegistrato(boolean registrato) {
        this.registrato = registrato;
    }

    public Date getDataRegistrazione() {
        return dataRegistrazione;
    }

    public void setDataRegistrazione(Date dataRegistrazione) {
        this.dataRegistrazione = dataRegistrazione;
    }
}