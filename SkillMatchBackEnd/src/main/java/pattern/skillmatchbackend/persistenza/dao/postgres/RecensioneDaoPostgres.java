package pattern.skillmatchbackend.persistenza.dao.postgres;

import pattern.skillmatchbackend.persistenza.DBManager;
import pattern.skillmatchbackend.persistenza.dao.RecensioneDao;
import pattern.skillmatchbackend.model.Recensione;



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
        String query = "SELECT * FROM RECENSIONE WHERE id = ?";
        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Recensione recensione = new Recensione();
                recensione.setIdRecensione(rs.getLong("id"));
                recensione.setTitolo(rs.getString("titolo"));
                recensione.setDescrizione(rs.getString("descrizione"));
                recensione.setPunteggio(rs.getInt("punteggio"));
                recensione.setRecensore(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username")));
                recensione.setRecensito(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username")));
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
                recensione.setRecensore(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username")));
                recensione.setRecensito(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recensione;
    }

    @Override
    public void saveOrUpdate(Recensione recensione) {

        String query = "INSERT INTO RECENSIONE VALUES (?, ?, ?, ?, ?, ?)";

        if (findByPrimaryKey(recensione.getIdRecensione()) != null)
            query = "UPDATE RECENSIONE SET titolo = ?, " + "descrizione = ?, " + "punteggio = ?, " + "idRecensore = ?, " + "idRecensito = ? " + "WHERE id = ?";

        try {
            PreparedStatement st = conn.prepareStatement(query);

            st.setLong(1, recensione.getIdRecensione());
            st.setString(2, recensione.getTitolo());
            st.setString(3, recensione.getDescrizione());
            st.setFloat(4, recensione.getPunteggio());
            st.setString(5, recensione.getRecensore().getUsername());
            st.setString(6, recensione.getRecensito().getUsername());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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

    @Override
    public List<Recensione>  findByPrimaryKeyLavoratore(String username) {

        List <Recensione> recensioni = new LinkedList<>();
        String query = "SELECT * FROM RECENSIONE WHERE idLavoratore = ?";

        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Recensione recensione = new Recensione();
                recensione.setIdRecensione(rs.getLong("id"));
                recensione.setTitolo(rs.getString("titolo"));
                recensione.setDescrizione(rs.getString("descrizione"));
                recensione.setPunteggio(rs.getInt("punteggio"));
                recensione.setRecensore(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username")));
                recensione.setRecensito(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username")));
                recensioni.add(recensione);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recensioni;

    }



    @Override
    public List<Recensione> findByPrimaryKeyCliente(String username) {

        List <Recensione> recensioni = new LinkedList<>();
        String query = "SELECT * FROM RECENSIONE WHERE idCliente = ?";

        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Recensione recensione = new Recensione();
                recensione.setIdRecensione(rs.getLong("id"));
                recensione.setTitolo(rs.getString("titolo"));
                recensione.setDescrizione(rs.getString("descrizione"));
                recensione.setPunteggio(rs.getInt("punteggio"));
                recensione.setRecensore(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username")));
                recensione.setRecensito(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username")));
                recensioni.add(recensione);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recensioni;

    }


}
