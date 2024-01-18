package pattern.skillmatchbackend.persistenza.dao.postgres;

import pattern.skillmatchbackend.model.TransazionePagamento;
import pattern.skillmatchbackend.persistenza.DBManager;

import pattern.skillmatchbackend.persistenza.dao.TransazionePagamentoDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TransazionePagamentoDaoPostgres implements TransazionePagamentoDao {

    Connection conn;

    public TransazionePagamentoDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<TransazionePagamento> findAll() {

        List<TransazionePagamento> transazioni = new LinkedList<>();
        String query = "SELECT * FROM transazione_pagamento";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                TransazionePagamento transazione = new TransazionePagamento();
                transazione.setId(rs.getLong("id_transazione"));
                transazione.setImporto(rs.getFloat("importo"));
                transazione.setDataTransazione(rs.getTimestamp("data"));
                transazione.setMetodoPagamento(rs.getString("metodo_di_pagamento"));
                transazione.setMittente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username_cliente")));
                transazione.setDestinatario(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username_lavoratore")));
                transazioni.add(transazione);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transazioni;

    }

    @Override
    public TransazionePagamento findByPrimaryKey(long id) {
        TransazionePagamento transazione = null;
        String query = "SELECT * FROM transazione_pagamento WHERE id_transazione_pagamento = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                transazione = new TransazionePagamento();
                transazione.setId(rs.getLong("id_transazione"));
                transazione.setImporto(rs.getFloat("importo"));
                transazione.setDataTransazione(rs.getTimestamp("data"));
                transazione.setMetodoPagamento(rs.getString("metodo_di_pagamento"));
                transazione.setMittente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username_cliente")));
                transazione.setDestinatario(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username_lavoratore")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transazione;
    }

    @Override
    public void saveOrUpdate(TransazionePagamento transazione) {

        String query = "INSERT INTO transazione_pagamento VALUES (?, ?, ?, ?, ?, ?)";


        if (findByPrimaryKey(transazione.getId()) != null)
            query = "UPDATE transazione_pagamento SET data_transazione = ?, " + "importo = ?, " + "idMittente = ?, " + "idDestinatario = ?, " + "metodo_pagamento = ? " + "WHERE id = ?";


        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, transazione.getId());
            st.setFloat(2, transazione.getImporto());
            st.setTimestamp(3, transazione.getDataTransazione());
            st.setString(4, transazione.getMetodoPagamento());
            st.setString(5, transazione.getMittente().getUsername());
            st.setString(6, transazione.getDestinatario().getUsername());
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(TransazionePagamento transazione) {
        String query = "DELETE FROM transazione_pagamento WHERE id_transazione = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, transazione.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TransazionePagamento> findByForeignKeyLavoratore(String username) {
        return findByForeignKeyClienteoLavoratore(username,"username_cliente");
    }

    @Override
    public List<TransazionePagamento> findByForeignKeyCliente(String username) {
        return findByForeignKeyClienteoLavoratore(username,"username_cliente");
    }

    public List<TransazionePagamento> findByForeignKeyClienteoLavoratore(String username, String profilo) {
        List<TransazionePagamento> transazioni = new LinkedList<>();
        String query = "SELECT * FROM transazione_pagamento WHERE "+profilo+" = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                TransazionePagamento transazione = new TransazionePagamento();
                transazione.setId(rs.getLong("id_transazione"));
                transazione.setImporto(rs.getFloat("importo"));
                transazione.setDataTransazione(rs.getTimestamp("data"));
                transazione.setMetodoPagamento(rs.getString("metodo_di_pagamento"));
                transazione.setMittente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username_cliente")));
                transazione.setDestinatario(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username_lavoratore")));
                transazioni.add(transazione);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transazioni;
    }




}
