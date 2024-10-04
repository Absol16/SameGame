package com.example.samegamefx.view.JavaFX;

import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MenuGame extends Node {

    private VBox option = new VBox();
    private Menu fileMenu ;
    private Label label;
    private TextField Height = new TextField();
    private TextField Width = new TextField();
    private Button Start = new Button("Start");
    private ComboBox combobox =  new ComboBox<>();

    public MenuGame(StackPane root,String fileName){

        label = new Label("SameGame");
        label.setStyle("-fx-font-family: 'Avenir Next'; -fx-font-size: 30");
        Start.setStyle("-fx-background-radius: 20px;" + "-fx-background-color: #00bbff;" + "-fx-border-color: #7502a2;" +
                "-fx-text-fill: WHITE;" + "-fx-border-radius: 30px;" + "-fx-font-size: 20px");
        label.setTextFill(Color.ROYALBLUE);

        Height.setPromptText("Enter the height");
        Width.setPromptText("Enter the width");

        this.fileMenu = new Menu(fileName);
        MenuItem level = new MenuItem("easy");
        MenuItem level1 = new MenuItem("medium");
        MenuItem level2 = new MenuItem("hard");
        fileMenu.getItems().addAll(level, level1, level2);

        MenuBar menuBar = new MenuBar(fileMenu);

        final ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setItems(FXCollections.observableArrayList("one", "two"));
        option.getChildren().add(comboBox);

        option.getChildren().add(label);
        label.setAlignment(Pos.TOP_CENTER);

        option.getChildren().addAll(menuBar,this.Height,this.Width,Start);
        option.setAlignment(Pos.BOTTOM_CENTER);
        option.setPadding(new Insets(50));

        option.setMaxWidth(300);
        option.setMaxHeight(300);
        option.setStyle("-fx-background-color: #159cff; -fx-border-style: solid;" +
                " -fx-border-color: #cc00cc ; -fx-border-width: 5");
        option.setAlignment(Pos.CENTER);
        root.getChildren().add(option);

        Height.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    Height.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        Width.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    Width.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public String getHeight(){
        return Height.getText();
    }

    public String getWidth(){
        return Width.getText();
    }

    public ComboBox getCombobox() { return combobox ; }
    public Button getStart() {
        return Start;
    }

    public VBox getOption() {
        return option;
    }

    public String getFileMenu() {
        return fileMenu.getText();
    }
}
