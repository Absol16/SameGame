package com.example.samegamefx.view.JavaFX;

import com.example.samegamefx.controller.ControllerFX;
import com.example.samegamefx.model.Facade;
import javafx.application.Application;
import javafx.event.EventDispatchChain;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import com.example.samegamefx.model.Board;
import utils.Observer;

public class ViewFX extends Application{

    private final Board model;
    private final StackPane root = new StackPane();
    private MenuGame menu;
    private GameBoard game;

    /**
     * Constructor of ViewFX
     * @param model
     */
    public ViewFX(Board model){
        this.model = model;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SameGame");
        this.menu = new MenuGame(root);
        this.game = new GameBoard(root,menu,model);
        game.start();
        game.setStyle();
        model.addObserver(game);
        Scene scene = new Scene(root, 1200, 750);
        scene.getStylesheets().add("/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
