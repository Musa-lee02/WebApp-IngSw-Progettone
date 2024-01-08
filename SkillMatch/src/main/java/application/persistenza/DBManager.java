package application.persistenza;

import application.persistenza.dao.*;
import application.persistenza.dao.postgres.*;

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

    public UtenteDao getUtenteDao() {
        return new UtenteDaoPostgres(getConnection());
    }

    public TransazioneDao getTransazioneDao() {
        return new TransazioneDaoPostgres(getConnection());
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




}
