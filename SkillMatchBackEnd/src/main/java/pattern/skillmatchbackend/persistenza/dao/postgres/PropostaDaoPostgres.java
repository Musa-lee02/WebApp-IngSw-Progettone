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
                proposta.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getLong("id_lavoratore")));
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
    public Proposta findByPrimaryKey(long idAnnuncio, long idLavoratore) {
        Proposta proposta = null;
        String query = "SELECT * FROM proposta WHERE WHERE id_annuncio = ? and id_lavoratore = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, idAnnuncio);
            st.setLong(2,idLavoratore);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                proposta = new Proposta();
                proposta.setAnnuncioRelativo(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("id_annuncio")));
                proposta.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getLong("id_lavoratore")));
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

        /*String query = "INSERT INTO PROPOSTA VALUES (?, ?, ?, ?, ?, ?, ?)";

        /*if (findByPrimaryKey(proposta.getIdProposta()) != null)
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
            }*/


    }

    @Override
    public void delete(Proposta proposta) {
        String query = "DELETE FROM proposta WHERE id_annuncio = ? and id_lavoratore = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, proposta.getAnnuncioRelativo().getId());
            st.setLong(2,proposta.getLavoratore().getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Proposta> findByByForeignKeyLavoratore(long id) {

        List<Proposta> proposte = new LinkedList<>();
        String query = "SELECT * FROM proposta WHERE id_lavoratore = ?";

        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Proposta proposta = new Proposta();
                proposta.setAnnuncioRelativo(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("id_annuncio")));
                proposta.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getLong("id_lavoratore")));
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
    public Proposta findByForeignKeyAnnuncio(long id) {
        Proposta proposta = null;
        String query = "SELECT * FROM proposta WHERE WHERE id_annuncio = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                proposta = new Proposta();
                proposta.setAnnuncioRelativo(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("id_annuncio")));
                proposta.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getLong("id_lavoratore")));
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

