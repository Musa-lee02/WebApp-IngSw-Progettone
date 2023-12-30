package application.persistenza.dao.postgres;

import application.model.Recensione;
import application.model.user.Cliente;
import application.model.user.Lavoratore;
import application.persistenza.dao.RecensioneDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RecensioneDaoPostgres implements RecensioneDao {

    Connection conn;

    public RecensioneDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Recensione> findAll() {
        return null;
    }

    @Override
    public Recensione findByPrimaryKey(long id) {
        Recensione recensione = null;
        String query = "SELECT * FROM RECENSIONE WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                recensione = new Recensione();
                recensione.setIdRecensione(rs.getLong("id"));
                recensione.setTitolo(rs.getString("titolo"));
                recensione.setDescrizione(rs.getString("descrizione"));
                recensione.setPunteggio(rs.getInt("punteggio"));
                //recensione.setRecensore(new Cliente(rs.getLong("idRecensore")));
                //recensione.setRecensito(new Lavoratore(rs.getLong("idRecensito")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recensione;
    }

    @Override
    public void saveOrUpdate(Recensione recensione) {
        if (findByPrimaryKey(recensione.getIdRecensione()) == null) {
            String insertStr = "INSERT INTO RECENSIONE VALUES (?, ?, ?, ?, ?, ?)";

            try {
                PreparedStatement st = conn.prepareStatement(insertStr);

                st.setLong(1, recensione.getIdRecensione());
                st.setString(2, recensione.getTitolo());
                st.setString(3, recensione.getDescrizione());
                st.setFloat(4, recensione.getPunteggio());
                //st.setLong(5, recensione.getRecensore().getIdCliente());
                //st.setLong(6, recensione.getRecensito().getIdLavoratore());

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String updateStr = "UPDATE RECENSIONE SET titolo = ?, "
                    + "descrizione = ?, "
                    + "punteggio = ?, "
                    + "idRecensore = ?, "
                    + "idRecensito = ? "
                    + "WHERE id = ?";

            try {
                PreparedStatement st = conn.prepareStatement(updateStr);

                st.setLong(1, recensione.getIdRecensione());
                st.setString(2, recensione.getTitolo());
                st.setString(3, recensione.getDescrizione());
                st.setFloat(4, recensione.getPunteggio());
                //st.setLong(5, recensione.getRecensore().getIdCliente());
                //st.setLong(6, recensione.getRecensito().getIdLavoratore());

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Recensione recensione) {
        String query = "DELETE FROM RECENSIONE WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, recensione.getIdRecensione());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
