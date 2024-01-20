package pattern.skillmatchbackend.persistenza.dao.postgres;

import pattern.skillmatchbackend.model.Notifica;
import pattern.skillmatchbackend.persistenza.DBManager;
import pattern.skillmatchbackend.persistenza.IdBroker;
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
                notifica.setUtente(DBManager.getInstance().getUtenteDao().findByPrimaryKey(rs.getString("username")));
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
                notifica.setUtente(DBManager.getInstance().getUtenteDao().findByPrimaryKey(rs.getString("username")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifica;


    }

    @Override
    public void saveOrUpdate(Notifica notifica) {

        String query = "INSERT INTO notifica VALUES (?, ?, ?, ?, ?, ?)";

        if (findByPrimaryKey(notifica.getId()) != null)
            query = "UPDATE notifica SET id_notifica = ?. contenuto = ?, data = ?, visualizzato = ?, chi = ?, username = ? WHERE id_notifica = ?";
        else
            notifica.setId(IdBroker.getId(conn));

        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, notifica.getId());
            st.setString(2, notifica.getContenuto());
            st.setTimestamp(3, notifica.getData());
            st.setBoolean(4, notifica.isVisualizzato());
            st.setBoolean(5, notifica.isChi());
            st.setString(6, notifica.getUtente().getUsername());

            if(query.startsWith("UPDDATE"))
                st.setLong(7, notifica.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


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

    @Override
    public List<Notifica> findByForeignKeyCliente(String username) {
        return findByForeignKeyClienteoLavoratore(username,"cliente");
    }

    @Override
    public List<Notifica> findByForeignKeyLavoratore(String username) {
        return findByForeignKeyClienteoLavoratore(username,"lavoratore");
    }

    public List<Notifica> findByForeignKeyClienteoLavoratore(String username,String profilo) {

        List<Notifica> notifiche = new LinkedList<>();
        String query = "SELECT * " +
                "FROM notifica,utente,"+profilo+
                " WHERE "+profilo+".username = ? and notifica.username = utente.username and utente.username = "+profilo+".username";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Notifica notifica = new Notifica();
                notifica.setId(rs.getLong("id_notifica"));
                notifica.setContenuto(rs.getString("contenuto"));
                notifica.setData(rs.getTimestamp("data"));
                notifica.setVisualizzato(rs.getBoolean("visualizzato"));
                notifica.setChi(rs.getBoolean("chi"));
                notifica.setUtente(DBManager.getInstance().getUtenteDao().findByPrimaryKey(rs.getString("username")));
                notifiche.add(notifica);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifiche;

    }


}