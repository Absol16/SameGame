package com.example.samegamefx.Main;

import com.example.samegamefx.controller.ControllerFX;
import javafx.application.Application;
import javafx.stage.Stage;
import com.example.samegamefx.model.Board;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        var model = new Board(5, 8);
        var controller = new ControllerFX(model, stage);
    }
}
