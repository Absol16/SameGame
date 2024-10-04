package com.example.samegamefx.view.JavaFX;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GameEnd extends VBox{

    private final Label end = new Label();
    private final Label finalScore = new Label();

    /**
     * Constructor of Vbox that appear when the game is over.
     */
    public GameEnd() {
    }

    /**
     * Method that active all style
     */
    public void setStyle(){
        this.getStyleClass().add("root");
        end.getStyleClass().add("end");
        end.setText("Game has Ended");
        finalScore.getStyleClass().add("end");
        this.getChildren().addAll(end, finalScore);
        this.setAlignment(Pos.CENTER);
        this.setVisible(false);
        this.setMaxWidth(700);
        this.setMaxHeight(700);
    }

    /**
     * Method that
     * @return the final score label
     */
    public Label getFinalScore() {
        return finalScore;
    }

}
