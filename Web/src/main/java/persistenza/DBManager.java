package persistenza;

import persistenza.dao.*;
import persistenza.dao.postgres.*;

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

    public CustomerDao getCustomerDao() {
        return new CustomerDaoPostgres(getConnection());
    }
    public JobAdvertisementDao getJobAdvertisementDao() { return  new JobAdvertisementDaoPostgres(getConnection());}

    public TransactionDao getTransactionDao() {return new TransactionDaoPostgres(getConnection());}

    public ProposalDao getProposalDao() {return new ProposalDaoPostgres(getConnection());}

    public ReviewDao getReviewDao() {return new ReviewDaoPostgres(getConnection());}

    public MessageDao getMessageDao() {return new MessageDaoPostgres(getConnection());}

    public WorkerDao getWorkerDao() {return new WorkerDaoPostgres(getConnection());}
    public ChatDao getChatDao() {return new ChatDaoPostgres(getConnection());}


}
