package application.persistenza.dao;

import application.model.Annuncio;


import java.util.List;


public interface AnnuncioDao {
    public List<Annuncio> findAll();
    public Annuncio findByPrimaryKey(long id);

    public void saveOrUpdate(Annuncio annuncio);

    public void delete(Annuncio annuncio);

}
