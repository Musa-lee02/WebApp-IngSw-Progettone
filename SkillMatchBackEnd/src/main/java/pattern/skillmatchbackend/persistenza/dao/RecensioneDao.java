package pattern.skillmatchbackend.persistenza.dao;

import pattern.skillmatchbackend.model.Recensione;


import java.util.LinkedList;
import java.util.List;

public interface RecensioneDao {
    public List<Recensione> findAll();
    public Recensione findByPrimaryKey(long id);

    public void saveOrUpdate(Recensione recensione);

    public void delete(Recensione recensione);

    public List<Recensione>  findByForeignKeyLavoratore(long id);
    public List<Recensione>  findByForeignKeyCliente(long id);


}
