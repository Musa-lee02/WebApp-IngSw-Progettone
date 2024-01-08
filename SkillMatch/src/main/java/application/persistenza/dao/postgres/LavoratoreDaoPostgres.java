package application.persistenza.dao.postgres;


import application.model.image.Image;
import application.model.user.Lavoratore;
import application.persistenza.DBManager;
import application.persistenza.dao.LavoratoreDao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class LavoratoreDaoPostgres implements LavoratoreDao {

    Connection conn;

    public LavoratoreDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Lavoratore> findAll() {

        List<Lavoratore> lavoratori = new LinkedList<>();
        String query = "SELECT * FROM LAVORATORE WHERE username = ?";
        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Lavoratore lavoratore = new Lavoratore();
                lavoratore.setUsername(rs.getString("username"));
                lavoratore.setPassword(rs.getString("password"));
                lavoratore.setNome(rs.getString("nome"));
                lavoratore.setCognome(rs.getString("cognome"));
                lavoratore.setEmail(rs.getString("email"));
                lavoratore.setIndirizzo(rs.getString("indirizzo"));
                lavoratore.setNumeroCivico(rs.getString("numero_civico"));
                lavoratore.setCitta(rs.getString("citta"));
                lavoratore.setProvinciaServizio(rs.getString("provinciaDiServizio"));
                lavoratore.setCap(rs.getString("cap"));
                lavoratore.setRuolo(rs.getBoolean("ruolo"));
                lavoratore.setRecensioni(DBManager.getInstance().getRecensioneDao().findByPrimaryKeyLavoratore(lavoratore.getUsername()));
                lavoratore.setTransazioni(DBManager.getInstance().getTransazioneDao().findByPrimaryKeyLavoratore(lavoratore.getUsername()));
                lavoratore.setAnnunciPeeMe(DBManager.getInstance().getAnnuncioDao().annunciPerMe(lavoratore.getProvinciaServizio(),null));
                lavoratore.setImage(new Image(rs.getString("immagine")));
                lavoratore.setProposte(DBManager.getInstance().getPropostaDao().findByLavoratore(lavoratore.getUsername()));
                lavoratori.add(lavoratore);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lavoratori;
    }

    @Override
    public Lavoratore findByPrimaryKey(String username) {
        Lavoratore lavoratore = null;
        String query = "SELECT * FROM LAVORATORE WHERE username = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                lavoratore = new Lavoratore();
                lavoratore.setUsername(rs.getString("username"));
                lavoratore.setPassword(rs.getString("password"));
                lavoratore.setNome(rs.getString("nome"));
                lavoratore.setCognome(rs.getString("cognome"));
                lavoratore.setEmail(rs.getString("email"));
                lavoratore.setIndirizzo(rs.getString("indirizzo"));
                lavoratore.setNumeroCivico(rs.getString("numero_civico"));
                lavoratore.setCitta(rs.getString("citta"));
                lavoratore.setProvinciaServizio(rs.getString("provinciaDiServizio"));
                lavoratore.setCap(rs.getString("cap"));
                lavoratore.setRuolo(rs.getBoolean("ruolo"));
                lavoratore.setRecensioni(DBManager.getInstance().getRecensioneDao().findByPrimaryKeyLavoratore(lavoratore.getUsername()));
                lavoratore.setTransazioni(DBManager.getInstance().getTransazioneDao().findByPrimaryKeyLavoratore(lavoratore.getUsername()));
                lavoratore.setAnnunciPeeMe(DBManager.getInstance().getAnnuncioDao().annunciPerMe(lavoratore.getProvinciaServizio(),null));
                lavoratore.setImage(new Image(rs.getString("immagine")));
                lavoratore.setProposte(DBManager.getInstance().getPropostaDao().findByLavoratore(lavoratore.getUsername()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lavoratore;
    }

    @Override
    public void saveOrUpdate(Lavoratore lavoratore) {

        String query = "INSERT INTO LAVORATORE VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

        if (findByPrimaryKey(lavoratore.getUsername()) != null)
            query  = "UPDATE LAVORATORE SET password = ?, " + "nome = ?, " + "cognome = ?, " + "email = ?, " + "indirizzo = ?, " + "numero_civico = ?, " + "citta = ?, " + "cap = ?, " + "ruolo = ? " + "WHERE username = ?";

        try {

            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, lavoratore.getUsername());
            st.setString(2, lavoratore.getPassword());
            st.setString(3, lavoratore.getNome());
            st.setString(4, lavoratore.getCognome());
            st.setString(5, lavoratore.getEmail());
            st.setString(6, lavoratore.getIndirizzo());
            st.setString(7, lavoratore.getNumeroCivico());
            st.setString(8, lavoratore.getCitta());
            st.setString(9, lavoratore.getCap());
            st.setBoolean(10, lavoratore.isRuolo());
            st.setString(11, lavoratore.getProvinciaServizio());
            st.setString(12, lavoratore.getImage().getPath());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Lavoratore lavoratore) {
        String query = "DELETE FROM LAVORATORE WHERE username = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, lavoratore.getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
