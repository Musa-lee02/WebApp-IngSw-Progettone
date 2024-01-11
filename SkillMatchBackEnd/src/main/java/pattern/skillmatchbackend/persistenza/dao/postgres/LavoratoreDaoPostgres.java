package pattern.skillmatchbackend.persistenza.dao.postgres;

import pattern.skillmatchbackend.model.Image;

import pattern.skillmatchbackend.model.Lavoratore;

import pattern.skillmatchbackend.model.Utente;
import pattern.skillmatchbackend.persistenza.DBManager;

import pattern.skillmatchbackend.persistenza.dao.LavoratoreDao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class LavoratoreDaoPostgres  implements LavoratoreDao  {

    Connection conn;

    public LavoratoreDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Lavoratore> findAll() {

        List<Lavoratore> lavoratori = new LinkedList<>();
        String query = "SELECT * FROM lavoratore";
        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lavoratori;
    }

    @Override
    public Lavoratore findByPrimaryKey(long id) {
        Lavoratore lavoratore = null;
        String query = "SELECT * FROM lavoratore WHERE id_lavoratore = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                lavoratore = new Lavoratore();
                lavoratore.setId(rs.getLong("id_lavoratore"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lavoratore;
    }

    @Override
    public Lavoratore findByPrimaryKey(String username) {
        Lavoratore lavoratore = null;
        String query = "SELECT * FROM lavoratore WHERE username = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1,username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                lavoratore = new Lavoratore();
                lavoratore.setId(rs.getLong("id_lavoratore"));
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

    @Override
    public boolean isEmailTaken(String email){
        String query = "SELECT * FROM LAVORATORE WHERE email = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isUsernameTaken(String username){
        String query = "SELECT * FROM LAVORATORE WHERE username = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }




}
