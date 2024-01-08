package application.persistenza.dao.postgres;

import application.model.Proposta;
import application.persistenza.dao.PropostaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PropostaDaoPostgres implements PropostaDao {

    Connection conn;

    public PropostaDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Proposta> findAll() {
        return null;
    }

    @Override
    public Proposta findByPrimaryKey(long id) {
        Proposta proposta = null;
        String query = "SELECT * FROM PROPOSTA WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                proposta = new Proposta();
                proposta.setIdProposta(rs.getLong("id"));
                proposta.setTitolo(rs.getString("titolo"));
                proposta.setDescrizione(rs.getString("descrizione"));
                proposta.setStato(rs.getString("stato"));
                proposta.setPrezzoLavoro(rs.getFloat("prezzo_lavoro"));
                //proposta.setAnnuncioRelativo(new Annuncio(rs.getLong("idAnnuncio")));
                //proposta.setLavoratore(new Lavoratore(rs.getLong("idLavoratore")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proposta;
    }

    @Override
    public void saveOrUpdate(Proposta proposta) {
        if (findByPrimaryKey(proposta.getIdProposta()) == null) {
            String insertStr = "INSERT INTO PROPOSTA VALUES (?, ?, ?, ?, ?, ?, ?)";

            try {
                PreparedStatement st = conn.prepareStatement(insertStr);

                st.setLong(1, proposta.getIdProposta());
                st.setString(2, proposta.getTitolo());
                st.setString(3, proposta.getDescrizione());
                st.setString(4, proposta.getStato());
                st.setFloat(5, proposta.getPrezzoLavoro());
                st.setLong(6, proposta.getAnnuncioRelativo().getIdAnnuncio());
                //st.setLong(7, proposta.getLavoratore().getIdLavoratore());

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String updateStr = "UPDATE PROPOSTA SET titolo = ?, "
                    + "descrizione = ?, "
                    + "stato = ?, "
                    + "prezzo_lavoro = ?, "
                    + "idAnnuncio = ?, "
                    + "idLavoratore = ? "
                    + "WHERE id = ?";

            try {
                PreparedStatement st = conn.prepareStatement(updateStr);

                st.setString(1, proposta.getTitolo());
                st.setString(2, proposta.getDescrizione());
                st.setString(3, proposta.getStato());
                st.setFloat(4, proposta.getPrezzoLavoro());
                st.setLong(5, proposta.getAnnuncioRelativo().getIdAnnuncio());
                //st.setLong(6, proposta.getLavoratore().getIdLavoratore());
                st.setLong(7, proposta.getIdProposta());

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Proposta proposta) {
        String query = "DELETE FROM PROPOSTA WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, proposta.getIdProposta());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
