package pattern.skillmatchbackend.persistenza.dao.postgres;

import pattern.skillmatchbackend.model.Image;
import pattern.skillmatchbackend.model.Utente;
import pattern.skillmatchbackend.persistenza.dao.UtenteDao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UtenteDaoPostgres implements UtenteDao {


    Connection conn;

    public UtenteDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Utente> findAll() {
        List<Utente> utenti= new LinkedList<>();
        String query = "SELECT * FROM utente";
        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Utente utente = new Utente();
                utente.setUsername(rs.getString("username"));
                utente.setPassword(rs.getString("password"));
                utente.setEmail(rs.getString("email"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setVia(rs.getString("via"));
                utente.setNumeroCivico(rs.getInt("numero_civico"));
                utente.setCap(rs.getString("cap"));
                utente.setProvincia(rs.getString("provincia"));
                utente.setCitta(rs.getString("citta"));
                utente.setProvinciaLavoro(rs.getString("provincia_lavoro"));
                utente.setImgProfilo(new Image(rs.getString("img_profilo")));
                utente.setRegistrato(rs.getBoolean("registrato"));
                utente.setDataRegistrazione(rs.getDate("data_Registrazione"));
                utenti.add(utente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utenti;

    }

    @Override
    public Utente findByPrimaryKey(String username) {
        Utente utente = null;
        String query = "SELECT * FROM utente WHERE username = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                utente.setUsername(rs.getString("username"));
                utente.setPassword(rs.getString("password"));
                utente.setEmail(rs.getString("email"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setVia(rs.getString("via"));
                utente.setNumeroCivico(rs.getInt("numero_civico"));
                utente.setCap(rs.getString("cap"));
                utente.setProvincia(rs.getString("provincia"));
                utente.setCitta(rs.getString("citta"));
                utente.setProvinciaLavoro(rs.getString("provincia_lavoro"));
                utente.setImgProfilo(new Image(rs.getString("img_profilo")));
                utente.setRegistrato(rs.getBoolean("registrato"));
                utente.setDataRegistrazione(rs.getDate("data_Registrazione"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utente;
    }

    @Override
    public void saveOrUpdate(Utente utente) {

        String query = "INSERT INTO Utenti "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        if (findByPrimaryKey(utente.getUsername()) != null)
            query  = "UPDATE Utenti SET "
                    + "password = ?, email = ?, nome = ?, cognome = ?, via = ?, numero_civico = ?, cap = ?, provincia = ?, "
                    + "citta = ?, provincia_lavoro = ?, img_profilo = ?, registrato = ?, data_registrazione = ? "
                    + "WHERE username = ?";

        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, utente.getUsername());
            st.setString(2, utente.getPassword());
            st.setString(3, utente.getEmail());
            st.setString(4, utente.getNome());
            st.setString(5, utente.getCognome());
            st.setString(6, utente.getVia());
            st.setInt(7, utente.getNumeroCivico());
            st.setString(8, utente.getCap());
            st.setString(9, utente.getProvincia());
            st.setString(10, utente.getCitta());
            st.setString(11, utente.getProvinciaLavoro());
            st.setString(12, utente.getImgProfilo().getPath());
            st.setBoolean(13, utente.isRegistrato());
            st.setDate(14, utente.getDataRegistrazione());
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void delete(Utente utente) {
        String query = "DELETE FROM utente WHERE username = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, utente.getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


