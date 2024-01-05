package pattern.skillmatchbackend.model;

import pattern.skillmatchbackend.model.Contenuto;
import pattern.skillmatchbackend.model.Messaggio;
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
        String query = "SELECT * FROM MESSAGGIO WHERE idProposta = ?";
        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Messaggio messaggio = new Messaggio();
                messaggio.setIdMessaggio(rs.getLong("id"));
                //messaggio.setContenuto(new Contenuto(rs.getString("contenuto")));
                messaggio.setDataInvio(rs.getDate("data_invio"));
                messaggio.setOraInvio(rs.getTime("ora_invio"));
                messaggio.setWho(rs.getBoolean("who"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messaggi;

    }


    @Override
    public Messaggio findByPrimaryKey(long id) {
        Messaggio messaggio = null;
        String query = "SELECT * FROM MESSAGGIO WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                messaggio = new Messaggio();
                messaggio.setIdMessaggio(rs.getLong("id"));
                messaggio.setContenuto(new Contenuto(rs.getString("contenuto")));
                messaggio.setDataInvio(rs.getDate("data_invio"));
                messaggio.setOraInvio(rs.getTime("ora_invio"));
                messaggio.setWho(rs.getBoolean("who"));
                messaggio.setProposta(DBManager.getInstance().getPropostaDao().findByPrimaryKey(rs.getLong("idProposta")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messaggio;
    }



    @Override
    public void saveOrUpdate(Messaggio messaggio) {

        String query = "INSERT INTO MESSAGGIO VALUES (?, ?, ?, ?, ?, ?)";

        if (findByPrimaryKey(messaggio.getIdMessaggio()) != null)
            query = "UPDATE MESSAGGIO SET contenuto = ?, " + "data_invio = ?, " + "ora_invio = ?, " + "who = ?, " + "idProposta = ? " + "WHERE id = ?";


            try {

                PreparedStatement st = conn.prepareStatement(query);

                st.setLong(1, messaggio.getIdMessaggio());
                st.setString(2, messaggio.getContenuto().getContenuto());
                st.setDate(3, messaggio.getDataInvio());
                st.setTime(4, messaggio.getOraInvio());
                st.setBoolean(5, messaggio.isWho());
                st.setLong(6, messaggio.getProposta().getIdProposta());

                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    @Override
    public void delete(Messaggio messaggio) {
        String query = "DELETE FROM MESSAGGIO WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, messaggio.getIdMessaggio());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Messaggio> findByIdProposta(long id) {

        List<Messaggio> messaggi = new LinkedList<>();
        String query = "SELECT * FROM MESSAGGIO WHERE idProposta = ?";
        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Messaggio messaggio = new Messaggio();
                messaggio.setIdMessaggio(rs.getLong("id"));
                messaggio.setContenuto(new Contenuto(rs.getString("contenuto")));
                messaggio.setDataInvio(rs.getDate("data_invio"));
                messaggio.setOraInvio(rs.getTime("ora_invio"));
                messaggio.setWho(rs.getBoolean("who"));
                messaggio.setProposta(DBManager.getInstance().getPropostaDao().findByPrimaryKey(rs.getLong("idProposta")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messaggi;


    }


}