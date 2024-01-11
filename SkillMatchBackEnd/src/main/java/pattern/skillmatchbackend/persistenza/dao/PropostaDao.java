package pattern.skillmatchbackend.persistenza.dao;

import pattern.skillmatchbackend.model.Proposta;

import java.util.List;

public interface PropostaDao {

    public List<Proposta> findAll();
    public Proposta findByPrimaryKey(long idAnnuncio, long idLavoratore);

    public void saveOrUpdate(Proposta proposta);

    public void delete(Proposta proposta);

    public List<Proposta> findByLavoratore(String username);
    public Proposta findByForeignKeyAnnuncio(long id);



}
