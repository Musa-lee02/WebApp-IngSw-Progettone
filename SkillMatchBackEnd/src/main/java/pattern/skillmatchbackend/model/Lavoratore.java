package pattern.skillmatchbackend.model;


import pattern.skillmatchbackend.model.email.EmailSender;

import java.util.LinkedList;
import java.util.List;

public class Lavoratore extends Utente implements Observer {


    protected String provinciaLavoro;
    private boolean notifica_email;
    private int punteggio;

    private List<Ambito> ambiti = new LinkedList<>();


    public Lavoratore(Utente utente) {
        super(utente);
    }

    public Lavoratore(){}


    public String getProvinciaLavoro() {
        return provinciaLavoro;
    }

    public void setProvinciaLavoro(String provinciaLavoro) {
        this.provinciaLavoro = provinciaLavoro;
    }

    public boolean isNotifica_email() {
        return notifica_email;
    }

    public void setNotifica_email(boolean notifica_email) {
        this.notifica_email = notifica_email;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }


    public List<Ambito> getAmbiti() {
        return ambiti;
    }

    public void setAmbiti(List<Ambito> ambiti) {
        this.ambiti = ambiti;
    }



    @Override
    public void update(Annuncio annuncio) {

        if(annuncio.getProvinciaAnnuncio().equals(provincia) && ambiti.contains(annuncio.getAmbito())){

            new EmailSender().annuncioRelativo(this,annuncio);

        }
    }
}