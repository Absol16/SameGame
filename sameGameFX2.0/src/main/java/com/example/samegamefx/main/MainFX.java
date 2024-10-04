package com.example.samegamefx.main;

import com.example.samegamefx.controller.ControllerFX;
import com.example.samegamefx.model.Board;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        var model = new Board(10, 10);
        var controller = new ControllerFX(model, stage);
    }
}

