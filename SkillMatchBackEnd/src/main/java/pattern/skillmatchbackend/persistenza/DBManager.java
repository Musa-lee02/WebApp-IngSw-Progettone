package pattern.skillmatchbackend.persistenza;

import pattern.skillmatchbackend.persistenza.dao.AmbitiDao;
import persistenza.dao.CustomerDao;
import persistenza.dao.postgres.JobAdvertisementDaoPostgres;
import persistenza.dao.postgres.CustomerDaoPostgres;
import persistenza.dao.postgres.TransactionDaoPostgres;

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
                con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SkillMatch", "postgres", "postgres");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return con;
    }

    public AmbitiDao



}
