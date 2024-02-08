package pattern.skillmatchbackend.persistenza.dao.postgres;

import pattern.skillmatchbackend.model.Annuncio;
import pattern.skillmatchbackend.model.Proposta;

import pattern.skillmatchbackend.persistenza.DBManager;
import pattern.skillmatchbackend.persistenza.dao.PropostaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PropostaDaoPostgres implements PropostaDao {

    Connection conn;

    public PropostaDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Proposta> findAll() {
        List<Proposta> proposte = new LinkedList<>();
        String query = "SELECT * FROM proposta";

        try {
            Proposta proposta;
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                proposta = new Proposta();
                proposta.setAnnuncioRelativo(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("id_annuncio")));
                proposta.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username_lavoratore")));
                proposta.setDataLavoro(rs.getDate("data_lavoro"));
                proposta.setDescrizione(rs.getString("descrizione"));
                proposta.setStato(rs.getString("stato"));
                proposta.setStatoLavoro(rs.getString("stato_lavoro"));
                proposta.setPrezzoLavoro(rs.getFloat("prezzo_lavoro"));
                proposte.add(proposta);


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proposte;
    }

    @Override
    public Proposta findByPrimaryKey(long idAnnuncio, String username) {
        Proposta proposta = null;
        String query = "SELECT * FROM proposta WHERE id_annuncio = ? and username_lavoratore = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, idAnnuncio);
            st.setString(2,username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                proposta = new Proposta();
                proposta.setAnnuncioRelativo(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("id_annuncio")));
                proposta.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username_lavoratore")));
                proposta.setDataLavoro(rs.getDate("data_lavoro"));
                proposta.setDescrizione(rs.getString("descrizione"));
                proposta.setStato(rs.getString("stato"));
                proposta.setStatoLavoro(rs.getString("stato_lavoro"));
                proposta.setPrezzoLavoro(rs.getFloat("prezzo_lavoro"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proposta;
    }

    @Override
    public boolean saveOrUpdate(Proposta proposta) {

        String query = "INSERT INTO proposta VALUES (?, ?, ?, ?, ?, ?,?)";

        if (findByPrimaryKey(proposta.getAnnuncioRelativo().getId(),proposta.getLavoratore().getUsername()) != null)
            query = "UPDATE proposta SET  id_annuncio = ?, username_lavoratore = ?, data_lavoro = ?, descrizione = ?, stato = ?, stato_lavoro = ?, prezzo_lavoro = ? WHERE id_annuncio = ? and username_lavoratore = ?";

        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, proposta.getAnnuncioRelativo().getId());
            st.setString(2, proposta.getLavoratore().getUsername());
            st.setDate(3, proposta.getDataLavoro());
            st.setString(4, proposta.getDescrizione());
            st.setString(5, proposta.getStato());
            st.setString(6, proposta.getStatoLavoro());
            st.setFloat(7, proposta.getPrezzoLavoro());

            if (query.startsWith("UPDATE")) {
                st.setLong(8, proposta.getAnnuncioRelativo().getId());
                st.setString(9, proposta.getLavoratore().getUsername());
            }

            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public void delete(Proposta proposta) {
        String query = "DELETE FROM proposta WHERE id_annuncio = ? and username_lavoratore = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, proposta.getAnnuncioRelativo().getId());
            st.setString(2,proposta.getLavoratore().getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Proposta> findByForeignKeyLavoratore(String username) {

        List<Proposta> proposte = new LinkedList<>();
        String query = "SELECT * FROM proposta WHERE username_lavoratore = ?";

        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Proposta proposta = new Proposta();
                proposta.setAnnuncioRelativo(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("id_annuncio")));
                proposta.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username_lavoratore")));
                proposta.setDataLavoro(rs.getDate("data_lavoro"));
                proposta.setStato(rs.getString("stato"));
                proposta.setStatoLavoro(rs.getString("stato_lavoro"));
                proposta.setDescrizione(rs.getString("descrizione"));
                proposta.setStato(rs.getString("stato"));
                proposta.setPrezzoLavoro(rs.getFloat("prezzo_lavoro"));
                proposte.add(proposta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proposte;

    }

    @Override
    public List<Proposta> findByForeignKeyAnnuncio(Long id) {
        List<Proposta> proposte = new LinkedList<>();
        String query = "SELECT * FROM proposta WHERE id_annuncio = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Proposta proposta = new Proposta();
                proposta.setAnnuncioRelativo(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("id_annuncio")));
                proposta.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username_lavoratore")));
                proposta.setDataLavoro(rs.getDate("data_lavoro"));
                proposta.setStato(rs.getString("stato"));
                proposta.setStatoLavoro(rs.getString("stato_lavoro"));
                proposta.setDescrizione(rs.getString("descrizione"));
                proposta.setStato(rs.getString("stato"));
                proposta.setPrezzoLavoro(rs.getFloat("prezzo_lavoro"));

                proposte.add(proposta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proposte;

    }

    @Override
    public Proposta findByChat(Long idAnnuncio, String usernameCliente, String usernameLavoratore) {
        Proposta proposta = null;
        String query = "SELECT * FROM proposta,annuncio WHERE proposta.id_annuncio = ? AND  username_lavoratore = ? AND proposta.id_annuncio = annuncio.id_annuncio AND annuncio.username_cliente = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, idAnnuncio);
            st.setString(2,usernameLavoratore);
            st.setString(3,usernameCliente);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                proposta = new Proposta();
                proposta.setAnnuncioRelativo(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("id_annuncio")));
                proposta.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username_lavoratore")));
                proposta.setDataLavoro(rs.getDate("data_lavoro"));
                proposta.setStato(rs.getString("stato"));
                proposta.setStatoLavoro(rs.getString("stato_lavoro"));
                proposta.setDescrizione(rs.getString("descrizione"));
                proposta.setStato(rs.getString("stato"));
                proposta.setPrezzoLavoro(rs.getFloat("prezzo_lavoro"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proposta;

    }

    @Override
    public Proposta findByAnnuncioFinalizzato(Long idAnnuncio){
        Proposta proposta = null;
        String query = "SELECT * FROM proposta WHERE proposta.id_annuncio = ? AND proposta.stato = 'accettata'";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, idAnnuncio);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                proposta = new Proposta();
                proposta.setAnnuncioRelativo(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("id_annuncio")));
                proposta.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username_lavoratore")));
                proposta.setDataLavoro(rs.getDate("data_lavoro"));
                proposta.setStato(rs.getString("stato"));
                proposta.setStatoLavoro(rs.getString("stato_lavoro"));
                proposta.setDescrizione(rs.getString("descrizione"));
                proposta.setStato(rs.getString("stato"));
                proposta.setPrezzoLavoro(rs.getFloat("prezzo_lavoro"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proposta;
    }


}


