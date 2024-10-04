package com.example.samegamefx.view.JavaFX;

import com.example.samegamefx.command.playAShot;
import com.example.samegamefx.model.Board;
import javafx.scene.layout.StackPane;

public class Game {

    private Board board;
    private playAShot playShot;

    public Game(StackPane root, gameBoard game ,Board board, MenuGame menu) {
        this.board = board;
    }
}
