package pattern.skillmatchbackend.persistenza.dao;

import pattern.skillmatchbackend.model.Messaggio;
import java.util.List;

public interface MessaggioDao {
    public List<Messaggio> findAll();
    public Messaggio findByPrimaryKey(long id);

    public void saveOrUpdate(Messaggio messaggio);

    public void delete(Messaggio messaggio);

    public List<Messaggio> findByForeignKeyChat(long idAnnuncio, long idCliente,long idLavoratore);

}
