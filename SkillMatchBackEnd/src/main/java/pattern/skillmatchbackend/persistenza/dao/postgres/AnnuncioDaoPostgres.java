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
        String query = "SELECT annuncio.id_annuncio, annuncio.titolo, annuncio.descrizione," +
                " annuncio.data_di_scadenza, annuncio.provincia_annuncio, annuncio.img_annuncio," +
                " annuncio.username_cliente, annuncio.id_ambito " +
                "FROM annuncio" +
                " EXCEPT " +
                "SELECT annuncio.id_annuncio, annuncio.titolo, annuncio.descrizione," +
                " annuncio.data_di_scadenza, annuncio.provincia_annuncio, annuncio.img_annuncio," +
                " annuncio.username_cliente, annuncio.id_ambito " +
                "FROM annuncio,proposta " +
                " WHERE proposta.id_annuncio = annuncio.id_annuncio AND  proposta.stato = 'accettata' ";
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
                annuncio.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username_cliente")));
                annuncio.setAmbito(DBManager.getInstance().getAmbitoDao().findByPrimaryKey(rs.getLong("id_ambito")));
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


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return annuncio;
    }

    @Override
    public void saveOrUpdate(Annuncio annuncio) {

        String query = "INSERT INTO annuncio VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        if (annuncio.getId() != null) {
            query = "UPDATE annuncio " +
                    "SET id_annuncio = ?, titolo = ?, descrizione = ?, data_di_scadenza = ?, provincia_annuncio = ?, " +
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
            System.out.println(st);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Annuncio annuncio = new Annuncio();
                annuncio.setId(rs.getLong("id_annuncio"));
                annuncio.setTitolo(rs.getString("titolo"));
                annuncio.setDescrizione(rs.getString("descrizione"));
                annuncio.setDataDiScadenza(rs.getDate("data_di_scadenza"));
                annuncio.setProvinciaAnnuncio(rs.getString("provincia_annuncio"));
                annuncio.setImage(rs.getString("img_annuncio"));
                annuncio.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username_cliente")));
                annuncio.setAmbito(DBManager.getInstance().getAmbitoDao().findByPrimaryKey(rs.getLong("id_ambito")));
                annunci.add(annuncio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return annunci;
    }

    @Override
    public List<Annuncio> getAnnunciFinalizzati(String username) {
        List<Annuncio> annunci = new LinkedList<>();
        String query = "SELECT * FROM annuncio, proposta WHERE username_cliente = ? AND annuncio.id_annuncio = proposta.id_annuncio AND proposta.stato = 'accettata'";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            System.out.println(st);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Annuncio annuncio = new Annuncio();
                annuncio.setId(rs.getLong("id_annuncio"));
                annuncio.setTitolo(rs.getString("titolo"));
                annuncio.setDescrizione(rs.getString("descrizione"));
                annuncio.setDataDiScadenza(rs.getDate("data_di_scadenza"));
                annuncio.setProvinciaAnnuncio(rs.getString("provincia_annuncio"));
                annuncio.setImage(rs.getString("img_annuncio"));
                annuncio.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username_cliente")));
                annuncio.setAmbito(DBManager.getInstance().getAmbitoDao().findByPrimaryKey(rs.getLong("id_ambito")));
                annunci.add(annuncio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return annunci;
    }

    @Override
    public List<Annuncio> getAnnunciByAmbitoAndProvincia(String nome, String provincia) {
        List<Annuncio> annunci = new LinkedList<>();
        /*String query = "SELECT *" +
                "FROM annuncio, ambito " +
                " WHERE ambito.nome = ? AND provincia_annuncio = ? AND ambito.id_ambito = annuncio.id_ambito";*/

        String query = "SELECT annuncio.id_annuncio, annuncio.titolo, annuncio.descrizione," +
                " annuncio.data_di_scadenza, annuncio.provincia_annuncio, annuncio.img_annuncio," +
                " annuncio.username_cliente, annuncio.id_ambito " +
                "FROM annuncio,ambito " +
                "WHERE ambito.nome = ? AND provincia_annuncio = ? AND ambito.id_ambito = annuncio.id_ambito"+
                " EXCEPT " +
                "SELECT annuncio.id_annuncio, annuncio.titolo, annuncio.descrizione," +
                " annuncio.data_di_scadenza, annuncio.provincia_annuncio, annuncio.img_annuncio," +
                " annuncio.username_cliente, annuncio.id_ambito " +
                "FROM annuncio,proposta,ambito " +
                " WHERE proposta.id_annuncio = annuncio.id_annuncio AND  proposta.stato = 'accettata' " +
                " AND ambito.id_ambito = annuncio.id_ambito AND ambito.nome = ? AND provincia_annuncio = ?";
        try {
            //TODO DATA DI SCADENZA E STATO
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1,nome);
            st.setString(2,provincia);
            st.setString(3,nome);
            st.setString(4,provincia);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Annuncio annuncio = new Annuncio();
                annuncio.setId(rs.getLong("id_annuncio"));
                annuncio.setTitolo(rs.getString("titolo"));
                annuncio.setDescrizione(rs.getString("descrizione"));
                annuncio.setDataDiScadenza(rs.getDate("data_di_scadenza"));
                annuncio.setProvinciaAnnuncio(rs.getString("provincia_annuncio"));
                annuncio.setImage(rs.getString("img_annuncio"));
                annuncio.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username_cliente")));
                annuncio.setAmbito(DBManager.getInstance().getAmbitoDao().findByPrimaryKey(rs.getLong("id_ambito")));
                annunci.add(annuncio);
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
                annuncio.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username_cliente")));
                annuncio.setAmbito(DBManager.getInstance().getAmbitoDao().findByPrimaryKey(rs.getLong("id_ambito")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return annunci;
    }

    @Override
    public List<Annuncio> findByLavoratore(String username) {
        List<Annuncio> annunci = new LinkedList<>();
        String query = "SELECT annuncio.id_annuncio, annuncio.titolo, annuncio.descrizione," +
                " annuncio.data_di_scadenza, annuncio.provincia_annuncio, annuncio.img_annuncio," +
                " annuncio.username_cliente, annuncio.id_ambito FROM annuncio, chat, proposta WHERE " +
                " chat.username_lavoratore = ? AND " +
                " chat.id_annuncio = annuncio.id_annuncio AND" +
                " chat.id_annuncio = proposta.id_annuncio AND" +
                " proposta.stato <> 'accettata'" +
                " UNION SELECT annuncio.id_annuncio, annuncio.titolo, annuncio.descrizione," +
                " annuncio.data_di_scadenza, annuncio.provincia_annuncio, annuncio.img_annuncio," +
                " annuncio.username_cliente, annuncio.id_ambito FROM annuncio " +
                " INNER JOIN chat ON annuncio.id_annuncio = chat.id_annuncio " +
                " LEFT JOIN proposta ON annuncio.id_annuncio = proposta.id_annuncio " +
                " WHERE chat.username_lavoratore = ? " +
                " AND proposta.id_annuncio IS NULL";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            st.setString(2, username);
            System.out.println(st);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Annuncio annuncio = new Annuncio();
                annuncio.setId(rs.getLong("id_annuncio"));
                annuncio.setTitolo(rs.getString("titolo"));
                annuncio.setDescrizione(rs.getString("descrizione"));
                annuncio.setDataDiScadenza(rs.getDate("data_di_scadenza"));
                annuncio.setProvinciaAnnuncio(rs.getString("provincia_annuncio"));
                annuncio.setImage(rs.getString("img_annuncio"));
                annuncio.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username_cliente")));
                annuncio.setAmbito(DBManager.getInstance().getAmbitoDao().findByPrimaryKey(rs.getLong("id_ambito")));
                annunci.add(annuncio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return annunci;
    }

    @Override
    public List<Annuncio> getAnnunciWithChat(String username) {

        List<Annuncio> annunci = new LinkedList<>();

        String query = "SELECT annuncio.id_annuncio, annuncio.titolo, annuncio.descrizione," +
                " annuncio.data_di_scadenza, annuncio.provincia_annuncio, annuncio.img_annuncio," +
                " annuncio.username_cliente, annuncio.id_ambito" +
                " FROM annuncio, proposta WHERE username_cliente = ? AND" +
                " annuncio.id_annuncio = proposta.id_annuncio AND " +
                " proposta.stato <> 'accettata'" +
                " UNION SELECT annuncio.id_annuncio, annuncio.titolo, annuncio.descrizione," +
                " annuncio.data_di_scadenza, annuncio.provincia_annuncio, annuncio.img_annuncio," +
                " annuncio.username_cliente, annuncio.id_ambito FROM annuncio " +
                " INNER JOIN chat ON annuncio.id_annuncio = chat.id_annuncio " +
                " LEFT JOIN proposta ON annuncio.id_annuncio = proposta.id_annuncio" +
                " WHERE annuncio.username_cliente = ? " +
                " AND proposta.id_annuncio IS NULL";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            st.setString(2, username);
            System.out.println(st);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Annuncio annuncio = new Annuncio();
                annuncio.setId(rs.getLong("id_annuncio"));
                annuncio.setTitolo(rs.getString("titolo"));
                annuncio.setDescrizione(rs.getString("descrizione"));
                annuncio.setDataDiScadenza(rs.getDate("data_di_scadenza"));
                annuncio.setProvinciaAnnuncio(rs.getString("provincia_annuncio"));
                annuncio.setImage(rs.getString("img_annuncio"));
                annuncio.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username_cliente")));
                annuncio.setAmbito(DBManager.getInstance().getAmbitoDao().findByPrimaryKey(rs.getLong("id_ambito")));
                annunci.add(annuncio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return annunci;
    }


}
