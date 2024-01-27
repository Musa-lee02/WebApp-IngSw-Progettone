package pattern.skillmatchbackend.persistenza.dao.postgres;

import pattern.skillmatchbackend.model.Annuncio;
import pattern.skillmatchbackend.model.Image;
import pattern.skillmatchbackend.persistenza.IdBroker;
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
        String query = "SELECT * FROM annuncio";
        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                Annuncio annuncio = new Annuncio();
                annuncio.setId(rs.getLong("id_annuncio"));
                annuncio.setTitolo(rs.getString("titolo"));
                annuncio.setDescrizione(rs.getString("descrizione"));
                annuncio.setDataDiScadenza(rs.getDate("data_di_scadenza"));
                annuncio.setProvinciaAnnuncio(rs.getString("provincia_annuncio"));
                annuncio.setImage(rs.getString("img_annuncio"));
               // annuncio.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username_cliente")));
               // annuncio.setAmbito(DBManager.getInstance().getAmbitoDao().findByPrimaryKey(rs.getLong("id_ambito")));
               // annuncio.setProposta(DBManager.getInstance().getPropostaDao().findByForeignKeyAnnuncio(annuncio.getId()));
                annunci.add(annuncio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return annunci;
    }


    @Override
    public Annuncio findByPrimaryKey(long id) {
        Annuncio annuncio = null;
        String query = "SELECT * FROM annuncio WHERE id_annuncio = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                annuncio = new Annuncio();
                annuncio.setId(rs.getLong("id_annuncio"));
                annuncio.setTitolo(rs.getString("titolo"));
                annuncio.setDescrizione(rs.getString("descrizione"));
                annuncio.setDataDiScadenza(rs.getDate("data_di_scadenza"));
                annuncio.setProvinciaAnnuncio(rs.getString("provincia_annuncio"));
                annuncio.setImage(rs.getString("img_annuncio"));
                annuncio.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username_cliente")));
                annuncio.setAmbito(DBManager.getInstance().getAmbitoDao().findByPrimaryKey(rs.getLong("id_ambito")));
                annuncio.setProposta(DBManager.getInstance().getPropostaDao().findByForeignKeyAnnuncio(annuncio.getId()));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return annuncio;
    }

    @Override
    public void saveOrUpdate(Annuncio annuncio) {

        String query = "INSERT INTO annuncio VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        //System.out.println(findByPrimaryKey(annuncio.getId()));
        if (annuncio.getId() != null && findByPrimaryKey(annuncio.getId()) != null) {
            query = "UPDATE annuncio " +
                    "SET id_annuncio = ? , titolo = ?, descrizione = ?, data_di_scadenza = ?, provincia_annuncio = ?, " +
                    "img_annuncio = ?, username_cliente = ?, id_ambito = ? " +
                    "WHERE id_annuncio = ?";
        }
        else {
            annuncio.setId(IdBroker.getId(conn));
        }
            try {

                PreparedStatement st = conn.prepareStatement(query);

                st.setLong(1, annuncio.getId());
                st.setString(2, annuncio.getTitolo());
                st.setString(3, annuncio.getDescrizione());
                st.setDate(4, annuncio.getDataDiScadenza());
                st.setString(5, annuncio.getProvinciaAnnuncio());
                st.setString(6, annuncio.getImage());
                st.setString(7, annuncio.getCliente().getUsername());
                st.setLong(8, annuncio.getAmbito().getId());

                if(query.startsWith("UPDATE")) {
                    st.setLong(9, annuncio.getId());
                }

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    @Override
    public void delete(Annuncio annuncio) {
        String query = "DELETE FROM annuncio WHERE id_annuncio = ?";
        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, annuncio.getId());
            st.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<Annuncio> findByForeignKeyCliente(String username) {
        List<Annuncio> annunci = new LinkedList<>();
        String query = "SELECT * FROM annuncio WHERE username_cliente = ?";
        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Annuncio annuncio = new Annuncio();
                annuncio.setId(rs.getLong("id_annuncio"));
                annuncio.setTitolo(rs.getString("titolo"));
                annuncio.setDescrizione(rs.getString("descrizione"));
                annuncio.setDataDiScadenza(rs.getDate("data_di_scadenza"));
                annuncio.setProvinciaAnnuncio(rs.getString("provincia_annuncio"));
                annuncio.setImage(rs.getString("img_annuncio"));
                annuncio.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username_cliente")));
                annuncio.setAmbito(DBManager.getInstance().getAmbitoDao().findByPrimaryKey(rs.getLong("id_ambito")));
                annuncio.setProposta(DBManager.getInstance().getPropostaDao().findByForeignKeyAnnuncio(annuncio.getId()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return annunci;
    }

    @Override
    public List<Annuncio> annunciPerMe(String provincia, String username) {
        List<Annuncio> annunci = new LinkedList<>();
        String query = "SELECT annuncio.id_annuncio, annuncio.titolo, annuncio.descrizione,annuncio.data_di_scadenza,annuncio.provincia_annuncio,annuncio.img_annuncio,annuncio.username_cliente,annuncio.id_ambito " +
                "FROM annuncio,ambito,competente " +
                "WHERE provincia_annuncio = ? and competente.username_lavoratore = ? and annuncio.id_ambito = ambito.id_ambito and competente.id_ambito = ambito.id_ambito ";
        try {
            //TODO DATA DI SCADENZA E STATO
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1,provincia);
            st.setString(2, username);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Annuncio annuncio = new Annuncio();
                annuncio.setId(rs.getLong("id_annuncio"));
                annuncio.setTitolo(rs.getString("titolo"));
                annuncio.setDescrizione(rs.getString("descrizione"));
                annuncio.setDataDiScadenza(rs.getDate("data_di_scadenza"));
                annuncio.setProvinciaAnnuncio(rs.getString("provincia_annuncio"));
                annuncio.setImage(rs.getString("img_annuncio"));
                //annuncio.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username_cliente")));
                //annuncio.setAmbito(DBManager.getInstance().getAmbitoDao().findByPrimaryKey(rs.getLong("id_ambito")));
                //annuncio.setProposta(DBManager.getInstance().getPropostaDao().findByForeignKeyAnnuncio(annuncio.getId()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return annunci;
    }


}
