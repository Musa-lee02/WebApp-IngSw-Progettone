package application.persistenza.dao.postgres;

import application.model.image.Image;
import application.model.user.Cliente;
import application.persistenza.DBManager;
import application.persistenza.dao.ClienteDao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ClienteDaoPostgres implements ClienteDao {
    Connection conn;

    public ClienteDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Cliente> findAll() {

        List<Cliente> clienti = new LinkedList<>();
        String query = "SELECT * FROM CLIENTE WHERE username = ?";
        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setUsername(rs.getString("username"));
                cliente.setPassword(rs.getString("password"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCognome(rs.getString("cognome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setIndirizzo(rs.getString("indirizzo"));
                cliente.setNumeroCivico(rs.getString("numero_civico"));
                cliente.setCitta(rs.getString("citta"));
                cliente.setProvinciaServizio(rs.getString("provinciaDiServizio"));
                cliente.setCap(rs.getString("cap"));
                cliente.setRuolo(rs.getBoolean("ruolo"));
                cliente.setRecensioni(DBManager.getInstance().getRecensioneDao().findByPrimaryKeyCliente(cliente.getUsername()));
                cliente.setTransazioni(DBManager.getInstance().getTransazioneDao().findByPrimaryKeyCliente(cliente.getUsername()));
                cliente.setAnnunci(DBManager.getInstance().getAnnuncioDao().findByPrimaryCliente(cliente.getUsername()));
                cliente.setImage(new Image(rs.getString("immagine")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clienti;
    }

    @Override
    public Cliente findByPrimaryKey(String username) {
        Cliente cliente = null;
        String query = "SELECT * FROM CLIENTE WHERE username = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setUsername(rs.getString("username"));
                cliente.setPassword(rs.getString("password"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCognome(rs.getString("cognome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setIndirizzo(rs.getString("indirizzo"));
                cliente.setNumeroCivico(rs.getString("numero_civico"));
                cliente.setCitta(rs.getString("citta"));
                cliente.setProvinciaServizio(rs.getString("provinciaDiServizio"));
                cliente.setCap(rs.getString("cap"));
                cliente.setRuolo(rs.getBoolean("ruolo"));
                cliente.setRecensioni(DBManager.getInstance().getRecensioneDao().findByPrimaryKeyCliente(cliente.getUsername()));
                cliente.setTransazioni(DBManager.getInstance().getTransazioneDao().findByPrimaryKeyCliente(cliente.getUsername()));
                cliente.setAnnunci(DBManager.getInstance().getAnnuncioDao().findByPrimaryCliente(cliente.getUsername()));
                cliente.setImage(new Image(rs.getString("immagine")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public void saveOrUpdate(Cliente cliente) {

        String query = "INSERT INTO CLIENTE VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

        if (findByPrimaryKey(cliente.getUsername()) != null)
            query  = "UPDATE CLIENTE SET password = ?, " + "nome = ?, " + "cognome = ?, " + "email = ?, " + "indirizzo = ?, " + "numero_civico = ?, " + "citta = ?, " + "cap = ?, " + "ruolo = ? " + "WHERE username = ?";

            try {
                PreparedStatement st = conn.prepareStatement(query);

                st.setString(1, cliente.getUsername());
                st.setString(2, cliente.getPassword());
                st.setString(3, cliente.getNome());
                st.setString(4, cliente.getCognome());
                st.setString(5, cliente.getEmail());
                st.setString(6, cliente.getIndirizzo());
                st.setString(7, cliente.getNumeroCivico());
                st.setString(8, cliente.getCitta());
                st.setString(9, cliente.getCap());
                st.setBoolean(10, cliente.isRuolo());
                st.setString(11, cliente.getProvinciaServizio());
                st.setString(12,cliente.getImage().getPath());

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    @Override
    public void delete(Cliente cliente) {
        String query = "DELETE FROM CLIENTE WHERE username = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, cliente.getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
