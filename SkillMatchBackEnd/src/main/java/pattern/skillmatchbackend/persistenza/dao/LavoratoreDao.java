package pattern.skillmatchbackend.persistenza.dao;

import pattern.skillmatchbackend.model.Cliente;
import pattern.skillmatchbackend.model.Lavoratore;

import java.util.List;

public interface LavoratoreDao {
    public List<Lavoratore> findAll();
    public Lavoratore findByUsername(String username);
    public void save(Lavoratore lavoratore);

    public List<Lavoratore> findByNome(String nome);

    public List<Lavoratore> findByCognome(String cognome);

    public List<Lavoratore> findByNomeCognome(String nome, String cognome);

    public List<Lavoratore> findByEmail(String email);

    public List<Lavoratore> findByIndirizzo(String indirizzo);

    public List<Lavoratore> findByCitta(String citta);

    public List<Lavoratore> findByProvincia(String provincia);

    public List<Lavoratore> findByAmbito(String ambito);

    public List<Lavoratore> findbyProvinciaLavoro(String provincia);

}
