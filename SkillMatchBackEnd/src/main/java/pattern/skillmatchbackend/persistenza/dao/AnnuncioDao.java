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
    public List<Annuncio> findByLavoratore(String username);
    public List<Annuncio> getAnnunciWithChat(String username);
    public List<Annuncio> getAnnunciByAmbitoAndProvincia(String nome, String provincia);
    public List<Annuncio> getAnnunciFinalizzati(String username);
    public List<Annuncio> getAnnunciByAmbito(String ambito);
    public List<Annuncio>getAnnunciByProvincia(String provincia);
}
