package pattern.skillmatchbackend.model;

import pattern.skillmatchbackend.persistenza.DBManager;


import java.util.LinkedList;
import java.util.List;

public class GestoreNotifiche {

    private List<Observer> observers = new LinkedList<>();

    public GestoreNotifiche() {

        for (Observer observer: DBManager.getInstance().getLavoratoreDao().findAllLazy() )
            addObserver(observer);

    }


    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Annuncio annuncio) {
        for (Observer observer : observers) {
            observer.update(annuncio);
        }
    }

}
