package pattern.skillmatchbackend.persistenza.dao;

import pattern.skillmatchbackend.model.Chat;

import java.util.List;

public interface ChatDao {

    public List<Chat> findAll();

    public Chat findByPrimaryKey(long idAnnuncio, long idCliente, long idLavoratore);

    public void saveOrUpdate(Chat chat);

    public void delete(Chat chat);

    public List<Chat> findByForeignKeyCliente(long id);
    public List<Chat> findByForeignKeyLavoratore(long id);
    public List<Chat> findByForeignKeyAnnuncio(long id);

}
