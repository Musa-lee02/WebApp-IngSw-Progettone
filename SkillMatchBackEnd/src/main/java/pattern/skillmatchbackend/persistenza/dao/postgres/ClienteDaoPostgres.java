package pattern.skillmatchbackend.persistenza.dao.postgres;

import pattern.skillmatchbackend.model.Cliente;
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
        List<Cliente> clienti = new LinkedList<>();
        String query = "SELECT * FROM cliente";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Cliente cliente = new Cliente(DBManager.getInstance().getUtenteDao().findByPrimaryKey(rs.getString("username")));
                clienti.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clienti;

    }

    @Override
    public Cliente findByPrimaryKey(String username) {

        Cliente cliente = null;
        String query = "SELECT * FROM cliente WHERE username = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(DBManager.getInstance().getUtenteDao().findByPrimaryKey(rs.getString("username")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;}

    @Override
    public void saveOrUpdate(Cliente cliente) {
        String query = "INSERT INTO cliente VALUES (?)";

        if (findByPrimaryKey(cliente.getUsername()) != null)
            query = "UPDATE cliente SET username = ? WHERE username = ?";

        try {

            DBManager.getInstance().getUtenteDao().saveOrUpdate(cliente);
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, cliente.getUsername());

            if(query.startsWith("UPDATE"))
                st.setString(1, cliente.getUsername());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Cliente cliente) {
        DBManager.getInstance().getUtenteDao().delete(cliente);
    }

}
