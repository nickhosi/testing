package observer;

/**
 * Classe abstraite du patron Observable
 *
 * @author Fred Simard | ETS
 * @version ETE 2018 - TP2
 */

import java.util.ArrayList;

public abstract class MonObservable {

    // liste des observers
    ArrayList<MonObserver> observers = new ArrayList<MonObserver>();

    /**
     * méthode pour attacher un Observer
     * @param observer
     */
    public void attacherObserver(MonObserver observer) {
        observers.add(observer);
    }

    /**
     * méthode pour avertir tous les observers
     */
    public void avertirLesObservers() {
        ArrayList<MonObserver> temp = new ArrayList<>(observers);
        for (MonObserver observer : temp) {
            observer.avertir();
        }
    }

}
