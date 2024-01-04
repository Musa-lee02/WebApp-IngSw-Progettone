package pattern.skillmatchbackend.persistenza.dao;

import pattern.skillmatchbackend.model.TransazionePagamento;

import java.util.List;

public interface TransazionePagamentoDao {

    public List<TransazionePagamentoDao> findAll();

    public TransazionePagamentoDao findByPrimaryKey(Long id);

    public void save(TransazionePagamentoDao transazionePagamento);

    public List<TransazionePagamento> findByDestinatario(Long idUtente);

    public List<TransazionePagamento> findByMittente(Long idUtente);

    public List<TransazionePagamento> findByMetodoPagamento(String MetodoPagamento);

    public List<TransazionePagamento> findByImporto(Double importo);

    public List<TransazionePagamento> findByDataTransazione(String dataTransazione);
}
