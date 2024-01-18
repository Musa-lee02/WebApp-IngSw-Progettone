package persistenza.dao;

import application.model.Message;


import java.util.List;

public interface MessageDao {

    public List<Message> findAll();
    public Message findByPrimaryKey(long id);
    public void saveOrUpdate(Message message);
    public void sort();
    public List<Message> findByIdChat(long id);


}

