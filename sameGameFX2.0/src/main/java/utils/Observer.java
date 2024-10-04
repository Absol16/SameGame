package utils;


import com.example.samegamefx.model.ColoredBall;
import com.example.samegamefx.model.Score;

public interface Observer {
    /**
     * This method is called whenever the observed object has changed.
     */
    void update(ColoredBall[][] Tableau, Score score);

}
