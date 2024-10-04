package com.example.samegamefx.controller;

import com.example.samegamefx.model.Board;
import com.example.samegamefx.view.JavaFX.ViewFX;
import javafx.stage.Stage;


public class ControllerFX {
    private final ViewFX view;
    private final Board model;
    private final Stage stage;

    public ControllerFX(Board model, Stage stage) throws Exception {
        this.model = model;
        this.stage = stage;
        this.view = new ViewFX(model);
        this.view.start(stage);
    }
}
