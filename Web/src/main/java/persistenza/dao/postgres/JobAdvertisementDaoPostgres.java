package persistenza.dao.postgres;

import persistenza.dao.JobAdvertisementDao;
import application.model.JobAdvertisement;
import application.model.WorkScope;
import application.model.user.Worker;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JobAdvertisementDaoPostgres implements JobAdvertisementDao {

    Connection conn;
    public JobAdvertisementDaoPostgres(Connection conn) {
        this.conn = conn;
    }


    @Override
    public List<JobAdvertisement> localJobSearch(WorkScope workScope, String province) {
        List<JobAdvertisement> jobAdvertisements = new LinkedList<>();
        String query = "select * from utente where fi =//// ?";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                JobAdvertisement jobAdvertisement = new JobAdvertisement();
                jobAdvertisement.setId(rs.getLong(""));
                jobAdvertisements.add(jobAdvertisement);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jobAdvertisements;
    }

    @Override
    public List<JobAdvertisement> findAll() {
        return null;
    }

    @Override
    public JobAdvertisement findByPrimaryKey(long id) {
        JobAdvertisement jobAdvertisement= null;
        String query = "SELECT * FROM JOBADVERTISEMENT WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                jobAdvertisement.setId(rs.getLong("id"));
                jobAdvertisement.setTitle(rs.getString("title"));
                jobAdvertisement.setDescription(rs.getString("description"));
                jobAdvertisement.setPublicationDate(rs.getString("publicationDate"));
                jobAdvertisement.setExpirationDate(rs.getString("expirationDate"));
                jobAdvertisement.setProvince(rs.getString("province"));
                //jobAdvertisement.setWorkScope(DBManager.getInstance().);


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobAdvertisement;
    }

    @Override
    public void saveOrUpdate(JobAdvertisement jobAdvertisement) {

        if (findByPrimaryKey(jobAdvertisement.getId()) == null) {

            String insertStr = "INSERT INTO JOBADVERTISEMENT VALUES (?, ?, ?, ?, ?,)";

            PreparedStatement st;
            try {
                st = conn.prepareStatement(insertStr);

                st.setLong(1, jobAdvertisement.getId());
                st.setString(2, jobAdvertisement.getTitle());
                st.setString(3, jobAdvertisement.getDescription());
                st.setString(4, jobAdvertisement.getPublicationDate());
                st.setString(5, jobAdvertisement.getExpirationDate());
                st.setString(6, jobAdvertisement.getProvince());
                st.setString(7, jobAdvertisement.getCustomer().getEmail());

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



                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void delete(JobAdvertisement jobAdvertisementDao) {

    }

    @Override
    public List<JobAdvertisement> findByEmail(String email) {
        /*Worker worker = null;
        String query = "select * from WORKER where email = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {





            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return worker;*/return null;
    }
}
