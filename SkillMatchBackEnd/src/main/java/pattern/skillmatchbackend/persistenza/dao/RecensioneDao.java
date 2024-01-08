package pattern.skillmatchbackend.persistenza.dao;

import pattern.skillmatchbackend.model.Recensione;


import java.util.LinkedList;
import java.util.List;

public interface RecensioneDao {
    public List<Recensione> findAll();
    public Recensione findByPrimaryKey(long idRecensione);

    public void saveOrUpdate(Recensione recensione);

    public void delete(Recensione recensione);

    public List<Recensione>  findByPrimaryKeyLavoratore(String username);
    public List<Recensione>  findByPrimaryKeyCliente(String username);


}
