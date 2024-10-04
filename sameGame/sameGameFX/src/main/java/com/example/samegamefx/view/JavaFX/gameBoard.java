package com.example.samegamefx.view.JavaFX;

import com.example.samegamefx.model.Board;
import com.example.samegamefx.model.ballColored;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.nio.charset.CharsetDecoder;

public class gameBoard {

    private GridPane boardGame = new GridPane();
    private Board model;
    private MenuGame menu;
    private ballColored[][] allBall;

    public gameBoard(StackPane root, MenuGame menu, Board model) {
        this.menu = menu;
        this.model = model;
        this.allBall = model.getTableau();
        boardGame.getChildren().addAll();
        boardGame.setStyle("-fx-background-color: black");
        boardGame.setVisible(false);
        boardGame.setDisable(true);
        boardGame.setAlignment(Pos.CENTER);
        root.getChildren().add(boardGame);
        //System.out.println("menu.getHeight() " + menu.getHeight());
        Start();
    }

    private void Start() {
        menu.getStart().addEventHandler(MouseEvent.MOUSE_PRESSED, (mouseEvent) -> {
            model.reset(Integer.parseInt(
                    menu.getHeight().equals("") ? "0" : menu.getHeight()),
                    Integer.parseInt(menu.getWidth().equals("") ? "0" : menu.getWidth())
            );
            System.out.println(menu.getCombobox().getValue());

            model.Start("hard");
            boardGame.setDisable(false);
            boardGame.setVisible(true);
            menu.getOption().setDisable(false);
            initializeBalls();
        });
    }

    public void initializeBalls() {
        boardGame.getChildren().clear();
        int numberBall = model.getWidth() * model.getHeight();
        for (int i = 0; i < numberBall; i++) {
            switch (model.getBall().get(i).getColor()) {
                case "🟡" -> boardGame.add(addButton("yellow"), model.getBall().get(i).getX(), model.getBall().get(i).getY());
                case "🔵" -> boardGame.add(addButton("blue"), model.getBall().get(i).getX(), model.getBall().get(i).getY());
                case "🟣" -> boardGame.add(addButton("purple"), model.getBall().get(i).getX(), model.getBall().get(i).getY());
                case "🟢" -> boardGame.add(addButton("green"), model.getBall().get(i).getX(), model.getBall().get(i).getY());
                case "🔴" -> boardGame.add(addButton("red"), model.getBall().get(i).getX(), model.getBall().get(i).getY());
            }
        }
    }

    public Button addButton(String color) {
        Button ball = new Button();
        ball.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 3");
        ball.setPadding(new Insets(10));
        switch (color) {
            case "yellow":
                ball.setStyle("-fx-background-color: #FFEA00EF;-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 3");
                break;
            case "blue":
                ball.setStyle("-fx-background-color: #00d0ff;-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 3");
                break;
            case "purple":
                ball.setStyle("-fx-background-color: #9f0b9f;-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 3");
                break;
            case "green":
                ball.setStyle("-fx-background-color: #7cf302;-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 3");
                break;
            case "red":
                ball.setStyle("-fx-background-color: #e30000;-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 3");
                break;
        }
        return ball;
    }
}
