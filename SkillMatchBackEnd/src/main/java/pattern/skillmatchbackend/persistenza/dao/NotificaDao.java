package pattern.skillmatchbackend.persistenza.dao;

import pattern.skillmatchbackend.model.Notifica;

import java.util.List;

public interface NotificaDao {

    public List<Notifica> findAll();

    public Notifica findByPrimaryKey(long id);

    public void saveOrUpdate(Notifica notifica);

    public void delete(Notifica notifica);

}
