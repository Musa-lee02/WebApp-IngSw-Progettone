package pattern.skillmatchbackend.persistenza.dao;

import java.util.List;

public interface Messaggio {

    public List<Messaggio> findAll();

    public Messaggio findByPrimaryKey(Long id);

    public void save(Messaggio messaggio);

    public List<Messaggio> findByChat(Long idChat);



}
