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
                lavoratore.setProvinciaLavoro(rs.getString("provincia_lavoro"));
                lavoratore.setNotifica_email(rs.getBoolean("notifica_email"));
                lavoratore.setPunteggio(rs.getInt("punteggio"));
                lavoratore.setProposte(DBManager.getInstance().getPropostaDao().findByForeignKeyLavoratore(lavoratore.getUsername()));
                lavoratore.setChats(DBManager.getInstance().getChatDao().findByForeignKeyLavoratore(lavoratore.getUsername()));
                lavoratore.setRecensioni(DBManager.getInstance().getRecensioneDao().findByForeignKeyLavoratore(lavoratore.getUsername()));
                lavoratore.setAnnunciDisponibili(DBManager.getInstance().getAnnuncioDao().annunciPerMe(lavoratore.getProvincia(),lavoratore.getUsername()));
                lavoratore.setAmbiti(DBManager.getInstance().getAmbitoDao().findByLavoratore(lavoratore.getUsername()));
                lavoratore.setTransazionePagamento(DBManager.getInstance().getTransazionePagamentoDao().findByForeignKeyLavoratore(lavoratore.getUsername()));
                lavoratore.setNotifiche(DBManager.getInstance().getNotificaDao().findByForeignKeyLavoratore(lavoratore.getUsername()));
                lavoratori.add(lavoratore);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lavoratori;
    }

    @Override
    public List<Lavoratore> findAllLazy() {

        List<Lavoratore> lavoratori = new LinkedList<>();
        String query = "SELECT * FROM lavoratore";
        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Lavoratore lavoratore = new LavoratoreProxy(DBManager.getInstance().getUtenteDao().findByPrimaryKey(rs.getString("username")),conn);
                lavoratore.setProvinciaLavoro(rs.getString("provincia_lavoro"));
                lavoratore.setNotifica_email(rs.getBoolean("notifica_email"));
                lavoratore.setPunteggio(rs.getInt("punteggio"));
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
        String query = "SELECT * FROM lavoratore WHERE username = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                lavoratore = new Lavoratore(DBManager.getInstance().getUtenteDao().findByPrimaryKey(rs.getString("username")));
                lavoratore.setProvinciaLavoro(rs.getString("provincia_lavoro"));
                lavoratore.setNotifica_email(rs.getBoolean("notifica_email"));
                lavoratore.setPunteggio(rs.getInt("punteggio"));
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
            query  = "UPDATE lavoratore SET username = ? , provincia_lavoro = ?, notifica_email = ? , punteggio = ? WHERE username = ?";

        try {

            DBManager.getInstance().getUtenteDao().saveOrUpdate(lavoratore);
            PreparedStatement st = conn.prepareStatement(query);



            st.setString(1,lavoratore.getUsername());
            st.setString(2,lavoratore.getProvinciaLavoro());
            st.setBoolean(3,lavoratore.isNotifica_email());
            st.setInt(4,lavoratore.getPunteggio());

            if(query.startsWith("UPDATE"))
                st.setString(5, lavoratore.getUsername());



            st.executeUpdate();

            if(query.startsWith("INSERT")) {

                for (Ambito ambito : lavoratore.getAmbiti()) {

                    if (query.startsWith("UPDATE"))
                        query = "UPDATE competente set username_lavoratore = ? , id_ambito = ? WHERE username_lavoratore = ? , id_ambito = ?";
                    else
                        query = "INSERT INTO competente VALUES (?,?)";

                    DBManager.getInstance().getUtenteDao().saveOrUpdate(lavoratore);
                    st = conn.prepareStatement(query);
                    st.setString(1, lavoratore.getUsername());
                    st.setLong(2, ambito.getId());

                    if (query.startsWith("UPDATE")) {
                        st.setString(3, lavoratore.getUsername());
                        st.setLong(4, ambito.getId());
                    }
                    st.executeUpdate();


                }
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
    public boolean isUsernameTaken(String username){
        return DBManager.getInstance().getUtenteDao().isUsernameTaken(username);

    }

    @Override
    public boolean isEmailTaken(String email){
        return DBManager.getInstance().getUtenteDao().isEmailTaken(email);

    }
}
