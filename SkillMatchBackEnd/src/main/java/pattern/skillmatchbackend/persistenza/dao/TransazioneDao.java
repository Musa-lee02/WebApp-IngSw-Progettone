package pattern.skillmatchbackend.persistenza.dao;

import pattern.skillmatchbackend.model.TransazionePagamento;

import java.util.List;

public interface TransazioneDao {

    public List<Transazione> findAll();
    public Transazione findByPrimaryKey(long id);

    public void saveOrUpdate(Transazione transazione);

    public void delete(Transazione transazione);


}
