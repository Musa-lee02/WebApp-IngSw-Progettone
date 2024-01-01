package persistenza.dao;

import application.model.user.Worker;

import java.util.List;

public interface WorkerDao {

    public List<Worker> findAll();
    public Worker findByPrimaryKey(String email);

    public void saveOrUpdate(Worker worker);

    public void delete(Worker worker);

}
