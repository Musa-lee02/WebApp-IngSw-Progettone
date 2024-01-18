package pattern.skillmatchbackend.persistenza.dao;

import pattern.skillmatchbackend.model.Ambito;
import java.util.List;

public interface AmbitoDao {

    public List<Ambito> findAll();

    public Ambito findByPrimaryKey(long id);

    public void saveOrUpdate(Ambito ambito);

    public void delete(Ambito ambito);

    public List<Ambito> findByLavoratore(long id);


}
