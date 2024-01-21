package pattern.skillmatchbackend.persistenza.dao;

import pattern.skillmatchbackend.model.Utente;

import java.util.List;

public interface UtenteDao {

    public List<Utente> findAll();

    public Utente findByPrimaryKey(String username);

    public boolean checkLogin(String username, String password);
    public void saveOrUpdate(Utente utente);

    public boolean isUsernameTaken(String username);
    public boolean isEmailTaken(String email);


    public void delete(Utente utente);

}
