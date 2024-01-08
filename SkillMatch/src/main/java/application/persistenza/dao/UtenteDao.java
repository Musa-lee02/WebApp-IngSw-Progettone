package application.persistenza.dao;

import application.model.user.Utente;

import java.util.List;

public interface UtenteDao {
    public List<Utente> findAll();
    public Utente findByPrimaryKey(String email);

    public void saveOrUpdate(Utente utente);

    public void delete(Utente utente);

}
