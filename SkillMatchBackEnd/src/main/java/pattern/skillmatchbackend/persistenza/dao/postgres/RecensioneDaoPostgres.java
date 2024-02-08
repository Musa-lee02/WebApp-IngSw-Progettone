package pattern.skillmatchbackend.persistenza.dao.postgres;

import pattern.skillmatchbackend.model.Recensione;
import pattern.skillmatchbackend.persistenza.DBManager;

import pattern.skillmatchbackend.persistenza.IdBroker;
import pattern.skillmatchbackend.persistenza.dao.RecensioneDao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class RecensioneDaoPostgres implements RecensioneDao {

    Connection conn;

    public RecensioneDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Recensione> findAll() {

        List <Recensione> recensioni = new LinkedList<>();
        String query = "SELECT * FROM recensione";
        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                Recensione recensione = new Recensione();
                recensione.setIdRecensione(rs.getLong("id_recensione"));
                recensione.setTitolo(rs.getString("titolo"));
                recensione.setDescrizione(rs.getString("descrizione"));
                recensione.setPunteggio(rs.getInt("punteggio"));
                recensione.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username_cliente")));
                recensione.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username_lavoratore")));
                recensioni.add(recensione);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recensioni;

    }

    @Override
    public Recensione findByPrimaryKey(long id) {
        Recensione recensione = null;
        String query = "SELECT * FROM recensione WHERE id_recensione = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                recensione = new Recensione();
                recensione.setIdRecensione(rs.getLong("id_recensione"));
                recensione.setTitolo(rs.getString("titolo"));
                recensione.setDescrizione(rs.getString("descrizione"));
                recensione.setPunteggio(rs.getInt("punteggio"));
                recensione.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username_cliente")));
                recensione.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username_lavoratore")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recensione;
    }

    @Override
    public void saveOrUpdate(Recensione recensione) {

        String query = "INSERT INTO recensione VALUES (?, ?, ?, ?, ?, ?)";

        if (recensione.getIdRecensione() != -1)
            query = "UPDATE recensione " +
                    "SET id_recensione = ?, titolo = ?, descrizione = ?, punteggio = ?, username_cliente = ?, username_lavoratore = ? " +
                    "WHERE id_recensione = ?";
        else
            recensione.setIdRecensione(IdBroker.getId(conn));


        try {

            PreparedStatement st = conn.prepareStatement(query);

            st.setLong(1,recensione.getIdRecensione());
            st.setString(2, recensione.getTitolo());
            st.setString(3, recensione.getDescrizione());
            st.setInt(4, recensione.getPunteggio());
            st.setString(5, recensione.getCliente().getUsername());
            st.setString(6, recensione.getLavoratore().getUsername());

            if(query.startsWith("UPDATE"))
                st.setLong(7,recensione.getIdRecensione());

            System.out.println("Query rec: " + st.toString());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Recensione recensione) {
        String query = "DELETE FROM recensione WHERE id_recensione = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, recensione.getIdRecensione());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Recensione> findByForeignKeyLavoratore(String username) {
        return findByForeignKeyClienteoLavoratore(username,"username_lavoratore");
    }

    @Override
    public List<Recensione> findByForeignKeyCliente(String username) {
        return findByForeignKeyClienteoLavoratore(username,"username_cliente");
    }


    public List<Recensione> findByForeignKeyClienteoLavoratore(String username, String profilo) {

        List <Recensione> recensioni = new LinkedList<>();
        String query = "SELECT * FROM recensione WHERE "+profilo+" = ?";

        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            //System.out.println("Query: " + rs.getString("descrizione"));

            while (rs.next()) {
                Recensione recensione = new Recensione();
                recensione.setIdRecensione(rs.getLong("id_recensione"));
                recensione.setTitolo(rs.getString("titolo"));
                recensione.setDescrizione(rs.getString("descrizione"));
                recensione.setPunteggio(rs.getInt("punteggio"));
                recensione.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username_cliente")));
                recensione.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username_lavoratore")));
                recensioni.add(recensione);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recensioni;

    }


}
