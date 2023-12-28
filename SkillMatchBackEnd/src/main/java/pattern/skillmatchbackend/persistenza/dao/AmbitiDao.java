package pattern.skillmatchbackend.persistenza.dao;

import pattern.skillmatchbackend.model.Ambiti;

import java.util.List;

public interface AmbitiDao {
    public List<Ambiti> findAll();

    public Ambiti findByNome(String nome);

    public Ambiti findById(Long id);
    public void save(Ambiti ambito);

}
