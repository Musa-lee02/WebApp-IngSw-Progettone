package persistenza.dao.postgres;

import persistenza.DBManager;
import persistenza.dao.ChatDao;
import application.model.Chat;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ChatDaoPostgres implements ChatDao {

    Connection conn;

    public ChatDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Chat> findAll() {
        return null;
    }

    @Override
    public Chat findByPrimaryKey(long id) {

        Chat chat = null;
        String query = "select * from CHAT where idChat = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                chat.setId(rs.getLong("idChat"));
                chat.setMessages(DBManager.getInstance().getMessageDao().findByIdChat(rs.getLong("idChat")));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return chat;

    }

    @Override
    public void saveOrUpdate(Chat chat) {

    }

    @Override
    public void delete(Chat chat) {

    }
}
