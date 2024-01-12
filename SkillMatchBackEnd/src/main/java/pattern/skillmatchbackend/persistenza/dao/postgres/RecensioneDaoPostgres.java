package pattern.skillmatchbackend.persistenza.dao.postgres;

import pattern.skillmatchbackend.model.Recensione;
import pattern.skillmatchbackend.persistenza.DBManager;

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
                recensione.setRecensore(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getLong(("id_cliente"))));
                recensione.setRecensito(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getLong(("id_lavoratore"))));
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
                recensione.setRecensore(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getLong("id_cliente")));
                recensione.setRecensito(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getLong(("id_lavoratore"))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recensione;
    }

    @Override
    public void saveOrUpdate(Recensione recensione) {

        String query = "INSERT INTO recensione VALUES (?, ?, ?, ?, ?, ?)";

        if (findByPrimaryKey(recensione.getIdRecensione()) != null)
            query = "UPDATE recensione " +
                    "SET titolo = ?, descrizione = ?, punteggio = ?, id_cliente = ?, id_lavoratore = ? " +
                    "WHERE id_recensione = ?";

        try {

            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, recensione.getTitolo());
            st.setString(2, recensione.getDescrizione());
            st.setInt(3, recensione.getPunteggio());
            st.setLong(4, recensione.getRecensore().getId());
            st.setLong(5, recensione.getRecensito().getId());
            st.setLong(6, recensione.getIdRecensione());
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
    public List<Recensione> findByForeignKeyLavoratore(long id) {
        return findByForeignKeyClienteoLavoratore(id,"id_lavoratore");
    }

    @Override
    public List<Recensione> findByForeignKeyCliente(long id) {
        return findByForeignKeyClienteoLavoratore(id,"id_cliente");
    }


    public List<Recensione> findByForeignKeyClienteoLavoratore(Long id, String profilo) {

        List <Recensione> recensioni = new LinkedList<>();
        String query = "SELECT * FROM recensione WHERE "+profilo+" = ?";

        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Recensione recensione = new Recensione();
                recensione.setIdRecensione(rs.getLong("id_recensione"));
                recensione.setTitolo(rs.getString("titolo"));
                recensione.setDescrizione(rs.getString("descrizione"));
                recensione.setPunteggio(rs.getInt("punteggio"));
                recensione.setRecensore(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getLong("id_cliente")));
                recensione.setRecensito(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getLong("id_lavoratore")));
                recensioni.add(recensione);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recensioni;

    }


}
