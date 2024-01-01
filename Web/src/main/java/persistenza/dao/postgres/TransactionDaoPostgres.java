package persistenza.dao.postgres;

import persistenza.dao.TransactionDao;
import application.model.Proposal;
import application.model.Transaction;
import persistenza.DBManager;
import application.model.user.Customer;
import application.model.user.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;

import java.util.LinkedList;
import java.util.List;

public class TransactionDaoPostgres implements TransactionDao {


    Connection conn;

    public TransactionDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Transaction> findAll() { return null;}

    @Override
    public Transaction findByPrimaryKey(long id) {
        return null;
    }

    @Override
    public void saveOrUpdate(Transaction transaction) {

        /*st.setLong(1, transaction.id());
        st.setFloat(2, transaction.amount());
        st.setString(3, transaction.customer().getEmail());
        st.setString(4, transaction.worker().getEmail());
        st.setDate(5, transaction.date());
        st.setString(6, transaction.paymentMethod());*/


    }

    @Override
    public void delete(Transaction Transaction) {

    }

    @Override
    public List<Transaction> findByEmail(String email) {
        List<Transaction> transactions = new LinkedList<>();
        String query = "select * from TRANSACTION where email = ?";

        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                long id = rs.getLong("id");
                Proposal proposal = DBManager.getInstance().getProposalDao().findByPrimaryKey(rs.getLong("idProposal"));
                float amount = rs.getFloat("amount");
                Customer customer = DBManager.getInstance().getCustomerDao().findByPrimaryKey("email");
                Worker worker = DBManager.getInstance().getWorkerDao().findByPrimaryKey("email");
                Date date = rs.getDate("date");
                String paymentMethod = rs.getString("paymentMethod");


                Transaction transaction = new Transaction(id,proposal,amount,customer,worker,date,paymentMethod);
                transactions.add(transaction);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return transactions;

    }


}
