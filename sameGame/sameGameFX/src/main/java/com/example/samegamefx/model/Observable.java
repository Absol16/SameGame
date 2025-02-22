package com.example.samegamefx.model;

import com.example.samegamefx.view.JavaFX.Observer;

public interface Observable {
    /**
     * Adds an observer to the list of observers.
     *
     * @param observer The observer to be added.
     */
    void addObserver(Observer observer);

    /**
     * Removes the observer from the list.
     *
     * @param observer The  observer to be removed.
     */
    void removeObserver(Observer observer);

    /**
     * Notifies all observers by calling their 'update' method.
     */
    void notifyObservers(boolean gameOver);


}
