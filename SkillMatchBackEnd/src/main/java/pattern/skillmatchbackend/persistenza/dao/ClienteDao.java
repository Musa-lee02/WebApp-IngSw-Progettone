package pattern.skillmatchbackend.persistenza.dao;

import pattern.skillmatchbackend.model.Cliente;


import java.util.List;

public interface ClienteDao {

    public List<Cliente> findAll();
    public Cliente findByUsername(String username);
    public void save(Cliente user);

    public List<Cliente> findByNome(String nome);

    public List<Cliente> findByCognome(String cognome);

    public List<Cliente> findByNomeCognome(String nome, String cognome);

    public List<Cliente> findByEmail(String email);

    public List<Cliente> findByIndirizzo(String indirizzo);

    public List<Cliente> findByCitta(String citta);

    public List<Cliente> findByProvincia(String provincia);

}