package pattern.skillmatchbackend.persistenza.dao.postgres;

import pattern.skillmatchbackend.model.Cliente;
import pattern.skillmatchbackend.model.Image;
import pattern.skillmatchbackend.model.Lavoratore;
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
        List<Cliente> clienti = new LinkedList<>();
        String query = "SELECT id_cliente, username FROM cliente";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Cliente cliente = new Cliente(DBManager.getInstance().getUtenteDao().findByPrimaryKey(rs.getString("username")));
                cliente.setId(rs.getLong("id_cliente"));
                cliente.setChats(DBManager.getInstance().getChatDao().findByForeignKeyCliente(cliente.getId()));
                cliente.setRecensioni(DBManager.getInstance().getRecensioneDao().findByForeignKeyCliente(cliente.getId()));
                cliente.setAnnunci(DBManager.getInstance().getAnnuncioDao().findByForeignKeyCliente(cliente.getId()));
                cliente.setTransazionePagamento(DBManager.getInstance().getTransazionePagamentoDao().findByForeignKeyCliente(cliente.getId()));
                clienti.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clienti;

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
                cliente = new Cliente(DBManager.getInstance().getUtenteDao().findByPrimaryKey(rs.getString("username")));
                cliente.setId(rs.getLong("id_cliente"));
                cliente.setChats(DBManager.getInstance().getChatDao().findByForeignKeyCliente(cliente.getId()));
                cliente.setRecensioni(DBManager.getInstance().getRecensioneDao().findByForeignKeyCliente(cliente.getId()));
                cliente.setTransazionePagamento(DBManager.getInstance().getTransazionePagamentoDao().findByForeignKeyCliente(cliente.getId()));
                cliente.setNotifiche(DBManager.getInstance().getNotificaDao().findByForeignKeyCliente(cliente.getId()));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }


    public Cliente findByPrimaryKey(String username) {

        Cliente cliente = null;
        String query = "SELECT * FROM cliente WHERE username = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(DBManager.getInstance().getUtenteDao().findByPrimaryKey(rs.getString("username")));
                cliente.setId(rs.getLong("id_cliente"));
                cliente.setChats(DBManager.getInstance().getChatDao().findByForeignKeyCliente(cliente.getId()));
                cliente.setRecensioni(DBManager.getInstance().getRecensioneDao().findByForeignKeyCliente(cliente.getId()));
                cliente.setTransazionePagamento(DBManager.getInstance().getTransazionePagamentoDao().findByForeignKeyCliente(cliente.getId()));
                cliente.setNotifiche(DBManager.getInstance().getNotificaDao().findByForeignKeyCliente(cliente.getId()));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;}

    @Override
    public void saveOrUpdate(Cliente cliente) {
        String query = "INSERT INTO cliente VALUES (?,?)";

        if (findByPrimaryKey(cliente.getId()) != null)
            query = "UPDATE cliente SET id_cliente = ? , username = ? ";

        try {
            DBManager.getInstance().getUtenteDao().saveOrUpdate(cliente);
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, cliente.getId());
            st.setString(2, cliente.getUsername());
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
