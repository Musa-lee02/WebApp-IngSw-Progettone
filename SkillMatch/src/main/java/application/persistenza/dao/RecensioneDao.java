package application.persistenza.dao;

import application.model.Recensione;

import java.util.LinkedList;
import java.util.List;

public interface RecensioneDao {
    public List<Recensione> findAll();
    public Recensione findByPrimaryKey(long idRecensione);

    public void saveOrUpdate(Recensione recensione);

    public void delete(Recensione recensione);


}
