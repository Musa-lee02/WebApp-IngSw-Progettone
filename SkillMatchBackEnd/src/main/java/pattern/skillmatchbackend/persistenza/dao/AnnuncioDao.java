package pattern.skillmatchbackend.persistenza.dao;


import pattern.skillmatchbackend.model.Annuncio;


import java.util.List;


public interface AnnuncioDao {
    public List<Annuncio> findAll();
    public Annuncio findByPrimaryKey(long id);

    public void saveOrUpdate(Annuncio annuncio);

    public void delete(Annuncio annuncio);

    public List<Annuncio> findByForeignKeyCliente(String username);
    public List<Annuncio> annunciPerMe(String provincia, String username);

}
