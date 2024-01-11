package pattern.skillmatchbackend.persistenza.dao;


import pattern.skillmatchbackend.model.Annuncio;

import pattern.skillmatchbackend.model.Ambito;


import java.util.List;


public interface AnnuncioDao {
    public List<Annuncio> findAll();
    public Annuncio findByPrimaryKey(long id);

    public void saveOrUpdate(Annuncio annuncio);

    public void delete(Annuncio annuncio);

    public List<Annuncio> findByForeignKeyCliente(long id);
    public List<Annuncio> annunciPerMe(String provincia, Ambito ambito);

}
