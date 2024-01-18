package pattern.skillmatchbackend.persistenza.dao.postgres;

import pattern.skillmatchbackend.model.Ambito;
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
                Lavoratore lavoratore = new Lavoratore(DBManager.getInstance().getUtenteDao().findByPrimaryKey(rs.getString("username")));
                lavoratore.setProposte(DBManager.getInstance().getPropostaDao().findByForeignKeyLavoratore(lavoratore.getUsername()));
                lavoratore.setChats(DBManager.getInstance().getChatDao().findByForeignKeyLavoratore(lavoratore.getUsername()));
                lavoratore.setRecensioni(DBManager.getInstance().getRecensioneDao().findByForeignKeyLavoratore(lavoratore.getUsername()));
                lavoratore.setAnnunciDisponibili(DBManager.getInstance().getAnnuncioDao().annunciPerMe(lavoratore.getProvincia(),lavoratore.getUsername()));
                lavoratore.setAmbiti(DBManager.getInstance().getAmbitoDao().findByLavoratore(lavoratore.getUsername()));
                lavoratore.setTransazionePagamento(DBManager.getInstance().getTransazionePagamentoDao().findByForeignKeyLavoratore(lavoratore.getUsername()));
                lavoratore.setNotifiche(DBManager.getInstance().getNotificaDao().findByForeignKeyLavoratore(lavoratore.getUsername()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lavoratori;
    }


    @Override
    public Lavoratore findByPrimaryKey(String username) {

        Lavoratore lavoratore = null;
        String query = "SELECT * FROM lavoratore WHERE username = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                lavoratore = new Lavoratore(DBManager.getInstance().getUtenteDao().findByPrimaryKey(rs.getString("username")));
                lavoratore.setProposte(DBManager.getInstance().getPropostaDao().findByForeignKeyLavoratore(lavoratore.getUsername()));
                lavoratore.setChats(DBManager.getInstance().getChatDao().findByForeignKeyLavoratore(lavoratore.getUsername()));
                lavoratore.setRecensioni(DBManager.getInstance().getRecensioneDao().findByForeignKeyLavoratore(lavoratore.getUsername()));
                lavoratore.setAnnunciDisponibili(DBManager.getInstance().getAnnuncioDao().annunciPerMe(lavoratore.getProvincia(),lavoratore.getUsername()));
                lavoratore.setAmbiti(DBManager.getInstance().getAmbitoDao().findByLavoratore(lavoratore.getUsername()));
                lavoratore.setTransazionePagamento(DBManager.getInstance().getTransazionePagamentoDao().findByForeignKeyLavoratore(lavoratore.getUsername()));
                lavoratore.setNotifiche(DBManager.getInstance().getNotificaDao().findByForeignKeyLavoratore(lavoratore.getUsername()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lavoratore;
    }

    @Override
    public void saveOrUpdate(Lavoratore lavoratore) {

        String query = "INSERT INTO lavoratore VALUES (?,?,?,?)";

        if (findByPrimaryKey(lavoratore.getUsername()) != null)
            query  = "UPDATE lavoratore SET ";

        try {

            DBManager.getInstance().getUtenteDao().saveOrUpdate(lavoratore);
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1,lavoratore.getUsername());
            st.setString(2,lavoratore.getProvinciaLavoro());
            st.setBoolean(3,lavoratore.isNotifica_email());
            st.setInt(4,lavoratore.getPunteggio());
            st.executeUpdate();


            for(Ambito ambito: lavoratore.getAmbiti()) {
                query = "INSERT INTO competente VALUES (?,?)";
                DBManager.getInstance().getUtenteDao().saveOrUpdate(lavoratore);
                st = conn.prepareStatement(query);
                st.setString(1, lavoratore.getUsername());
                st.setLong(2, ambito.getId());
                st.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void delete(Lavoratore lavoratore) {

        DBManager.getInstance().getUtenteDao().delete(lavoratore);

    }

    @Override
    public boolean isEmailTaken(String email){
        String query = "SELECT * FROM lavoratore WHERE  = ?";
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
        String query = "SELECT username FROM lavoratore WHERE username = ?";
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
