package pattern.skillmatchbackend.persistenza.dao.postgres;

import pattern.skillmatchbackend.model.Cliente;
import pattern.skillmatchbackend.persistenza.dao.ClienteDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class ClienteDaoPostgres implements ClienteDao {

    Connection conn;

    public ClienteDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Cliente> findAll() {
        return null;
    }

    @Override
    public Cliente findByUsername(String username) {

        Cliente cliente = null;
        String query = "SELECT * FROM cliente where username = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setUsername(rs.getString("username"));
                cliente.setPassword(rs.getString("password"));
                cliente.setEmail(rs.getString("email"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCognome(rs.getString("cognome"));
                cliente.setIndirizzo(rs.getString("via"));
                cliente.setNumero_civico(rs.getString("numero_civico"));
                cliente.setCap(rs.getString("cap"));
                cliente.setCitta(rs.getString("citta"));
            }
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return cliente;
    }

    @Override
    public void save(Cliente user) {

    }

    @Override
    public List<Cliente> findByNome(String nome) {
        return null;
    }

    @Override
    public List<Cliente> findByCognome(String cognome) {
        return null;
    }

    @Override
    public List<Cliente> findByNomeCognome(String nome, String cognome) {
        return null;
    }

    @Override
    public List<Cliente> findByEmail(String email) {
        return null;
    }

    @Override
    public List<Cliente> findByIndirizzo(String indirizzo) {
        return null;
    }

    @Override
    public List<Cliente> findByCitta(String citta) {
        return null;
    }

    @Override
    public List<Cliente> findByProvincia(String provincia) {
        return null;
    }
}