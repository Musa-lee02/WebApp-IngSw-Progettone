package pattern.skillmatchbackend.persistenza.dao.postgres;

import pattern.skillmatchbackend.model.TransazionePagamento;
import pattern.skillmatchbackend.persistenza.DBManager;

import pattern.skillmatchbackend.persistenza.dao.TransazionePagamentoDao;
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
        String query = "SELECT * FROM TRANSAZIONE WHERE id = ?";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                TransazionePagamento transazione = new TransazionePagamento;
                transazione.setIdTransazione(rs.getLong("id"));
                transazione.setDataTransazione(rs.getDate("data_transazione"));
                transazione.setImporto(rs.getDouble("importo"));
                transazione.setMittente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username")));
                transazione.setDestinatario(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username")));
                transazione.setMetodoPagamento(rs.getString("metodo_pagamento"));
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
        String query = "SELECT * FROM TRANSAZIONE WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                transazione = new TransazionePagamento();
                transazione.setIdTransazione(rs.getLong("id"));
                transazione.setDataTransazione(rs.getDate("data_transazione"));
                transazione.setImporto(rs.getDouble("importo"));
                transazione.setMittente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username")));
                transazione.setDestinatario(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username")));
                transazione.setMetodoPagamento(rs.getString("metodo_pagamento"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transazione;
    }

    @Override
    public void saveOrUpdate(TransazionePagamento transazione) {

        String query = "INSERT INTO TRANSAZIONE VALUES (?, ?, ?, ?, ?, ?)";

        if (findByPrimaryKey(transazione.getIdTransazione()) != null)
            query = "UPDATE TRANSAZIONE SET data_transazione = ?, " + "importo = ?, " + "idMittente = ?, " + "idDestinatario = ?, " + "metodo_pagamento = ? " + "WHERE id = ?";

        try {
                PreparedStatement st = conn.prepareStatement(query);

                st.setLong(1, transazione.getIdTransazione());
                st.setDate(2, transazione.getDataTransazione());
                st.setDouble(3, transazione.getImporto());
                st.setString(4, transazione.getMittente().getUsername());
                st.setString(5, transazione.getDestinatario().getUsername());
                st.setString(6, transazione.getMetodoPagamento());

                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    @Override
    public void delete(TransazionePagamento transazione) {
        String query = "DELETE FROM TRANSAZIONE WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, transazione.getIdTransazione());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TransazionePagamento> findByPrimaryKeyLavoratore(String username) {

        List<TransazionePagamento> transazioni = new LinkedList<>();
        String query = "SELECT * FROM TRANSAZIONE WHERE idLavoratore = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                TransazionePagamento transazione = new TransazionePagamento();
                transazione.setIdTransazione(rs.getLong("id"));
                transazione.setDataTransazione(rs.getDate("data_transazione"));
                transazione.setImporto(rs.getDouble("importo"));
                transazione.setMittente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username")));
                transazione.setDestinatario(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username")));
                transazione.setMetodoPagamento(rs.getString("metodo_pagamento"));
                transazioni.add(transazione);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transazioni;

    }

    @Override
    public List<TransazionePagamento> findByPrimaryKeyCliente(String username) {
        List<TransazionePagamento> transazioni = new LinkedList<>();
        String query = "SELECT * FROM TRANSAZIONE WHERE idCliente = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                TransazionePagamento transazione = new TransazionePagamento();
                transazione.setIdTransazione(rs.getLong("id"));
                transazione.setDataTransazione(rs.getDate("data_transazione"));
                transazione.setImporto(rs.getDouble("importo"));
                transazione.setMittente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username")));
                transazione.setDestinatario(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username")));
                transazione.setMetodoPagamento(rs.getString("metodo_pagamento"));
                transazioni.add(transazione);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transazioni;
    }


}
