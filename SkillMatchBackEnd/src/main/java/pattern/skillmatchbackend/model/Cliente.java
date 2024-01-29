package pattern.skillmatchbackend.model;


import java.util.LinkedList;
import java.util.List;

public class Cliente extends Utente {


    public Cliente(Lavoratore lavoratore){
        super(lavoratore);
    }
    public Cliente(Utente utente) {
        super(utente);
    }

    public Cliente(){}



}
