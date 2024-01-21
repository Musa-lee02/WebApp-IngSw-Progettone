package pattern.skillmatchbackend.persistenza.dao;

import pattern.skillmatchbackend.model.Chat;

import java.util.List;

public interface ChatDao {

    public List<Chat> findAll();

    public Chat findByPrimaryKey(long idAnnuncio,String username_cliente,String username_lavoratore);

    public void saveOrUpdate(Chat chat);

    public void delete(Chat chat);

    public List<Chat> findByForeignKeyCliente(String username);
    public List<Chat> findByForeignKeyLavoratore(String username);
    public List<Chat> findByForeignKeyAnnuncio(long id);

}
