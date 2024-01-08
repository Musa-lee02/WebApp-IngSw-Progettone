package pattern.skillmatchbackend.persistenza;

import pattern.skillmatchbackend.model.TransazionePagamento;
import pattern.skillmatchbackend.persistenza.dao.*;
import pattern.skillmatchbackend.persistenza.dao.postgres.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    public static DBManager instance = new DBManager();
    private DBManager() {}

    private Connection con = null;

    public static DBManager getInstance() {
        return instance;
    }

    public Connection getConnection(){
        if (con == null){
            try {
                con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/webapp24_ristoranti", "postgres", "postgres");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return con;
    }

    public LavoratoreDao getLavoratoreDao() {
        return new LavoratoreDaoPostgres(getConnection());
    }
    public ClienteDao getClienteDao() {
        return new ClienteDaoPostgres(getConnection());
    }

    public TransazionePagamentoDao getTransazioneDao() {
        return new TransazionePagamentoDaoPostgres(getConnection());
    }

    public MessaggioDao getMessaggioDao() {
        return new MessaggioDaoPostgres(getConnection());
    }

    public PropostaDao getPropostaDao() {
        return new PropostaDaoPostgres(getConnection());
    }

    public AnnuncioDao getAnnuncioDao() {
        return new AnnuncioDaoPostgres(getConnection());
    }

    public RecensioneDao getRecensioneDao() {
        return new RecensioneDaoPostgres(getConnection());
    }

    public AmbitoDao getAmbitoDao() { return new AmbitoDaoPostgres(getConnection());}




}
