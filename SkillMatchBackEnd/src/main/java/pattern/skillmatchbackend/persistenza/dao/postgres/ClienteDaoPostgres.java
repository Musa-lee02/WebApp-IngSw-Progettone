package pattern.skillmatchbackend.persistenza.dao.postgres;

import pattern.skillmatchbackend.model.Cliente;
import pattern.skillmatchbackend.model.Image;
import pattern.skillmatchbackend.model.Utente;
import pattern.skillmatchbackend.persistenza.DBManager;
import pattern.skillmatchbackend.persistenza.dao.ClienteDao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ClienteDaoPostgres  implements ClienteDao {
    Connection conn;

    public ClienteDaoPostgres(Connection conn) {
        this.conn = conn;
    }


    @Override
    public List<Cliente> findAll() {
        return null;
    }

    @Override
    public Cliente findByPrimaryKey(long id) {
        Cliente cliente = null;
        String query = "SELECT * FROM cliente WHERE id_cliente = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                cliente.setId(rs.getLong("id_cliente"));
                cliente.setUs(rs.getString("id_cliente"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public void saveOrUpdate(Cliente cliente) {

    }

    @Override
    public void delete(Cliente cliente) {

    }
}
