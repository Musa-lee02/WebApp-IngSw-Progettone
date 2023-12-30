package application.persistenza.dao.postgres;

import application.model.Messaggio;
import application.model.Proposta;
import application.persistenza.dao.MessaggioDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;




public class MessaggioDaoPostgres implements MessaggioDao {

    Connection conn;

    public MessaggioDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Messaggio findByPrimaryKey(long id) {
        Messaggio messaggio = null;
        String query = "SELECT * FROM MESSAGGIO WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                messaggio = new Messaggio();
                messaggio.setIdMessaggio(rs.getLong("id"));
                //messaggio.setContenuto(new Contenuto(rs.getString("contenuto")));
                messaggio.setDataInvio(rs.getDate("data_invio"));
                messaggio.setOraInvio(rs.getTime("ora_invio"));
                messaggio.setWho(rs.getBoolean("who"));
                //messaggio.setProposta(new Proposta(rs.getLong("idProposta")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messaggio;
    }

    @Override
    public List<Messaggio> findAll() {
        return null;
    }

    @Override
    public void saveOrUpdate(Messaggio messaggio) {
        if (findByPrimaryKey(messaggio.getIdMessaggio()) == null) {
            String insertStr = "INSERT INTO MESSAGGIO VALUES (?, ?, ?, ?, ?, ?)";

            try {
                PreparedStatement st = conn.prepareStatement(insertStr);

                st.setLong(1, messaggio.getIdMessaggio());
                //st.setString(2, messaggio.getContenuto().getText());
                st.setDate(3, messaggio.getDataInvio());
                st.setTime(4, messaggio.getOraInvio());
                st.setBoolean(5, messaggio.isWho());
                st.setLong(6, messaggio.getProposta().getIdProposta());

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String updateStr = "UPDATE MESSAGGIO SET contenuto = ?, "
                    + "data_invio = ?, "
                    + "ora_invio = ?, "
                    + "who = ?, "
                    + "idProposta = ? "
                    + "WHERE id = ?";

            try {
                PreparedStatement st = conn.prepareStatement(updateStr);

                //st.setString(1, messaggio.getContenuto().getText());
                st.setDate(2, messaggio.getDataInvio());
                st.setTime(3, messaggio.getOraInvio());
                st.setBoolean(4, messaggio.isWho());
                st.setLong(5, messaggio.getProposta().getIdProposta());
                st.setLong(6, messaggio.getIdMessaggio());

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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


}
