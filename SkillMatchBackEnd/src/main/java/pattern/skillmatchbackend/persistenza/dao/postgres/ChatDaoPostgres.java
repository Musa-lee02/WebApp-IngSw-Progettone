package pattern.skillmatchbackend.persistenza.dao.postgres;

import pattern.skillmatchbackend.model.Chat;
import pattern.skillmatchbackend.persistenza.DBManager;
import pattern.skillmatchbackend.persistenza.dao.ChatDao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ChatDaoPostgres implements ChatDao {

    Connection conn;

    public ChatDaoPostgres(Connection conn) {
        this.conn = conn;
    }


    @Override
    public List<Chat> findAll() {
        List<Chat> chats = new LinkedList<>();
        String query = "SELECT * FROM chat";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Chat chat = new Chat();
                chat.setAnnuncio(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("id_annuncio")));
                chat.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username_cliente")));
                chat.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username_lavoratore")));
                chats.add(chat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chats;
    }

    @Override
    public Chat findByPrimaryKey(long idAnnuncio,String username_cliente,String username_lavoratore) {
        Chat chat = null;
        String query = "SELECT * FROM chat id_annuncio = ? and username_cliente = ? and username_lavoratore = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, idAnnuncio);
            st.setString(2, username_cliente);
            st.setString(3,username_lavoratore);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                chat.setAnnuncio(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("id_annuncio")));
                chat.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username_cliente")));
                chat.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username_lavoratore")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chat;
    }

    @Override
    public void saveOrUpdate(Chat chat) {
        String query = "INSERT INTO chat VALUES (?, ?, ?)";

        if (findByPrimaryKey(chat.getAnnuncio().getId(), chat.getCliente().getUsername(),chat.getLavoratore().getUsername()) != null)
            query = "UPDATE chat SET  id_annuncio = ?, username_cliente = ?, username_lavoratore = ? WHERE id_annuncio = ?, username_cliente = ?, username_lavoratore = ?";

        try {
            PreparedStatement st = conn.prepareStatement(query);

            st.setLong(1, chat.getAnnuncio().getId());
            st.setString(3, chat.getCliente().getUsername());
            st.setString(3, chat.getLavoratore().getUsername());

            if(query.startsWith("UPDATE")) {

                st.setLong(4, chat.getAnnuncio().getId());
                st.setString(5, chat.getCliente().getUsername());
                st.setString(6, chat.getLavoratore().getUsername());

            }

            st.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Chat chat) {
        String query = "DELETE FROM chat WHERE id_annuncio = ? and id_cliente = ? and id_lavoratore = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, chat.getAnnuncio().getId());
            st.setString(2,chat.getCliente().getUsername());
            st.setString(3,chat.getLavoratore().getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Chat> findByForeignKeyCliente(String username) {
        return findByForeignKeyClienteOLavoratore(username,"username_cliente");
    }

    @Override
    public List<Chat> findByForeignKeyLavoratore(String username) {
        return findByForeignKeyClienteOLavoratore(username,"username_lavoratore");
    }

    @Override
    public List<Chat> findByForeignKeyAnnuncio(long id) {

        List<Chat> chats = new LinkedList<>();

        String query = "SELECT * FROM chat id_annuncio = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Chat chat = new Chat();
                chat.setAnnuncio(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("id_annuncio")));
                chat.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username_cliente")));
                chat.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username_lavoratore")));
                chats.add(chat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chats;

    }

    private List<Chat> findByForeignKeyClienteOLavoratore(String username,String cosa) {
        List<Chat> chats = new LinkedList<>();

        String query = "SELECT * FROM chat WHERE "+cosa+" = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Chat chat = new Chat();
                chat.setAnnuncio(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("id_annuncio")));
                chat.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getString("username_cliente")));
                chat.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getString("username_lavoratore")));
                chats.add(chat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chats;
    }


}

