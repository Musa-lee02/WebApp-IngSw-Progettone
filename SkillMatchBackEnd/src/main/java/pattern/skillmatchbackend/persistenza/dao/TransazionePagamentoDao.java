package pattern.skillmatchbackend.persistenza.dao;

import pattern.skillmatchbackend.model.TransazionePagamento;
import java.util.List;

public interface TransazionePagamentoDao {

    public List<TransazionePagamento> findAll();
    public TransazionePagamento findByPrimaryKey(long id);

    public void saveOrUpdate(TransazionePagamento transazione);

    public void delete(TransazionePagamento transazione);

    public List<TransazionePagamento> findByForeignKeyLavoratore(long id);
    public List<TransazionePagamento> findByForeignKeyCliente(long id);

}
