package pattern.skillmatchbackend.persistenza.dao.postgres;

import pattern.skillmatchbackend.model.*;
import pattern.skillmatchbackend.persistenza.DBManager;

import java.util.List;

public class LavoratoreProxy extends Lavoratore {

    

    public LavoratoreProxy() {
        super();
    }

    public LavoratoreProxy(Utente utente) {
        super(utente);
    }



    @Override
    public List<Ambito> getAmbiti() {

        List<Ambito> ambiti = super.getAmbiti();

        if (ambiti == null){

            super.setAmbiti(DBManager.getInstance().getAmbitoDao().findByLavoratore(username));

        }
        return ambiti;

    }

}
