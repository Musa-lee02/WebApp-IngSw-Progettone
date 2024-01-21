package pattern.skillmatchbackend.persistenza.dao;

import pattern.skillmatchbackend.model.Cliente;

import java.util.List;

public interface ClienteDao {

    public List<Cliente> findAll();

    public Cliente findByPrimaryKey(String username);

    public void saveOrUpdate(Cliente cliente);

    public void delete(Cliente cliente);


}
