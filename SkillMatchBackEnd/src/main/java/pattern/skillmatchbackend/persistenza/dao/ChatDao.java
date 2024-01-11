package pattern.skillmatchbackend.persistenza.dao;

import pattern.skillmatchbackend.model.Chat;

import java.util.List;

public interface ChatDao {

    public List<Chat> findAll();

    public Chat findByPrimaryKey(long idAnnuncio, long idCliente, long idLavoratore);

    public void saveOrUpdate(Chat chat);

    public void delete(Chat chat);

    //public Chat findByForeignKeyCliente(long id);
    //public Chat findByForeignKeyLavoratore(long id);

}
