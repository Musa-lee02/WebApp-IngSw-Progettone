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
    public List<Recensione> getRecensioni() {

        List<Recensione> recensioni = super.getRecensioni();

        if(recensioni == null)
            return DBManager.getInstance().getRecensioneDao().findByForeignKeyLavoratore(username);

        return recensioni;


    }

    @Override
    public List<TransazionePagamento> getTransazionePagamento() {
        List<TransazionePagamento> transazioni = super.getTransazionePagamento();

        if(transazioni == null)
            return DBManager.getInstance().getTransazionePagamentoDao().findByForeignKeyLavoratore(username);

        return transazioni;

    }

    @Override
    public List<Notifica> getNotifiche() {

        List<Notifica> notifiche = super.getNotifiche();

        if (notifiche == null)
            return DBManager.getInstance().getNotificaDao().findByForeignKeyLavoratore(username);

        return notifiche;

    }

    @Override
    public List<Chat> getChats() {

        List<Chat> chats = super.getChats();

        if (chats == null)
            return DBManager.getInstance().getChatDao().findByForeignKeyLavoratore(username);

        return chats;

    }

    @Override
    public List<Proposta> getProposte() {

        List<Proposta> proposte = super.getProposte();

        if (proposte == null)
            return DBManager.getInstance().getPropostaDao().findByForeignKeyLavoratore(username);

        return proposte;

    }

    @Override
    public List<Ambito> getAmbiti() {

        List<Ambito> ambiti = super.getAmbiti();

        if (ambiti == null)
            return DBManager.getInstance().getAmbitoDao().findByLavoratore(username);

        return ambiti;

    }

    @Override
    public List<Annuncio> getAnnunciDisponibili() {

        List<Annuncio> annunciDisponibili = super.getAnnunciDisponibili();

        if(annunciDisponibili == null)
            return DBManager.getInstance().getAnnuncioDao().annunciPerMe(provincia,username);

        return  annunciDisponibili;
    }
}
