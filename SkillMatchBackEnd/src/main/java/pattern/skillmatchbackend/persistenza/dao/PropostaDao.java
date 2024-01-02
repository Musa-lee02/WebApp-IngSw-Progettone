package pattern.skillmatchbackend.persistenza.dao;
import pattern.skillmatchbackend.model.Proposta;

import java.util.List;

public interface PropostaDao {

    public List<Proposta> findAll();
    public Proposta findByPrimaryKey(long id);

    public void saveOrUpdate(Proposta proposta);

    public void delete(Proposta proposta);


}
