package pattern.skillmatchbackend.persistenza.dao;

import pattern.skillmatchbackend.model.Proposta;

import java.util.List;

public interface PropostaDao {

    public List<Proposta> findAll();
    public Proposta findByPrimaryKey(long idAnnuncio, String username);

    public void saveOrUpdate(Proposta proposta);

    public void delete(Proposta proposta);

    public List<Proposta> findByForeignKeyLavoratore(String username);
    public Proposta findByForeignKeyAnnuncio(Long id);

    public Proposta findByAnnuncioEUsernameLavoratore(long idAnnuncio,String username);



}
