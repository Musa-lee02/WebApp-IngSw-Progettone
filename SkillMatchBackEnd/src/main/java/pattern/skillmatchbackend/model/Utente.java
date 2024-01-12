package pattern.skillmatchbackend.model;

import java.sql.Date;

public class  Utente {

    protected String username;
    protected String password;
    protected String email;
    protected String nome;
    protected String cognome;
    protected String via;
    protected int numeroCivico;
    protected String cap;
    protected String provincia;
    protected String citta;
    protected String provinciaLavoro;
    protected Image imgProfilo = new Image();
    protected boolean registrato;
    protected Date dataRegistrazione;

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

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public int getNumeroCivico() {
        return numeroCivico;
    }

    public void setNumeroCivico(int numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getProvinciaLavoro() {
        return provinciaLavoro;
    }

    public void setProvinciaLavoro(String provinciaLavoro) {
        this.provinciaLavoro = provinciaLavoro;
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