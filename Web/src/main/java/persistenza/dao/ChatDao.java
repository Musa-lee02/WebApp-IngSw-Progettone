package persistenza.dao;

import application.model.Chat;
import java.util.List;

public interface ChatDao {

    public List<Chat> findAll();
    public Chat findByPrimaryKey(long id);
    public void saveOrUpdate(Chat chat);
    public void delete(Chat chat);

}
