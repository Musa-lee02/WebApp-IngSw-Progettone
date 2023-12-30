package application.persistenza.dao;

import application.model.Transazione;

import java.util.List;

public interface TransazioneDao {

    public List<Transazione> findAll();
    public Transazione findByPrimaryKey(long id);

    public void saveOrUpdate(Transazione transazione);

    public void delete(Transazione transazione);


}
