package application.persistenza.dao.postgres;

import application.model.Transazione;
import application.persistenza.dao.TransazioneDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TransazioneDaoPostgres implements TransazioneDao {

    Connection conn;

    public TransazioneDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Transazione> findAll() {
        return null;
    }

    @Override
    public Transazione findByPrimaryKey(long id) {
        Transazione transazione = null;
        String query = "SELECT * FROM TRANSAZIONE WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                transazione = new Transazione();
                transazione.setIdTransazione(rs.getLong("id"));
                transazione.setDataTransazione(rs.getDate("data_transazione"));
                transazione.setImporto(rs.getDouble("importo"));
                //transazione.setMittente(new Cliente(rs.getLong("idMittente")));
                //transazione.setDestinatario(new Lavoratore(rs.getLong("idDestinatario")));
                transazione.setMetodoPagamento(rs.getString("metodo_pagamento"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transazione;
    }

    @Override
    public void saveOrUpdate(Transazione transazione) {
        if (findByPrimaryKey(transazione.getIdTransazione()) == null) {
            String insertStr = "INSERT INTO TRANSAZIONE VALUES (?, ?, ?, ?, ?, ?)";

            try {
                PreparedStatement st = conn.prepareStatement(insertStr);

                st.setLong(1, transazione.getIdTransazione());
                st.setDate(2, transazione.getDataTransazione());
                st.setDouble(3, transazione.getImporto());
                //st.setLong(4, transazione.getMittente().getIdCliente());
                //st.setLong(5, transazione.getDestinatario().getIdLavoratore());
                st.setString(6, transazione.getMetodoPagamento());

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String updateStr = "UPDATE TRANSAZIONE SET data_transazione = ?, "
                    + "importo = ?, "
                    + "idMittente = ?, "
                    + "idDestinatario = ?, "
                    + "metodo_pagamento = ? "
                    + "WHERE id = ?";

            try {
                PreparedStatement st = conn.prepareStatement(updateStr);

                st.setDate(1, transazione.getDataTransazione());
                st.setDouble(2, transazione.getImporto());
                //st.setLong(3, transazione.getMittente().getIdCliente());
                //st.setLong(4, transazione.getDestinatario().getIdLavoratore());
                st.setString(5, transazione.getMetodoPagamento());
                st.setLong(6, transazione.getIdTransazione());

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Transazione transazione) {
        String query = "DELETE FROM TRANSAZIONE WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, transazione.getIdTransazione());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
