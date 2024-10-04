package com.example.samegamefx.controller;

import javafx.stage.Stage;
import com.example.samegamefx.model.Board;
import com.example.samegamefx.view.JavaFX.ViewFX;


public class ControllerFX {
    private Board model;
    private ViewFX view;
    private Stage stage;

    public ControllerFX(Board model, Stage stage) throws Exception {
        this.model = model;
        this.stage = stage;
        this.view = new ViewFX(model, this);
        this.view.start(stage);
    }
}
