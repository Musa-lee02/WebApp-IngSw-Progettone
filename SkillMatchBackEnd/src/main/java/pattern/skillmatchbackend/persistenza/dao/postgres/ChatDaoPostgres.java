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
                chat.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getLong("id_clliente")));
                chat.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getLong("id_lavoratore")));
                chat.setMessaggi(DBManager.getInstance().getMessaggioDao().findByForeignKeyChat(chat.getAnnuncio().getId(),chat.getCliente().getId(),chat.getLavoratore().getId()));
                chats.add(chat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chats;
    }

    @Override
    public Chat findByPrimaryKey(long idAnnuncio, long idCliente,long idLavoratore) {
        Chat chat = null;
        String query = "SELECT * FROM chat id_annuncio = ? and id_cliente = ? and id_lavoratore = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, idAnnuncio);
            st.setLong(2, idCliente);
            st.setLong(3,idCliente);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                chat.setAnnuncio(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("id_annuncio")));
                chat.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getLong("id_clliente")));
                chat.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getLong("id_lavoratore")));
                chat.setMessaggi(DBManager.getInstance().getMessaggioDao().findByForeignKeyChat(chat.getAnnuncio().getId(),chat.getCliente().getId(),chat.getLavoratore().getId()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chat;
    }

    @Override
    public void saveOrUpdate(Chat chat) {
        String query = "INSERT INTO chat VALUES (?, ?, ?)";

        if (findByPrimaryKey(chat.getAnnuncio().getId(), chat.getCliente().getId(),chat.getLavoratore().getId()) != null)
            query = "UPDATE chat SET + \"id_annuncio = ?, id_cliente = ?, id_lavoratore = ? \"\n" +
                    "        + \"WHERE id_annuncio = ?, id_cliente = ?, id_lavoratore = ?= ?\";";

        try {
            PreparedStatement st = conn.prepareStatement(query);

            st.setLong(1, chat.getCliente().getId());
            st.setLong(2, chat.getCliente().getId());
            st.setLong(5, chat.getAnnuncio().getId());



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
            st.setLong(2,chat.getCliente().getId());
            st.setLong(3,chat.getLavoratore().getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Chat> findByForeignKeyCliente(long id) {
        return findByForeignKeyAnnunciooClienteOLavoratore(id,"id_cliente");
    }

    @Override
    public List<Chat> findByForeignKeyLavoratore(long id) {
        return findByForeignKeyAnnunciooClienteOLavoratore(id,"id_lavoratore");
    }

    @Override
    public List<Chat> findByForeignKeyAnnuncio(long id) {
        return findByForeignKeyAnnunciooClienteOLavoratore(id,"id_annuncio");
    }

    public List<Chat> findByForeignKeyAnnunciooClienteOLavoratore(long id,String cosa) {
        List<Chat> chats = new LinkedList<>();

        String query = "SELECT * FROM chat "+cosa+" = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Chat chat = new Chat();
                chat.setAnnuncio(DBManager.getInstance().getAnnuncioDao().findByPrimaryKey(rs.getLong("id_annuncio")));
                chat.setCliente(DBManager.getInstance().getClienteDao().findByPrimaryKey(rs.getLong("id_clliente")));
                chat.setLavoratore(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(rs.getLong("id_lavoratore")));
                chat.setMessaggi(DBManager.getInstance().getMessaggioDao().findByForeignKeyChat(chat.getAnnuncio().getId(),chat.getCliente().getId(),chat.getLavoratore().getId()));
                chats.add(chat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chats;
    }


}
