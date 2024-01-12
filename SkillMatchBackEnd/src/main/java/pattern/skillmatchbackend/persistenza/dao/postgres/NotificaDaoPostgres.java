package pattern.skillmatchbackend.persistenza.dao.postgres;

import pattern.skillmatchbackend.model.Notifica;
import pattern.skillmatchbackend.persistenza.dao.NotificaDao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class NotificaDaoPostgres implements NotificaDao {

    Connection conn;

    public NotificaDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Notifica> findAll() {
        List<Notifica> notifiche = new LinkedList<>();
        String query = "SELECT * FROM notifica";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Notifica notifica = new Notifica();
                notifica.setId(rs.getLong("id_notifica"));
                notifica.setContenuto(rs.getString("contenuto"));
                notifica.setData(rs.getTimestamp("data"));
                notifica.setVisualizzato(rs.getBoolean("visualizzato"));
                notifica.setChi(rs.getBoolean("chi"));
                notifiche.add(notifica);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifiche;

    }

    @Override
    public Notifica findByPrimaryKey(long id) {

        Notifica notifica = null;
        String query = "SELECT * FROM notifica WHERE id_notifica = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                notifica = new Notifica();
                notifica.setId(rs.getLong("id_notifica"));
                notifica.setContenuto(rs.getString("contenuto"));
                notifica.setData(rs.getTimestamp("data"));
                notifica.setVisualizzato(rs.getBoolean("visualizzato"));
                notifica.setChi(rs.getBoolean("chi"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifica;


    }

    @Override
    public void saveOrUpdate(Notifica notifica) {

    }

    @Override
    public void delete(Notifica notifica) {
        String query = "DELETE FROM notifica WHERE id_notifica = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, notifica.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
