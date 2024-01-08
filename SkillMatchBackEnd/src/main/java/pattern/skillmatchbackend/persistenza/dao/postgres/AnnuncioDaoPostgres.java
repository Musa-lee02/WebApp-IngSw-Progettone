package pattern.skillmatchbackend.persistenza.dao.postgres;

import pattern.skillmatchbackend.model.Ambito;
import pattern.skillmatchbackend.model.Annuncio;
import pattern.skillmatchbackend.model.Image;
import pattern.skillmatchbackend.persistenza.dao.AnnuncioDao;
import pattern.skillmatchbackend.persistenza.DBManager;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AnnuncioDaoPostgres implements AnnuncioDao {

    Connection conn;

    public AnnuncioDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Annuncio> findAll() {

        List<Annuncio> annunci = new LinkedList<>();
        String query = "SELECT * FROM ANNUNCIO WHERE id = ?";
        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Annuncio annuncio = new Annuncio();
                annuncio.setIdAnnuncio(rs.getLong("id"));
                annuncio.setTitolo(rs.getString("titolo"));
                annuncio.setDescrizione(rs.getString("descrizione"));
                annuncio.setDataDiScadenza(rs.getDate("data_di_scadenza"));
                annuncio.setProvinciaAnnuncio(rs.getString("provincia_annuncio"));
                annuncio.setAmbito(DBManager.getInstance().getAmbitoDao().findByPrimaryKey(rs.getLong("ambito")));
                annuncio.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username")));
                annuncio.setImage(new Image(rs.getString("image_path")));
                annuncio.setProposteRicevute(DBManager.getInstance().getPropostaDao().findByPrimaryKeyAnnuncio(annuncio.getIdAnnuncio()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return annunci;
    }


    @Override
    public Annuncio findByPrimaryKey(long id) {
        Annuncio annuncio = null;
        String query = "SELECT * FROM ANNUNCIO WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                annuncio = new Annuncio();
                annuncio.setIdAnnuncio(rs.getLong("id"));
                annuncio.setTitolo(rs.getString("titolo"));
                annuncio.setDescrizione(rs.getString("descrizione"));
                annuncio.setDataDiScadenza(rs.getDate("data_di_scadenza"));
                annuncio.setProvinciaAnnuncio(rs.getString("provincia_annuncio"));
                annuncio.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username")));
                annuncio.setImage(new Image(rs.getString("image_path")));
                annuncio.setProposteRicevute(DBManager.getInstance().getPropostaDao().findByPrimaryKeyAnnuncio(annuncio.getIdAnnuncio()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return annuncio;
    }

    @Override
    public void saveOrUpdate(Annuncio annuncio) {

        String query = "INSERT INTO ANNUNCIO VALUES (?, ?, ?, ?, ?, ?, ?,?)";

        if (findByPrimaryKey(annuncio.getIdAnnuncio()) != null)
            query = "UPDATE ANNUNCIO SET titolo = ?, " + "descrizione = ?, " + "data_di_scadenza = ?, " + "provincia_annuncio = ?, " + "idCliente = ?, " + "image_path = ? " + "WHERE id = ?";

            try {
                PreparedStatement st = conn.prepareStatement(query);

                st.setLong(1, annuncio.getIdAnnuncio());
                st.setString(2, annuncio.getTitolo());
                st.setString(3, annuncio.getDescrizione());
                st.setDate(4, annuncio.getDataDiScadenza());
                st.setString(5, annuncio.getProvinciaAnnuncio());
                st.setString(6, annuncio.getCliente().getUsername());
                st.setString(7, annuncio.getImage().getPath());
                st.setLong(8,annuncio.getAmbito().getId());

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    @Override
    public void delete(Annuncio annuncio) {
        String query = "DELETE FROM ANNUNCIO WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, annuncio.getIdAnnuncio());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Annuncio> findByPrimaryCliente(String username) {
        List<Annuncio> annunci = new LinkedList<>();
        String query = "SELECT * FROM ANNUNCIO WHERE id = ?";
        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                Annuncio annuncio = new Annuncio();
                annuncio.setIdAnnuncio(rs.getLong("id"));
                annuncio.setTitolo(rs.getString("titolo"));
                annuncio.setDescrizione(rs.getString("descrizione"));
                annuncio.setDataDiScadenza(rs.getDate("data_di_scadenza"));
                annuncio.setProvinciaAnnuncio(rs.getString("provincia_annuncio"));
                annuncio.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username")));
                annuncio.setImage(new Image(rs.getString("image_path")));
                annuncio.setProposteRicevute(DBManager.getInstance().getPropostaDao().findByPrimaryKeyAnnuncio(annuncio.getIdAnnuncio()));
                annuncio.setAmbito(DBManager.getInstance().getAmbitoDao().findByPrimaryKey(annuncio.getAmbito().getId()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return annunci;
    }

    @Override
    public List<Annuncio> annunciPerMe(String provincia, Ambito ambito) {
        List<Annuncio> annunci = new LinkedList<>();
        String query = "SELECT * FROM ANNUNCIO WHERE id = ? and provincia=?";
        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, ambito.getId());
            st.setString(2,provincia);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Annuncio annuncio = new Annuncio();
                annuncio.setIdAnnuncio(rs.getLong("id"));
                annuncio.setTitolo(rs.getString("titolo"));
                annuncio.setDescrizione(rs.getString("descrizione"));
                annuncio.setDataDiScadenza(rs.getDate("data_di_scadenza"));
                annuncio.setProvinciaAnnuncio(rs.getString("provincia_annuncio"));
                annuncio.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username")));
                annuncio.setImage(new Image(rs.getString("image_path")));
                annuncio.setProposteRicevute(DBManager.getInstance().getPropostaDao().findByPrimaryKeyAnnuncio(annuncio.getIdAnnuncio()));
                annuncio.setAmbito(DBManager.getInstance().getAmbitoDao().findByPrimaryKey(annuncio.getAmbito().getId()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return annunci;
    }


}
