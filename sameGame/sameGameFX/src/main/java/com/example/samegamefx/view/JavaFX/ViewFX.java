package com.example.samegamefx.view.JavaFX;

import com.example.samegamefx.controller.ControllerFX;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.samegamefx.model.Board;

public class ViewFX extends Application implements Observer {

    private Board model;
    private ControllerFX controller;
    private Button ball;
    private StackPane root = new StackPane();
    private MenuGame menu;
    private gameBoard game;
    private Game sameGame;

    public ViewFX(Board model, ControllerFX controller){
        this.model = model;
        this.controller = controller;
        model.addObserver(this);
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SameGame");
        this.menu = new MenuGame(root,"Level");
        this.game = new gameBoard(root,menu,model);
        this.sameGame = new Game(root,game,model,menu);

        Scene scene = new Scene(root, 800, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void update(boolean gameIsFinish) {

    }
}
