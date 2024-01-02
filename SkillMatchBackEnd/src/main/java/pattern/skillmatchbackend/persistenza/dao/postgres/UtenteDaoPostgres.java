package pattern.skillmatchbackend.persistenza.dao.postgres;

import application.model.user.Utente;
import application.persistenza.dao.UtenteDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UtenteDaoPostgres implements UtenteDao {

    Connection conn;

    public UtenteDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Utente> findAll() {
        return null;
    }

    @Override
    public Utente findByPrimaryKey(String username) {
        /*Utente utente = null;
        String query = "SELECT * FROM UTENTE WHERE username = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                utente = new Utente();
                utente.setUsername(rs.getString("username"));
                utente.setPassword(rs.getString("password"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setEmail(rs.getString("email"));
                utente.setIndirizzo(rs.getString("indirizzo"));
                utente.setNumeroCivico(rs.getString("numero_civico"));
                utente.setCitta(rs.getString("citta"));
                utente.setCap(rs.getString("cap"));
                utente.setRuolo(rs.getBoolean("ruolo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utente;*/
        return null;
    }

    @Override
    public void saveOrUpdate(Utente utente) {
        if (findByPrimaryKey(utente.getUsername()) == null) {
            String insertStr = "INSERT INTO UTENTE VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try {
                PreparedStatement st = conn.prepareStatement(insertStr);

                st.setString(1, utente.getUsername());
                st.setString(2, utente.getPassword());
                st.setString(3, utente.getNome());
                st.setString(4, utente.getCognome());
                st.setString(5, utente.getEmail());
                st.setString(6, utente.getIndirizzo());
                st.setString(7, utente.getNumeroCivico());
                st.setString(8, utente.getCitta());
                st.setString(9, utente.getCap());
                st.setBoolean(10, utente.isRuolo());

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String updateStr = "UPDATE UTENTE SET password = ?, "
                    + "nome = ?, "
                    + "cognome = ?, "
                    + "email = ?, "
                    + "indirizzo = ?, "
                    + "numero_civico = ?, "
                    + "citta = ?, "
                    + "cap = ?, "
                    + "ruolo = ? "
                    + "WHERE username = ?";

            try {
                PreparedStatement st = conn.prepareStatement(updateStr);

                st.setString(1, utente.getPassword());
                st.setString(2, utente.getNome());
                st.setString(3, utente.getCognome());
                st.setString(4, utente.getEmail());
                st.setString(5, utente.getIndirizzo());
                st.setString(6, utente.getNumeroCivico());
                st.setString(7, utente.getCitta());
                st.setString(8, utente.getCap());
                st.setBoolean(9, utente.isRuolo());
                st.setString(10, utente.getUsername());

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Utente utente) {
        String query = "DELETE FROM UTENTE WHERE username = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, utente.getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
