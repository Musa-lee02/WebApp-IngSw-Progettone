/*package pattern.skillmatchbackend.persistenza.dao.postgres;

import pattern.skillmatchbackend.model.Proposta;

import pattern.skillmatchbackend.persistenza.DBManager;
import pattern.skillmatchbackend.persistenza.dao.PropostaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PropostaDaoPostgres implements PropostaDao {

    Connection conn;

    public PropostaDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Proposta> findAll() {
        List<Proposta> proposte = new LinkedList<>();
        String query = "SELECT * FROM PROPOSTA";

        try {
            Proposta proposta;
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                proposta = new Proposta();
                proposta.setStato(rs.getString("stato"));
                proposta.setPrezzoLavoro(rs.getFloat("prezzo_lavoro"));
                proposta.setAnnuncioRelativo(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("username")));
                proposta.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString(rs.getString("username"))));
                proposte.add(proposta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proposte;
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
               // proposta.setIdProposta(rs.getLong("id"));
               // proposta.setTitolo(rs.getString("titolo"));
               // proposta.setDescrizione(rs.getString("descrizione"));
                proposta.setStato(rs.getString("stato"));
                proposta.setPrezzoLavoro(rs.getFloat("prezzo_lavoro"));
                proposta.setAnnuncioRelativo(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("username")));
                proposta.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString(rs.getString("username"))));
                //proposta.setMessaggi(DBManager.getInstance().getMessaggioDao().findByIdProposta(proposta.getIdProposta()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proposta;
    }

    @Override
    public void saveOrUpdate(Proposta proposta) {

        String query = "INSERT INTO PROPOSTA VALUES (?, ?, ?, ?, ?, ?, ?)";

        if (findByPrimaryKey(proposta.getIdProposta()) != null)
            query = "UPDATE PROPOSTA SET titolo = ?, " + "descrizione = ?, " + "stato = ?, " + "prezzo_lavoro = ?, " + "idAnnuncio = ?, " + "idLavoratore = ? " + "WHERE id = ?";

            try {
                PreparedStatement st = conn.prepareStatement(query);

                st.setLong(1, proposta.getIdProposta());
                st.setString(2, proposta.getTitolo());
                st.setString(3, proposta.getDescrizione());
                st.setString(4, proposta.getStato());
                st.setFloat(5, proposta.getPrezzoLavoro());
                st.setLong(6, proposta.getAnnuncioRelativo().getIdAnnuncio());
                st.setString(7, proposta.getLavoratore().getUsername());

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
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

    @Override
    public List<Proposta> findByLavoratore(String username) {

        List<Proposta> proposte = new LinkedList<>();
        String query = "SELECT * FROM PROPOSTA WHERE idAnuncio = ?";

        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Proposta proposta = new Proposta();
                proposta.setIdProposta(rs.getLong("id"));
                proposta.setTitolo(rs.getString("titolo"));
                proposta.setDescrizione(rs.getString("descrizione"));
                proposta.setStato(rs.getString("stato"));
                proposta.setPrezzoLavoro(rs.getFloat("prezzo_lavoro"));
                proposta.setAnnuncioRelativo(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("username")));
                proposta.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString(rs.getString("username"))));
                proposte.add(proposta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proposte;

    }

    @Override
    public List<Proposta> findByPrimaryKeyAnnuncio(long id) {
        List<Proposta> proposte = new LinkedList<>();
        String query = "SELECT * FROM PROPOSTA WHERE idAnuncio = ?";

        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Proposta proposta = new Proposta();
                proposta.setIdProposta(rs.getLong("id"));
                proposta.setTitolo(rs.getString("titolo"));
                proposta.setDescrizione(rs.getString("descrizione"));
                proposta.setStato(rs.getString("stato"));
                proposta.setPrezzoLavoro(rs.getFloat("prezzo_lavoro"));
                proposta.setAnnuncioRelativo(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("username")));
                proposta.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString(rs.getString("username"))));
                proposte.add(proposta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proposte;

    }

}*/
