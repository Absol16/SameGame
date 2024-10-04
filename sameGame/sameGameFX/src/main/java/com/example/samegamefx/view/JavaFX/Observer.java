package com.example.samegamefx.view.JavaFX;


public interface Observer {
    /**
     * This method is called whenever the observed object has changed.
     *
     * @param gameOver
     */
    void update(boolean gameOver);
}
