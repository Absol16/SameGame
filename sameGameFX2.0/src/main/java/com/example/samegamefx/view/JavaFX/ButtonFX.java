package com.example.samegamefx.view.JavaFX;
import com.example.samegamefx.model.ColorEnum;
import javafx.scene.control.Button;

import javax.sound.midi.MidiUnavailableException;

public class ButtonFX extends Button{

    private Sound sound;

    /**
     * Constructor of button
     */
    public ButtonFX() {
    }


    void buttonColor(ColorEnum color){
        this.setStyle("-fx-background-radius: 20px; -fx-border-color: black; -fx-border-style: solid; -fx-border-width: 3" +
                "; -fx-border-radius: 100px;-fx-background-color: "+ color.fxBgColor);
    }

    void soundOnClick(int score){
            if(score != 0) {
                try {
                    sound = new Sound(7, 100, 80);
                } catch (MidiUnavailableException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    sound = new Sound(5, 30, 80);
                } catch (MidiUnavailableException e) {
                    e.printStackTrace();
                }
            }
    }
}
