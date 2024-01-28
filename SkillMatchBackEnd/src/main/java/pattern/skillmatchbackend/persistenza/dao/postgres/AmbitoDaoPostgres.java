package pattern.skillmatchbackend.persistenza.dao.postgres;


import pattern.skillmatchbackend.model.Ambito;

import pattern.skillmatchbackend.persistenza.IdBroker;
import pattern.skillmatchbackend.persistenza.dao.AmbitoDao;
import java.util.List;
import java.sql.*;
import java.util.LinkedList;


public class AmbitoDaoPostgres implements AmbitoDao {

    private Connection conn;

    public AmbitoDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Ambito> findAll() {
        List<Ambito> ambiti = new LinkedList<>();
        String query = "SELECT * FROM ambito";

        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Ambito ambito = new Ambito();
                ambito.setId(rs.getLong("id_ambito"));
                ambito.setNome(rs.getString("nome"));
                ambiti.add(ambito);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ambiti;
    }

    @Override
    public Ambito findByPrimaryKey(long id) {
        Ambito ambito = null;
        String query = "SELECT * FROM ambito WHERE id_ambito = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                ambito = new Ambito();
                ambito.setId(rs.getLong("id_ambito"));
                ambito.setNome(rs.getString("nome"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ambito;
    }

    @Override
    public void saveOrUpdate(Ambito ambito) {
        String query = "INSERT INTO ambito VALUES (?, ?)";




        if (ambito.getId() != null)
            query = "UPDATE ambito SET id_ambito = ?, nome = ? WHERE id_ambito = ?";
        else
            ambito.setId(IdBroker.getId(conn));

        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, ambito.getId());
            st.setString(2, ambito.getNome());

            if(query.startsWith("UPDATE"))
                st.setLong(3, ambito.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Ambito ambito) {
        String query = "DELETE FROM ambito WHERE id_ambito = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, ambito.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Ambito> findByLavoratore(String username) {

        List<Ambito> ambiti = new LinkedList<>();
        String query = "SELECT ambito.id_ambito,ambito.nome FROM ambito,competente,lavoratore WHERE competente.username_lavoratore = ? and lavoratore.username = competente.username_lavoratore and ambito.id_ambito = competente.id_ambito";

        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Ambito ambito = new Ambito();
                ambito.setId(rs.getLong("id_ambito"));
                ambito.setNome(rs.getString("nome"));
                ambiti.add(ambito);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ambiti;
    }
}
