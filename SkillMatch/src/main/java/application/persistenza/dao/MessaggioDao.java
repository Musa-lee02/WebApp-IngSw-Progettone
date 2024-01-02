package application.persistenza.dao;

import application.model.Messaggio;

import java.util.List;

public interface MessaggioDao {
    public List<Messaggio> findAll();
    public Messaggio findByPrimaryKey(long id);

    public void saveOrUpdate(Messaggio messaggio);

    public void delete(Messaggio messaggio);

}
