package application.persistenza.dao;

import application.model.user.Lavoratore;

import java.util.List;

public interface LavoratoreDao {

    public List<Lavoratore> findAll();

    public Lavoratore findByPrimaryKey(String username);

    public void saveOrUpdate(Lavoratore lavoratore);

    public void delete(Lavoratore lavoratore);


}
