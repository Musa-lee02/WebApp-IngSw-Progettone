package pattern.skillmatchbackend.model;

import java.util.ArrayList;
import java.util.List;

public class GestoreAnnunci {

    private List<Observer> observers = new ArrayList<>();

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
