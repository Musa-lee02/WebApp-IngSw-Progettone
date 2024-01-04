package pattern.skillmatchbackend.persistenza;

import pattern.skillmatchbackend.persistenza.dao.ClienteDao;
import pattern.skillmatchbackend.persistenza.dao.postgres.ClienteDaoPostgres;

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
                con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return con;
    }

    public ClienteDao getClienteDao(){
        return new ClienteDaoPostgres(getConnection());
    }

}
