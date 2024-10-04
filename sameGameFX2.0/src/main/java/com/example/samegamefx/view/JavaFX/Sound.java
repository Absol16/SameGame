package com.example.samegamefx.view.JavaFX;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;

public class Sound {

    /**
     * Constructor of Sound.
     * @param channels type of instrument
     * @param noteNumber the note of the instrument
     * @param velocity note speed
     * @throws MidiUnavailableException
     */
    public Sound(int channels, int noteNumber, int velocity) throws MidiUnavailableException {
        var synth = MidiSystem.getSynthesizer();
        synth.open();
        var channel = synth.getChannels()[channels];
        channel.noteOn(noteNumber, velocity);
        var pause = new PauseTransition(Duration.seconds(0.4));
        pause.setOnFinished(event -> channel.noteOff(noteNumber));
        pause.play();
    }
}
