package pattern.skillmatchbackend.persistenza.dao;
import pattern.skillmatchbackend.model.Ambiti;

import java.sql.Date;
import java.util.List;

public interface Annuncio {

    public Annuncio findById(Long id);

    public List<Annuncio> findByTitolo(String titolo);

    public List<Annuncio> findByDescrizione(String descrizione);

    public List<Annuncio> findByDataDiScadenza(Date dataDiScadenza);

    public List<Annuncio> findByProvinciaAnnuncio(String provinciaAnnuncio);
    public List<Annuncio> findByAmbiti(List<Ambiti> ambiti);

    public List<Annuncio> findAll();

    public void save(Annuncio annuncio);

    public void update(Annuncio annuncio);

    public void delete(Annuncio annuncio);
}
