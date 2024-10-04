package com.example.samegamefx.view.JavaFX;

import com.example.samegamefx.command.CommandBuild;
import com.example.samegamefx.model.*;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import utils.Observer;

public class GameBoard implements Observer {

    private final GridPane boardGame = new GridPane();
    private boolean quit = false;
    private final AnchorPane underBoard = new AnchorPane();
    private final Board model;
    private final MenuGame menu;
    private ColoredBall[][] allBall;
    private Score score;
    private final Label scorelabl = new Label();
    private final Label ballRemaining = new Label();
    private final Label countScore = new Label();
    private final Button undo = new Button("undo");
    private final Button redo = new Button("redo");
    private final Button quitBtn = new Button("Quit");
    private final Button reset = new Button("reset");
    private ButtonFX ballFX;
    private final CommandBuild commandBuild;
    private GameEnd endBox;

    /**
     * Constructor of GameBoard
     * @param root
     * @param menu
     * @param model
     */
    public GameBoard(StackPane root, MenuGame menu, Board model) {
        this.menu = menu;
        this.model = model;
        this.endBox = new GameEnd();
        endBox.setStyle();
        this.commandBuild = new CommandBuild(model);
        underBoard.getChildren().addAll(scorelabl, countScore , ballRemaining, undo,quitBtn,redo,reset);
        underBoard.getChildren().add(boardGame);
        root.getChildren().addAll(underBoard, endBox);
    }

    /**
     * Method that make appear the game interface when we click on the button start
     */
    public void start() {
        menu.getStart().addEventHandler(MouseEvent.MOUSE_PRESSED, (mouseEvent) -> {
            model.reset((int) menu.getHeight().getValue(), (int) menu.getWidth().getValue());
            allBall = new ColoredBall[model.getHeight()][model.getWidth()];
            model.start(menu.getSelectedItem() == null ? Difficulty.EASY : (Difficulty) menu.getSelectedItem());
            boardGame.setDisable(false);
            boardGame.setVisible(true);
            underBoard.setDisable(false);
            underBoard.setVisible(true);
            menu.getOption().setDisable(true);
            menu.getOption().setVisible(false);
            initializeBalls();
            undoRedo();
            resetBtn();
            quitBtn();
        });
    }

    /**
     * Method that update the board interface and add all button on the gridPane.
     */
    public void initializeBalls() {
        model.notifyObserver();
        gameEnded(quit);
        boardGame.getChildren().clear();
        scorelabl.setText("Current score : " + score.getScore());
        if(model.getCountScore() != 0 ) {
            countScore.setText("+ " + score.getCountScore());
        }
        ballRemaining.setText("Ball remaining : " + (model.getWidth() * model.getWidth() - model.getNbColorDelete()));
        scorelabl.getStyleClass().add("labelWhite");
        countScore.setTextFill(Color.RED);
        ballRemaining.getStyleClass().add("labelWhite");;

        for (int i = 0; i < allBall.length; i++) {
            for (int j = 0; j < allBall[0].length; j++) {
                if (allBall[i][j].getColor() != ColorEnum.NONE) {
                    boardGame.add(addButton(allBall[i][j].getColor(), j,i),j,i);
                }
            }
        }
    }

    /**
     * Method called by addButtonColored(). It creates a new colored ball and when we click on it
     * the actual state of the board is saved on a stack.
     * @param color of the new ball
     * @param j position Y
     * @param i position X
     * @return Ball
     */
    private ButtonFX addButton(ColorEnum color, int j, int i) {
            ballFX = new ButtonFX();
            ballFX.buttonColor(color);

        ballFX.setPadding(new Insets(2,10,2,10));
        ballFX.setOnAction(event -> {
            commandBuild.playAShot(i, j);
            ballFX.soundOnClick(model.getCountScore());
            initializeBalls();
        });
        return ballFX;
    }

    /**
     * Method that end the game and make appear an interface when it's finish
     * @param quit : turn to true when the user want to quit
     */
    private void gameEnded(boolean quit) {
        if (model.gameIsFinish() || quit) {
            underBoard.setDisable(true);
            underBoard.setVisible(false);
            menu.getOption().setDisable(true);
            menu.getOption().setVisible(false);
            endBox.getFinalScore().setText("Your final score : " + score.getScore());
            endBox.setDisable(false);
            endBox.setVisible(true);
            var pause = new PauseTransition(Duration.seconds(4));
            pause.setOnFinished(event -> {
                menu.getOption().setDisable(false);
                menu.getOption().setVisible(true);
                endBox.setVisible(false);
                endBox.setDisable(true);
                model.clearList();
                model.setScore(0);
                this.quit = false;
            });
            pause.play();
            commandBuild.clearStack();
        }
    }

    /**
     * Button undo reset the state of the balls before the last click.
     * Button redo reset the state of the balls before the last click of the undo button.
     */
    private void undoRedo() {
        undo.setOnAction(event -> {
            commandBuild.undo();
            initializeBalls();
        });
        redo.setOnAction(event -> {
            commandBuild.redo();
            initializeBalls();
        });
    }

    private void resetBtn(){
        reset.setOnAction(event -> {
            commandBuild.reset();
            initializeBalls();
        });
    }

    private void quitBtn(){
        quitBtn.setOnAction(event -> {
            this.quit = true;
            gameEnded(quit);
        });
    }

    /**
     * Method that put the style of the board game.
     */
    public void setStyle(){
        undo.getStyleClass().add("optionBtn");
        redo.getStyleClass().add("optionBtn");
        quitBtn.getStyleClass().add("optionBtn");
        reset.getStyleClass().add("optionBtn");

        boardGame.setStyle("-fx-background-color: #011341");
        underBoard.getStyleClass().add("underBoard");
        boardGame.setVisible(false);
        boardGame.setDisable(true);
        underBoard.setDisable(true);
        underBoard.setVisible(false);
        underBoard.setMaxWidth(300);
        underBoard.setMaxHeight(600);

        AnchorPane.setLeftAnchor(boardGame, 200.0);
        AnchorPane.setRightAnchor(boardGame, 200.0);
        AnchorPane.setTopAnchor(undo, 5.0);
        AnchorPane.setTopAnchor(quitBtn, 5.0);
        AnchorPane.setTopAnchor(redo, 5.0);
        AnchorPane.setTopAnchor(reset, 5.0);
        AnchorPane.setBottomAnchor(boardGame, 0.0);
        AnchorPane.setRightAnchor(undo, 20.0);
        AnchorPane.setLeftAnchor(quitBtn, 300.0);
        AnchorPane.setLeftAnchor(reset, 400.0);
        AnchorPane.setLeftAnchor(redo, 20.0);
        AnchorPane.setTopAnchor(scorelabl, 35.0);
        AnchorPane.setTopAnchor(countScore, 70.0);
        AnchorPane.setRightAnchor(countScore, 10.0);
        AnchorPane.setRightAnchor(scorelabl, 10.0);
        AnchorPane.setTopAnchor(ballRemaining, 35.0);
        AnchorPane.setLeftAnchor(ballRemaining, 10.0);
    }

    @Override
    public void update(ColoredBall[][] boardGame,Score score) {
        for (int i = 0; i < boardGame.length; i++) {
            for (int j = 0; j < boardGame[0].length; j++) {
                this.allBall[i][j] = boardGame[i][j];
            }
        }
        this.score = score;
    }
}

