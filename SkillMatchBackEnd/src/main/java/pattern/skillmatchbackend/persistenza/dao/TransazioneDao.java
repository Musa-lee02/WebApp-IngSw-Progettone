package pattern.skillmatchbackend.persistenza.dao;

import pattern.skillmatchbackend.model.TransazionePagamento;

import java.util.List;

public interface TransazioneDao {

    public List<TransazionePagamento> findAll();
    public TransazionePagamento findByPrimaryKey(long id);

    public void saveOrUpdate(TransazionePagamento transazione);

    public void delete(TransazionePagamento transazione);


}
