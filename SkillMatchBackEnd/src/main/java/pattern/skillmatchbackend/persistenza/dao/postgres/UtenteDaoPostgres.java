package pattern.skillmatchbackend.persistenza.dao.postgres;

import pattern.skillmatchbackend.model.Image;
import pattern.skillmatchbackend.model.Utente;
import pattern.skillmatchbackend.persistenza.dao.UtenteDao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static pattern.skillmatchbackend.config.PasswordCrypt.encode;

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
                utente.setProvincia(rs.getString("provincia"));
                utente.setImgProfilo(new Image(rs.getString("img_profilo")));
                utente.setRegistrato(rs.getBoolean("registrato"));
                utente.setDataRegistrazione(rs.getDate("data_registrazione"));
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
                utente = new Utente();
                utente.setUsername(rs.getString("username"));
                utente.setPassword(rs.getString("password"));
                utente.setEmail(rs.getString("email"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setProvincia(rs.getString("provincia"));
                utente.setImgProfilo(new Image(rs.getString("img_profilo")));
                utente.setRegistrato(rs.getBoolean("registrato"));
                utente.setDataRegistrazione(rs.getDate("data_registrazione"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utente;
    }

    @Override
    public void saveOrUpdate(Utente utente) {

        String query = "INSERT INTO utente "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        if (findByPrimaryKey(utente.getUsername()) != null)
            query  = "UPDATE utente SET "
                    + "username = ?, password = ?, email = ?, nome = ?, cognome = ?, provincia = ?, "
                    + "img_profilo = ?, registrato = ?, data_registrazione = ? "
                    + "WHERE username = ?";


        try {
            System.out.println("utente registrato: " + utente.isRegistrato());

            String passC = utente.getPassword();
            utente.setPassword(encode(passC));

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, utente.getUsername());
            st.setString(2, utente.getPassword());
            st.setString(3, utente.getEmail());
            st.setString(4, utente.getNome());
            st.setString(5, utente.getCognome());
            st.setString(6, utente.getProvincia());
            st.setString(7, utente.getImgProfilo().getPath());
            st.setBoolean(8, utente.isRegistrato());
            st.setDate(9, utente.getDataRegistrazione());


            if(query.startsWith("UPDATE"))
                st.setString(10, utente.getUsername());



            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isUsernameTaken(String username){
        String query = "SELECT * FROM utente WHERE  username = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if(rs.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isEmailTaken(String email){
        String query = "SELECT * FROM utente WHERE  email = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if(rs.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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


