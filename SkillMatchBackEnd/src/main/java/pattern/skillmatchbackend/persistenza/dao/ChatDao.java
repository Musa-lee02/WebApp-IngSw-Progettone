package pattern.skillmatchbackend.persistenza.dao;

import pattern.skillmatchbackend.model.Chat;

import java.util.List;

public interface ChatDao {

    public List<Chat> findAll();

    public Chat findByPrimaryKey(Long id);

    public void save(Chat chat);


}
