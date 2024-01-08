package application.persistenza.dao.postgres;

import application.model.Annuncio;
import application.persistenza.dao.AnnuncioDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AnnuncioDaoPostgres implements AnnuncioDao {

    Connection conn;

    public AnnuncioDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Annuncio> findAll() {
        return null;
    }

    @Override
    public Annuncio findByPrimaryKey(long id) {
        Annuncio annuncio = null;
        String query = "SELECT * FROM ANNUNCIO WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                annuncio = new Annuncio();
                annuncio.setIdAnnuncio(rs.getLong("id"));
                annuncio.setTitolo(rs.getString("titolo"));
                annuncio.setDescrizione(rs.getString("descrizione"));
                //annuncio.setDataDiScadenza(rs.getDate("data_di_scadenza"));
                annuncio.setProvinciaAnnuncio(rs.getString("provincia_annuncio"));
                //annuncio.setCliente(new Cliente(rs.getLong("idCliente")));
                //annuncio.setImage(new Image(rs.getString("image_path")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return annuncio;
    }

    @Override
    public void saveOrUpdate(Annuncio annuncio) {
        if (findByPrimaryKey(annuncio.getIdAnnuncio()) == null) {
            String insertStr = "INSERT INTO ANNUNCIO VALUES (?, ?, ?, ?, ?, ?, ?)";

            try {
                PreparedStatement st = conn.prepareStatement(insertStr);

                st.setLong(1, annuncio.getIdAnnuncio());
                st.setString(2, annuncio.getTitolo());
                st.setString(3, annuncio.getDescrizione());
                //st.setDate(4, annuncio.getDataDiScadenza());
                st.setString(5, annuncio.getProvinciaAnnuncio());
                //st.setLong(6, annuncio.getCliente().getIdCliente());
                st.setString(7, annuncio.getImage().getPath());

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String updateStr = "UPDATE ANNUNCIO SET titolo = ?, "
                    + "descrizione = ?, "
                    + "data_di_scadenza = ?, "
                    + "provincia_annuncio = ?, "
                    + "idCliente = ?, "
                    + "image_path = ? "
                    + "WHERE id = ?";

            try {
                PreparedStatement st = conn.prepareStatement(updateStr);

                st.setString(1, annuncio.getTitolo());
                st.setString(2, annuncio.getDescrizione());
                //st.setDate(3, annuncio.getDataDiScadenza());
                st.setString(4, annuncio.getProvinciaAnnuncio());
                //st.setLong(5, annuncio.getCliente().getIdCliente());
                st.setString(6, annuncio.getImage().getPath());
                st.setLong(7, annuncio.getIdAnnuncio());

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Annuncio annuncio) {
        String query = "DELETE FROM ANNUNCIO WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, annuncio.getIdAnnuncio());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
