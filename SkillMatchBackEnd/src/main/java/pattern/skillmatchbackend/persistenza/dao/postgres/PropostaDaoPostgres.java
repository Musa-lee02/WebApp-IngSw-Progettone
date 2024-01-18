package pattern.skillmatchbackend.persistenza.dao.postgres;

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
        String query = "SELECT * FROM proposta";

        try {
            Proposta proposta;
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                proposta = new Proposta();
                proposta.setAnnuncioRelativo(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("id_annuncio")));
                proposta.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username_clavoratore")));
                proposta.setTitolo(rs.getString("titolo"));
                proposta.setDescrizione(rs.getString("descrizione"));
                proposta.setStato(rs.getString("stato"));
                proposta.setPrezzoLavoro(rs.getFloat("prezzo_lavoro"));
                proposte.add(proposta);


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proposte;
    }

    @Override
    public Proposta findByPrimaryKey(long idAnnuncio, String username) {
        Proposta proposta = null;
        String query = "SELECT * FROM proposta WHERE WHERE id_annuncio = ? and id_lavoratore = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, idAnnuncio);
            st.setString(2,username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                proposta = new Proposta();
                proposta.setAnnuncioRelativo(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("id_annuncio")));
                proposta.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username_clavoratore")));
                proposta.setTitolo(rs.getString("titolo"));
                proposta.setDescrizione(rs.getString("descrizione"));
                proposta.setStato(rs.getString("stato"));
                proposta.setPrezzoLavoro(rs.getFloat("prezzo_lavoro"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proposta;
    }

    @Override
    public void saveOrUpdate(Proposta proposta) {

        /*String query = "INSERT INTO proposta (id_annuncio, id_lavoratore, titolo, descrizione, stato, prezzo_lavoro) VALUES (?, ?, ?, ?, ?, ?)";

        if (findByPrimaryKey(proposta.getId_annuncio(), proposta.getId_lavoratore()) != null)
            query = "UPDATE proposta SET titolo = ?, descrizione = ?, stato = ?, prezzo_lavoro = ? WHERE id_annuncio = ? AND id_lavoratore = ?";

        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, proposta.getId_annuncio());
            st.setLong(2, proposta.getId_lavoratore());
            st.setString(3, proposta.getTitolo());
            st.setString(4, proposta.getDescrizione());
            st.setString(5, proposta.getStato());
            st.setBigDecimal(6, proposta.getPrezzo_lavoro());

            if (query.startsWith("UPDATE")) {
                // If it's an update, set additional parameters
                st.setString(7, proposta.getTitolo());
                st.setString(8, proposta.getDescrizione());
                st.setString(9, proposta.getStato());
                st.setBigDecimal(10, proposta.getPrezzo_lavoro());
                st.setLong(11, proposta.getId_annuncio());
                st.setLong(12, proposta.getId_lavoratore());
            }

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
*/


    }

    @Override
    public void delete(Proposta proposta) {
        String query = "DELETE FROM proposta WHERE id_annuncio = ? and id_lavoratore = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, proposta.getAnnuncioRelativo().getId());
            st.setString(2,proposta.getLavoratore().getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Proposta> findByForeignKeyLavoratore(String username) {

        List<Proposta> proposte = new LinkedList<>();
        String query = "SELECT * FROM proposta WHERE username_lavoratore = ?";

        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Proposta proposta = new Proposta();
                proposta.setAnnuncioRelativo(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("id_annuncio")));
                proposta.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username_clavoratore")));
                proposta.setTitolo(rs.getString("titolo"));
                proposta.setDescrizione(rs.getString("descrizione"));
                proposta.setStato(rs.getString("stato"));
                proposta.setPrezzoLavoro(rs.getFloat("prezzo_lavoro"));
                proposte.add(proposta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proposte;

    }

    @Override
    public Proposta findByForeignKeyAnnuncio(Long id) {
        Proposta proposta = null;
        String query = "SELECT * FROM proposta WHERE WHERE id_annuncio = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                proposta = new Proposta();
                proposta.setAnnuncioRelativo(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("id_annuncio")));
                proposta.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username_clavoratore")));
                proposta.setTitolo(rs.getString("titolo"));
                proposta.setDescrizione(rs.getString("descrizione"));
                proposta.setStato(rs.getString("stato"));
                proposta.setPrezzoLavoro(rs.getFloat("prezzo_lavoro"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proposta;

    }

    }

