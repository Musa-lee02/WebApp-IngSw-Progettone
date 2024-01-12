package persistenza.dao.postgres;

import persistenza.DBManager;
import persistenza.dao.MessageDao;
import application.model.Message;
import application.model.contenuto.Contenuto;
import application.model.user.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MessageDaoPostgres implements MessageDao {

    Connection conn;

    public MessageDaoPostgres(Connection conn) {
        this.conn = conn;
    }



    @Override
    public List<Message> findAll() {return null;}

    @Override
    public Message findByPrimaryKey(long id) {
        return null;
    }

    @Override
    public void saveOrUpdate(Message message) {

    }

    @Override
    public void sort() {

    }

    @Override
    public List<Message> findByIdChat(long id) {
        /*List<Message> messages = new LinkedList<>();
        String query = "select * from MESSAGE where idChat = ?";

        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                long idMessage = rs.getLong("idMessage");
                //Contenuto contenuto = new Contenuto(rs.getString("contenuto")).getContenuto();
                Date date;
                String emailSender = rs.getString("emailSender");
                String emailRecepient = rs.getString("emailRecepient");
                boolean who = true;

                //se true vuol dire che il mittente è lavoratore
                if(rs.getBoolean("who")){
                    String T = emailSender;
                    emailSender = emailRecepient;
                    emailRecepient = emailSender;
                }


                User sender = DBManager.getInstance().getCustomerDao().findByPrimaryKey(emailSender);
                User recipient = DBManager.getInstance().getCustomerDao().findByPrimaryKey(emailRecepient);



                        Message message; = new Message(id,contenuto,date,sender,recipient,who);
                messages.add(message);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return messages;*/ return null;
    }
}
