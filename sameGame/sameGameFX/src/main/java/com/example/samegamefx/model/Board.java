package com.example.samegamefx.model;

import com.example.samegamefx.view.JavaFX.Observer;

import java.util.ArrayList;
import java.util.List;


public class Board implements Observable{
    private List<Observer> observer;
    private int height;
    private int width;
    private ballColored[][] Tableau;
    private final ArrayList<ball> listBall;
    private final ArrayList<String> listColor;
    private int score;

    public Board(int height, int width) {
        this.observer = new ArrayList<>();
        this.height = height;
        this.width = width;
        this.Tableau = new ballColored[this.height][this.width];
        this.listBall = new ArrayList<>();
        this.listColor = new ArrayList<>();
    }

    public void reset(int height, int width) {
        this.height = height;
        this.width = width;
        this.Tableau = new ballColored[this.height][this.width];
    }

    /**
     * Method that add new type of colored ball on the list of color.
     *
     * @param numberColor
     */
    private void listOfColor(String numberColor) {
        switch (numberColor) {
            case "hard":
                this.listColor.add("🟣");
            case "medium":
                this.listColor.add("🟢");
            case "easy":
                this.listColor.add("🔴");
                this.listColor.add("🔵");
                this.listColor.add("🟡");
                break;

        }
    }

    /**
     * Method that Start adding all balls on the list of ball
     *
     * @param numberColor
     */
    public void Start(String numberColor) {
        int numberBall = height * width;
        int x, y;
        int cpt = 0;
        listOfColor(numberColor);
        while (cpt < numberBall) {
            for (y = 0; y < height; y++) {
                for (x = 0; x < width; x++) {
                    ballColored ball = new ballColored(x, y, listColor.get((int) (Math.random() * listColor.size())));
                    listBall.add(ball);
                    cpt++;
                }
            }
        }
        putBall();
    }

    /**
     * Method that put all balls on the Board.
     */
    private void putBall() {
        for (int i = 0; i < listBall.size(); i++) {
            this.Tableau[listBall.get(i).getY()][listBall.get(i).getX()] = (ballColored) listBall.get(i);
        }
    }

    /**
     * Method that delete ball on the board at the position (i,j).
     *
     * @param i number of axe y.
     * @param j number of axe x
     * @return
     */
    public int deleteBall(int i, int j) {
        int countScore = 0;
        boolean[][] visited = new boolean[Tableau.length][Tableau[0].length];
        for (int k = 0; k < visited.length; k++) {
            for (int l = 0; l < visited[0].length; l++) {
                visited[k][l] = false;
            }
        }
        if (i < 0 || i >= Tableau.length || j < 0 || j > Tableau[0].length) {
            throw new IllegalArgumentException("Something wrong with the position");
        }
        ballColored saveColorBall = Tableau[i][j];
        if (saveColorBall == null) {
            throw new IllegalArgumentException("There is no ball there !");
        }
        if (saveColorBall.getColor() != "◼️") {
            if ((i - 1 >= 0 && Tableau[i - 1][j].getColor() == saveColorBall.getColor()) ||
                    (i + 1 < Tableau.length && Tableau[i + 1][j].getColor() == saveColorBall.getColor()) ||
                    (j - 1 >= 0 && Tableau[i][j - 1].getColor() == saveColorBall.getColor()) ||
                    (j + 1 < Tableau[0].length && Tableau[i][j + 1].getColor() == saveColorBall.getColor())) {
                visited[i][j] = true;
                countScore = 1;
                countScore += DeleteNeighborBall(i + 1, j, saveColorBall, visited, "up") +
                        DeleteNeighborBall(i - 1, j, saveColorBall, visited, "down") +
                        DeleteNeighborBall(i, j - 1, saveColorBall, visited, "right") +
                        DeleteNeighborBall(i, j + 1, saveColorBall, visited, "left");
                Tableau[i][j].delete();
                //  Compact the board
                compactBoardDown();
                compactBoardLeft();
            }
        }
        score += countScore * (countScore - 1);
        return this.score;
    }

    /**
     * Method that searches around the current ball the colored ball that have the same color.
     *
     * @param i
     * @param j
     * @param colorBall : current Ball
     * @param visited   : position that is already visited.
     * @param direction : direction where we search.
     * @return the number of deleted ball.
     */
    private int DeleteNeighborBall(int i, int j, ballColored colorBall, boolean[][] visited, String direction) {
        int count = 0;
        if (i >= 0 && i < Tableau.length && j >= 0 && j < Tableau.length) {
            if (colorBall.getColor() == Tableau[i][j].getColor() && !visited[i][j]) {
                visited[i][j] = true;
                Tableau[i][j].delete();
                count = count + 1;
                if (!direction.equals("up")) {
                    count += DeleteNeighborBall(i - 1, j, colorBall, visited, "down");
                }
                if (!direction.equals("down")) {
                    count += DeleteNeighborBall(i + 1, j, colorBall, visited, "up");
                }
                if (!direction.equals("left")) {
                    count += DeleteNeighborBall(i, j - 1, colorBall, visited, "right");
                }
                if (!direction.equals("right")) {
                    count += DeleteNeighborBall(i, j + 1, colorBall, visited, "left");
                }
            }
        }
        return count;
    }

