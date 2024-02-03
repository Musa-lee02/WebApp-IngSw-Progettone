package pattern.skillmatchbackend.persistenza.dao;

import pattern.skillmatchbackend.model.Lavoratore;
import java.util.List;

public interface LavoratoreDao {

    public List<Lavoratore> findAll();

    public List<Lavoratore> findAllLazy();

    public Lavoratore findByPrimaryKey(String username);

    public void saveOrUpdate(Lavoratore lavoratore);

    public void delete(Lavoratore lavoratore);

    public boolean isEmailTaken(String email);

    public boolean isUsernameTaken(String username);

    public List<Lavoratore> getLavoratori(long idAnnuncio);

    public Lavoratore getLavoratoreByIdAnnuncio(long idAnnuncio);


}
