package persistenza.dao.postgres;

import persistenza.DBManager;
import persistenza.dao.CustomerDao;
import application.model.user.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDaoPostgres implements CustomerDao {

    Connection conn;

    public CustomerDaoPostgres(Connection conn) {
        this.conn = conn;
    }


    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer findByPrimaryKey(String email) {
        Customer customer = null;
        String query = "SELECT * FROM CUSTOMER WHERE email = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                customer = new Customer();
                customer.setFirstName(rs.getString("firstName"));
                customer.setLastName(rs.getString("lastName"));
                customer.setRegion(rs.getString("region"));
                customer.setProvince(rs.getString("province"));
                customer.setCity(rs.getString("city"));
                customer.setAddress(rs.getString("adress"));
                customer.setHouseNumber(rs.getInt("houseNumber"));
                customer.setCap(rs.getInt("cap"));
                customer.setEmail(rs.getString("email"));
                customer.setPassword(rs.getString("password"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                customer.setImagePath(rs.getString("imagePath"));
                customer.setTransactions(DBManager.getInstance().getTransactionDao().findByEmail(customer.getEmail()));
                customer.setReviews(DBManager.getInstance().getReviewDao().findAllByIdWorker(rs.getLong("idReview")));
                customer.setJobAdvertisements(DBManager.getInstance().getJobAdvertisementDao().findByEmail(customer.getEmail()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;

    }

    @Override
    public void saveOrUpdate(Customer customer) {
        if (findByPrimaryKey(customer.getEmail()) == null) {
            String insertStr = "INSERT INTO CUSTOMER VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement st;
            try {
                st = conn.prepareStatement(insertStr);

                st.setString(1, customer.getFirstName());
                st.setString(2, customer.getLastName());
                st.setString(3, customer.getRegion());
                st.setString(4, customer.getProvince());
                st.setString(5, customer.getCity());
                st.setString(6, customer.getAddress());
                st.setInt(7, customer.getHouseNumber());
                st.setInt(8, customer.getCap());
                st.setString(9, customer.getEmail());
                st.setString(10, customer.getPassword());
                st.setString(11, customer.getPhoneNumber());
                st.setString(12, customer.getToken());
                st.setString(13, customer.getImagePath());

                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String updateStr = "UPDATE WORKER SET firstName = ?, "
                    + "lastName = ?, "
                    + "region = ?, "
                    + "province = ?, "
                    + "city = ?, "
                    + "address = ?, "
                    + "houseNumber = ?, "
                    + "cap = ?, "
                    + "email = ?, "
                    + "password = ?, "
                    + "phoneNumber = ?, "
                    + "token = ?, "
                    + "imagePath = ? "
                    + "WHERE email = ?";

            PreparedStatement st;
            try {
                st = conn.prepareStatement(updateStr);

                st.setString(1, customer.getFirstName());
                st.setString(2, customer.getLastName());
                st.setString(3, customer.getRegion());
                st.setString(4, customer.getProvince());
                st.setString(5, customer.getCity());
                st.setString(6, customer.getAddress());
                st.setInt(7, customer.getHouseNumber());
                st.setInt(8, customer.getCap());
                st.setString(9, customer.getEmail());
                st.setString(10, customer.getPassword());
                st.setString(11, customer.getPhoneNumber());
                st.setString(12, customer.getToken());
                st.setString(13, customer.getImagePath());
                st.setString(14, customer.getEmail()); // Where condition

                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void delete(Customer customer) {

        String query = "DELETE FROM CUSTOMER WHERE email = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, customer.getEmail());
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