    /**
     * Method that make balls falling down
     */
    private void compactBoardDown() {
        //  First move everything down
        for (int col = 0; col < Tableau[0].length; col++) {
            int nextEmptyRow = Tableau.length - 1;
            int nextOccupiedRow = nextEmptyRow;
            while (nextOccupiedRow >= 0 && nextEmptyRow >= 0) {
                //  First find the next empty row
                while (nextEmptyRow >= 0 && Tableau[nextEmptyRow][col].getColor() != "◼️") {
                    nextEmptyRow--;
                }
                if (nextEmptyRow >= 0) {
                    //  Then find the next occupied row from the next empty row
                    nextOccupiedRow = nextEmptyRow - 1;
                    while (nextOccupiedRow >= 0 &&
                            Tableau[nextOccupiedRow][col].getColor() == "◼️")
                        nextOccupiedRow--;
                    if (nextOccupiedRow >= 0) {
                        //  Now move the block from occupied to empty
                        Tableau[nextEmptyRow][col].setColor(Tableau[nextOccupiedRow][col].getColor());
                        Tableau[nextOccupiedRow][col].delete();
                    }
                }
            }
        }
    }

    /**
     * Method that compact all balls to the left when a column is empty.
     */
    private void compactBoardLeft() {
        for (int col = 0; col < Tableau[0].length; col++) {
            //  Then move everything from right to left
            int NextEmptyCol = 0;
            int NextOccupiedCol = NextEmptyCol;
            while (NextEmptyCol < Tableau[0].length && NextOccupiedCol < Tableau[0].length) {
                //  First find the next empty column
                while (NextEmptyCol < Tableau[0].length && Tableau[Tableau.length - 1][NextEmptyCol].getColor() != "◼️") {
                    NextEmptyCol++;
                }
                if (NextEmptyCol < Tableau[0].length) {
                    //  Then find the next column with something in it
                    NextOccupiedCol = NextEmptyCol + 1;
                    while (NextOccupiedCol < Tableau[0].length &&
                            Tableau[Tableau.length - 1][NextOccupiedCol].getColor() == "◼️")
                        NextOccupiedCol++;
                    if (NextOccupiedCol < Tableau[0].length) {
                        //  Move entire column to the left
                        for (int row = 0; row < Tableau.length; row++) {
                            Tableau[row][NextEmptyCol].setColor(Tableau[row][NextOccupiedCol].getColor());
                            Tableau[row][NextOccupiedCol].delete();
                        }
                    }
                }
            }
        }
    }

    /**
     * Method that
     *
     * @return true if the game is finish or false if it isn't.
     */
    public boolean gameIsFinish() {
        boolean hasEnd = true;
        ballColored[][] Tab = this.Tableau;
        for (int i = 0; i < Tab.length && hasEnd; i++) {
            for (int j = 0; j < Tab[0].length; j++) {
                if (Tab[i][j].getColor() != "◼️") {
                    if (i < Tab.length - 1) {
                        if (Tab[i + 1][j].getColor() == Tab[i][j].getColor()) {
                            hasEnd = false;
                        }
                    }
                    if (i > 0) {
                        if (Tab[i - 1][j].getColor() == Tab[i][j].getColor()) {
                            hasEnd = false;
                        }
                    }
                    if (j < Tab[0].length - 1) {
                        if (Tab[i][j + 1].getColor() == Tab[i][j].getColor()) {
                            hasEnd = false;
                        }
                    }
                    if (j > 0) {
                        if (Tab[i][j - 1].getColor() == Tab[i][j].getColor()) {
                            hasEnd = false;
                        }
                    }
                }
            }
        }
        return hasEnd;
    }

    /**
     * Method that copy the current board.
     *
     * @return
     */
    public ballColored[][] getCopyBoard() {
        ballColored[][] copyBoard = new ballColored[Tableau.length][Tableau[0].length];
        for (int i = 0; i < Tableau.length; i++) {
            for (int j = 0; j < Tableau[0].length; j++) {
                copyBoard[i][j] = new ballColored(Tableau[i][j].getY(), Tableau[i][j].getX(), Tableau[i][j].getColor());
            }
        }
        return copyBoard;
    }

    /**
     * Method that
     *
     * @return the number of deleted ball
     */
    public int getNbColorDelete() {
        int nbColorDelete = 0;
        for (int i = 0; i < Tableau.length; i++) {
            for (int j = 0; j < Tableau[0].length; j++) {
                if (Tableau[i][j].getColor() == "◼️") {
                    nbColorDelete++;
                }
            }
        }
        return nbColorDelete;
    }

    @Override
    public void addObserver(Observer observer) {
        if (!this.observer.contains(observer))
            this.observer.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (!this.observer.contains(observer))
            this.observer.remove(observer);
    }

    @Override
    public void notifyObservers(boolean gameOver) {
        for (Observer obs : observer) {
            obs.update(gameOver);
        }
    }

    public int getHeight() {
        return height;
    }

    public ballColored[][] getTableau() {
        return Tableau;
    }

    public int getWidth() {
        return width;
    }

    public ArrayList<ball> getBall() {
        return listBall;
    }

    public int getScore() {
        return score;
    }

    public void setTableau(ballColored[][] tableau) {
        Tableau = tableau;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setHeight (int height) { this.height = height; }

    public void setWidth (int width) { this.width = width; }
}


