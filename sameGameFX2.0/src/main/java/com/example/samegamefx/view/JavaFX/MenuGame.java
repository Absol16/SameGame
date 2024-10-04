package com.example.samegamefx.view.JavaFX;

import com.example.samegamefx.model.Difficulty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MenuGame extends Node {

    private VBox option = new VBox();
    private Slider height = new Slider(0,20,10);
    private Slider width = new Slider(0,20,10);
    private Button start = new Button("start");
    private Object selectedItem;

    /**
     * Constructor of MenuGame
     */
    public MenuGame(StackPane root){

        Label label = new Label("SameGame");
        Label heightLabl = new Label("Height");
        Label widthLabl = new Label("width");
        label.setStyle("-fx-font-family: 'Avenir Next'; -fx-font-size: 30");
        start.setStyle("-fx-background-color: rgba(255,255,252,0.81);" + "-fx-border-color: #003a33 ;" +
                "-fx-text-fill: WHITE;" + "-fx-font-size: 20px");
        label.setTextFill(Color.BLUEVIOLET);

        height.valueProperty().addListener((observable, oldValue, newValue) -> {
            int value = (int) height.getValue();
            heightLabl.setText("Height : " + value);
        });

        heightLabl.setStyle("-fx-font-family: 'Avenir Next'; -fx-font-size: 15");
        heightLabl.setTextFill(Color.WHITE);

        width.valueProperty().addListener((observable, oldValue, newValue) -> {
            int value = (int) width.getValue();
            widthLabl.setText("Width: " + Integer.toString(value));
        });

        widthLabl.setStyle("-fx-font-family: 'Avenir Next'; -fx-font-size: 15");
        widthLabl.setTextFill(Color.WHITE);

        final ComboBox<Difficulty> comboBox = new ComboBox<>();
        comboBox.setItems(FXCollections.observableArrayList(Difficulty.values()));

        comboBox.setOnAction((event) -> {
            this.selectedItem = comboBox.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                label.setText("Select a level");
            } else if(selectedItem== Difficulty.EASY){
                label.setTextFill(Color.WHITE);
                option.setStyle("-fx-background-color: #c2f66c; -fx-border-style: solid;" +
                        " -fx-border-color: #76efd2 ; -fx-border-width: 4");
                start.setStyle("-fx-background-color: #cff692;" + "-fx-border-color: #91fce1 ;" +
                        "-fx-text-fill: WHITE;" +"-fx-border-width: 4;" + "-fx-font-size: 20px");

            }else if(selectedItem==Difficulty.MEDIUM){
                label.setTextFill(Color.ORANGE);
                option.setStyle("-fx-background-color: #f6d85f; -fx-border-style: solid;" +
                        " -fx-border-color: #f8543e ; -fx-border-width: 4");
                start.setStyle("-fx-background-color: #f6d85f;" + "-fx-border-color: #f8543e ;" +
                        "-fx-text-fill: WHITE;" + "-fx-border-width: 4;" + "-fx-font-size: 20px");
            }else if(selectedItem==Difficulty.HARD){
                label.setTextFill(Color.RED);
                option.setStyle("-fx-background-color: rgba(255,255,255,0.64); -fx-border-style: solid;" +
                        " -fx-border-color: #003a33 ; -fx-border-width: 4");
                start.setStyle("-fx-background-color: rgba(255,255,252,0.81);" + "-fx-border-color: #003a33 ;" +
                        "-fx-text-fill: WHITE;" + "-fx-border-width: 4;" + "-fx-font-size: 20px");
            }

        });
        option.getChildren().add(label);
        label.setAlignment(Pos.TOP_CENTER);
        label.setPadding(new Insets(20,0,20,0));
        start.setPadding(new Insets(7,0,4,0));

        option.getChildren().addAll(comboBox,heightLabl,height,widthLabl,width,start);
        option.setAlignment(Pos.CENTER);
        option.setPadding(new Insets(50));

        comboBox.setStyle("-fx-background-color: #fffefd; -fx-border-style: solid;" +
                " -fx-border-color: #020202 ; -fx-border-width: 3; -fx-font-family: 'DecoType Naskh'; -fx-font-size: 14;");
        comboBox.setPromptText("Difficult");
        comboBox.getSelectionModel().selectFirst();
        option.setMaxWidth(300);
        option.setMaxHeight(300);
        option.setStyle("-fx-background-color: #000000; -fx-border-style: solid;" +
                " -fx-border-color: #fcf233 ; -fx-border-width: 4");
        root.getChildren().add(option);
    }

    /**
     * Method that
     * @return the slider
     */
    public Slider getHeight() {
        return height;
    }

    /**
     * Method that
     * @return the width
     */
    public Slider getWidth() {
        return width;
    }

    /**
     * Method that
     * @return the selectedItem
     */
    public Object getSelectedItem() {
        return selectedItem;
    }

    /**
     * Method that
     * @return the button start
     */
    public Button getStart() {
        return start;
    }

    /**
     * Method that
     * @return the option Vbox
     */
    public VBox getOption() {
        return option;
    }
}
