package persistenza.dao.postgres;

import persistenza.DBManager;
import persistenza.dao.WorkerDao;
import application.model.user.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class WorkerDaoPostgres implements WorkerDao {

    Connection conn;

    public WorkerDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Worker> findAll() {
        return null;
    }

    @Override
    public Worker findByPrimaryKey(String email) {

        Worker worker = null;
        String query = "select * from WORKER where email = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                worker = new Worker();
                worker.setFirstName(rs.getString("firstName"));
                worker.setLastName(rs.getString("lastName"));
                worker.setRegion(rs.getString("region"));
                worker.setProvince(rs.getString("province"));
                worker.setCity(rs.getString("city"));
                worker.setAddress(rs.getString("adress"));
                worker.setHouseNumber(rs.getInt("houseNumber"));
                worker.setCap(rs.getInt("cap"));
                worker.setEmail(rs.getString("email"));
                worker.setPassword(rs.getString("password"));
                worker.setPhoneNumber(rs.getString("phoneNumber"));
                worker.setImagePath(rs.getString("imagePath"));
                worker.setTransactions(DBManager.getInstance().getTransactionDao().findByEmail(worker.getEmail()));
                worker.setReviews(DBManager.getInstance().getReviewDao().findAllByIdWorker(rs.getLong("idReview")));
                //worker.setWorkScope(rs.getString("workScope"));
                //worker.setProposal(DBManager.getInstance().getProposalDao().);



            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return worker;

    }

    @Override
    public void saveOrUpdate(Worker worker) {

        if (findByPrimaryKey(worker.getEmail()) == null) {
            String insertStr = "INSERT INTO WORKER VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement st;
            try {

                st = conn.prepareStatement(insertStr);

                st.setString(1, worker.getFirstName());
                st.setString(2, worker.getLastName());
                st.setString(3, worker.getRegion());
                st.setString(4, worker.getProvince());
                st.setString(5, worker.getCity());
                st.setString(6, worker.getAddress());
                st.setInt(7, worker.getHouseNumber());
                st.setInt(8, worker.getCap());
                st.setString(9, worker.getEmail());
                st.setString(10, worker.getPassword());
                st.setString(11, worker.getPhoneNumber());
                st.setString(12, worker.getToken());
                st.setString(13,worker.getImagePath());

                st.executeUpdate();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {

            String updateStr = "";

            PreparedStatement st;
            try {
                st = conn.prepareStatement(updateStr);

                st.setString(1, worker.getFirstName());
                st.setString(2, worker.getLastName());
                st.setString(3, worker.getRegion());
                st.setString(4, worker.getProvince());
                st.setString(5, worker.getCity());
                st.setString(6, worker.getAddress());
                st.setInt(7, worker.getHouseNumber());
                st.setInt(8, worker.getCap());
                st.setString(9, worker.getEmail());
                st.setString(10, worker.getPassword());
                st.setString(11, worker.getPhoneNumber());
                st.setString(12, worker.getToken());
                st.setString(13,worker.getImagePath());
                st.setString(14, worker.getEmail()); // Where condition

                st.executeUpdate();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }



    }

    @Override
    public void delete(Worker worker) {

        String query = "DELETE FROM WORKER WHERE email = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, worker.getEmail());
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
