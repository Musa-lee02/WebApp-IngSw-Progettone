package persistenza.dao.postgres;

import persistenza.DBManager;
import persistenza.dao.ReviewDao;
import application.model.Review;
import application.model.user.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ReviewDaoPostgres implements ReviewDao {

    Connection conn;

    public ReviewDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Review> findAll() {
        return null;
    }

    @Override
    public Review findByPrimaryKey(long id) {
        return null;
    }

    @Override
    public void saveOrUpdate(Review review) {

    }

    @Override
    public void delete(Review review) {

    }

    @Override
    public List<Review> findAllByIdWorker(long id) {
        List<Review> reviews = new LinkedList<>();
        String query = "select * from Review where idWorker = ?";

        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                long idReview = rs.getLong("idReview");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int rating = rs.getInt("rating");
                String emailWorker = rs.getString("emailWorker");
                String emailCustomer = rs.getString("emailCustomer");
                User customer = DBManager.getInstance().getCustomerDao().findByPrimaryKey(emailCustomer);
                User worker = DBManager.getInstance().getWorkerDao().findByPrimaryKey(emailWorker);

                Review review = new Review(idReview,title,description,rating,customer,worker);
                reviews.add(review);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return reviews;
    }

}
