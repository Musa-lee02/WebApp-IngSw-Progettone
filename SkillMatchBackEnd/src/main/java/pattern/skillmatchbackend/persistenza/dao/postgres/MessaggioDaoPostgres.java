package pattern.skillmatchbackend.persistenza.dao.postgres;

import pattern.skillmatchbackend.model.Messaggio;

import pattern.skillmatchbackend.persistenza.DBManager;
import pattern.skillmatchbackend.persistenza.IdBroker;
import pattern.skillmatchbackend.persistenza.dao.MessaggioDao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;




public class MessaggioDaoPostgres implements MessaggioDao {

    Connection conn;

    public MessaggioDaoPostgres(Connection conn) {
        this.conn = conn;
    }


    @Override
    public List<Messaggio> findAll() {

        List<Messaggio> messaggi = new LinkedList<>();
        String query = "SELECT * FROM messaggio";
        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {


                Messaggio messaggio = new Messaggio();
                messaggio.setId(rs.getLong("id_messaggio"));
                messaggio.setContenuto(rs.getString("contenuto"));
                messaggio.setData(rs.getTimestamp("data"));
                messaggio.setLetto(rs.getBoolean("visualizzato"));
                messaggio.setChi(rs.getBoolean("chi"));
                messaggio.setChat(DBManager.getInstance().getChatDao().findByPrimaryKey(rs.getLong("id_annuncio"),rs.getString("username_cliente"),rs.getString("username_lavoratore")));
                messaggi.add(messaggio);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messaggi;

    }


    @Override
    public Messaggio findByPrimaryKey(long id) {
        Messaggio messaggio = null;
        String query = "SELECT * FROM messaggio WHERE id_messaggio = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                messaggio = new Messaggio();
                messaggio.setId(rs.getLong("id_messaggio"));
                messaggio.setContenuto(rs.getString("contenuto"));
                messaggio.setData(rs.getTimestamp("data"));
                messaggio.setLetto(rs.getBoolean("visualizzato"));
                messaggio.setChi(rs.getBoolean("chi"));
                messaggio.setChat(DBManager.getInstance().getChatDao().findByPrimaryKey(rs.getLong("id_annuncio"),rs.getString("username_cliente"),rs.getString("username_lavoratore")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messaggio;
    }



    @Override
    public void saveOrUpdate(Messaggio messaggio) {

        String query = "INSERT INTO messaggio VALUES (?, ?, ?, ?, ?, ?,?,?)";

        if (findByPrimaryKey(messaggio.getId()) != null)
            query = "UPDATE messaggio SET "
                    + "id_messaggio = ?, contenuto = ?, data = ?, visualizzato = ?, chi = ?, id_annuncio = ?, username_cliente = ?, username_lavoratore = ? "
                    + "WHERE id_messaggio = ?";
        else
            messaggio.setId(IdBroker.getId(conn));


            try {

                PreparedStatement st = conn.prepareStatement(query);

                st.setLong(1, messaggio.getId());
                st.setString(2, messaggio.getContenuto());
                st.setTimestamp(3, messaggio.getData());
                st.setBoolean(4, messaggio.isLetto());
                st.setBoolean(5, messaggio.isChi());
                st.setLong(6, messaggio.getChat().getAnnuncio().getId());
                st.setString(7, messaggio.getChat().getCliente().getUsername());
                st.setString(8, messaggio.getChat().getLavoratore().getUsername());

                if(query.startsWith("UPDATE"))
                    st.setLong(9, messaggio.getId());

                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    @Override
    public void delete(Messaggio messaggio) {
        String query = "DELETE FROM messaggio WHERE id_messaggio = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, messaggio.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Messaggio> findByForeignKeyChat(long idAnnuncio, String username_cliente, String username_lavoratore) {
        List<Messaggio> messaggi = new LinkedList<>();
        String query = "SELECT * FROM messaggio WHERE id_annuncio = ? , username_cliente = ? , username_lavoratore = ?";
        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, idAnnuncio);
            st.setString(2, username_cliente);
            st.setString(3, username_cliente);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {


                Messaggio messaggio = new Messaggio();
                messaggio.setId(rs.getLong("id_messaggio"));
                messaggio.setContenuto(rs.getString("contenuto"));
                messaggio.setData(rs.getTimestamp("data"));
                messaggio.setLetto(rs.getBoolean("visualizzato"));
                messaggio.setChi(rs.getBoolean("chi"));
                messaggio.setChat(DBManager.getInstance().getChatDao().findByPrimaryKey(rs.getLong("id_annuncio"),rs.getString("username_cliente"),rs.getString("username_lavoratore")));
                messaggi.add(messaggio);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messaggi;
    }


}
