package com.example.samegamefx.model;

import utils.Observable;
import utils.Observer;
import java.util.ArrayList;

public class Board implements Observable {
    private int height;
    private int width;
    private ColoredBall[][] board;
    private final ArrayList<BallColor> listBall;
    private final ArrayList<ColorEnum> listColor;
    private Score score;

    /**
     * Constructor of Board
     * @param height
     * @param width
     */
    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.score = new Score();
        this.board = new ColoredBall[this.height][this.width];
        this.listBall = new ArrayList<>();
        this.listColor = new ArrayList<>();
    }

    /**
     * Method that reset the height and the width of the board
     * @param height
     * @param width
     */
    public void reset(int height, int width) {
        this.height = height;
        this.width = width;
        this.board = new ColoredBall[this.height][this.width];
    }

    /**
     * Method that clear List of ball and list of color.
     */
    public void clearList(){
        this.listBall.clear();
        this.listColor.clear();
        notifyObserver();
    }

    /**
     * Method that add new type of colored ball on the list of color.
     *
     * @param difficulty of the level
     */
    private void listOfColor(Difficulty difficulty) {
        this.listColor.addAll(difficulty.colors);
    }

    /**
     * Method that Start adding all balls on the list of ball
     *
     * @param numberColor
     */
    public void start(Difficulty numberColor) {
        int numberBall = height * width;
        int x, y;
        int cpt = 0;
        listOfColor(numberColor);
        while (cpt < numberBall) {
            for (y = 0; y < height; y++) {
                for (x = 0; x < width; x++) {
                    ColoredBall ball = new ColoredBall(x, y, listColor.get((int) (Math.random() * listColor.size())));
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
    public void putBall() {
        for (BallColor ballColor : listBall) {
            this.board[ballColor.getY()][ballColor.getX()] = (ColoredBall) ballColor;
        }
        notifyObserver();
    }

    /**
     * Method that delete ball on the board at the position (i,j).
     *
     * @param i number of axe y.
     * @param j number of axe x
     */
    public void deleteBall(int i, int j) {
        int countScore = 0;
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int k = 0; k < visited.length; k++) {
            for (int l = 0; l < visited[0].length; l++) {
                visited[k][l] = false;
            }
        }
        if (i < 0 || i >= board.length || j < 0 || j > board[0].length) {
            throw new IllegalArgumentException("Something wrong with the position");
        }
        ColoredBall saveColorBall = board[i][j];
        if (saveColorBall == null) {
            throw new IllegalArgumentException("There is no ball there !");
        }
        if (!(saveColorBall.getColor() == ColorEnum.NONE)) {
            if ((i - 1 >= 0 && board[i - 1][j].getColor().equals(saveColorBall.getColor())) ||
                    (i + 1 < board.length && board[i + 1][j].getColor().equals(saveColorBall.getColor())) ||
                    (j - 1 >= 0 && board[i][j - 1].getColor().equals(saveColorBall.getColor())) ||
                    (j + 1 < board[0].length && board[i][j + 1].getColor().equals(saveColorBall.getColor()))) {
                visited[i][j] = true;
                countScore = 1;
                countScore += deleteNeighborBall(i + 1, j, saveColorBall, visited, "up") +
                        deleteNeighborBall(i - 1, j, saveColorBall, visited, "down") +
                        deleteNeighborBall(i, j - 1, saveColorBall, visited, "right") +
                        deleteNeighborBall(i, j + 1, saveColorBall, visited, "left");
                board[i][j].delete();
                //  Compact the board
                compactBoardDown();
                compactBoardLeft();
            }
        }
        score.setCountScore(countScore * (countScore - 1));
        score.addPoint(score.getCountScore());
        notifyObserver();
    }

    /**
     * Method that searches around the current ball the colored ball that have the same color.
     *
     * @param i position X
     * @param j position Y
     * @param colorBall : current Ball
     * @param visited   : position that is already visited.
     * @param direction : direction where we search.
     * @return the number of deleted ball.
     */
    private int deleteNeighborBall(int i, int j, ColoredBall colorBall, boolean[][] visited, String direction) {
        int count = 0;
        if (i >= 0 && i < board.length && j >= 0 && j < board[0].length) {
            if (colorBall.getColor().equals(board[i][j].getColor()) && !visited[i][j]) {
                visited[i][j] = true;
                board[i][j].delete();
                count = count + 1;
                if (!direction.equals("up")) {
                    count += deleteNeighborBall(i - 1, j, colorBall, visited, "down");
                }
                if (!direction.equals("down")) {
                    count += deleteNeighborBall(i + 1, j, colorBall, visited, "up");
                }
                if (!direction.equals("left")) {
                    count += deleteNeighborBall(i, j - 1, colorBall, visited, "right");
                }
                if (!direction.equals("right")) {
                    count += deleteNeighborBall(i, j + 1, colorBall, visited, "left");
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
        for (int col = 0; col < board[0].length; col++) {
            int nextEmptyRow = board.length - 1;
            int nextOccupiedRow = nextEmptyRow;
            while (nextOccupiedRow >= 0 && nextEmptyRow >= 0) {
                //  First find the next empty row
                while (nextEmptyRow >= 0 && !(board[nextEmptyRow][col].getColor() == ColorEnum.NONE)) {
                    nextEmptyRow--;
                }
                if (nextEmptyRow >= 0) {
                    //  Then find the next occupied row from the next empty row
                    nextOccupiedRow = nextEmptyRow - 1;
                    while (nextOccupiedRow >= 0 &&
                            board[nextOccupiedRow][col].getColor() == ColorEnum.NONE)
                        nextOccupiedRow--;
                    if (nextOccupiedRow >= 0) {
                        //  Now move the block from occupied to empty
                        board[nextEmptyRow][col].setColor(board[nextOccupiedRow][col].getColor());
                        board[nextOccupiedRow][col].delete();
                    }
                }
            }
        }
    }

    /**
     * Method that compact all balls to the left when a column is empty.
     */
    private void compactBoardLeft() {
        for (int col = 0; col < board[0].length; col++) {
            //  Then move everything from right to left
            int NextEmptyCol = 0;
            int NextOccupiedCol = NextEmptyCol;
            while (NextEmptyCol < board[0].length && NextOccupiedCol < board[0].length) {
                //  First find the next empty column
                while (NextEmptyCol < board[0].length && board[board.length - 1][NextEmptyCol].getColor() != ColorEnum.NONE) {
                    NextEmptyCol++;
                }
                if (NextEmptyCol < board[0].length) {
                    //  Then find the next column with something in it
                    NextOccupiedCol = NextEmptyCol + 1;
                    while (NextOccupiedCol < board[0].length &&
                            board[board.length - 1][NextOccupiedCol].getColor() == ColorEnum.NONE)
                        NextOccupiedCol++;
                    if (NextOccupiedCol < board[0].length) {
                        //  Move entire column to the left
                        for (int row = 0; row < board.length; row++) {
                            board[row][NextEmptyCol].setColor(board[row][NextOccupiedCol].getColor());
                            board[row][NextOccupiedCol].delete();
                        }
                    }
                }
            }
        }
    }

    /**
     * Method that
     * @return true if the game is finish or false if it isn't.
     */
    public boolean gameIsFinish() {
        boolean hasEnd = true;
        ColoredBall[][] board = this.board;
        for (int i = 0; i < board.length && hasEnd; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].getColor() != ColorEnum.NONE) {
                    if (i < board.length - 1) {
                        if (board[i + 1][j].getColor().equals(board[i][j].getColor())) {
                            hasEnd = false;
                        }
                    }
                    if (i > 0) {
                        if (board[i - 1][j].getColor().equals(board[i][j].getColor())) {
                            hasEnd = false;
                        }
                    }
                    if (j < board[0].length - 1) {
                        if (board[i][j + 1].getColor().equals(board[i][j].getColor())) {
                            hasEnd = false;
                        }
                    }
                    if (j > 0) {
                        if (board[i][j - 1].getColor().equals(board[i][j].getColor())) {
                            hasEnd = false;
                        }
                    }
                }
            }
        }
        notifyObserver();
        return hasEnd;
    }

    /**
     * Method that copy the current board.
     * @return the copy
     */
    public ColoredBall[][] getCopyBoard() {
        ColoredBall[][] copyBoard = new ColoredBall[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                copyBoard[i][j] = new ColoredBall(board[i][j].getY(), board[i][j].getX(), board[i][j].getColor());
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
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].getColor() == ColorEnum.NONE) {
                    nbColorDelete++;
                }
            }
        }
        return nbColorDelete;
    }

    @Override
    public void addObserver(Observer observer) {
        if (observer == null) {
            throw new IllegalArgumentException("a observer can't be null");
        }
        if (!Board.observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observer == null) {
            throw new IllegalArgumentException("a observer can't be null");
        }
        if (!Board.observers.contains(observer)) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObserver() {
        observers.forEach((observer) -> {
            observer.update(this.board, this.score);
        });
    }


    /**
     * Method that
     * @return the height of the board
     */
    public int getHeight() {
        return height;
    }
    /**
     * Method that
     * @return the board
     */
    public ColoredBall[][] getBoard() {
        return board;
    }
    /**
     * Method that
     * @return the width of de board
     */
    public int getWidth() {
        return width;
    }
    /**
     * Method that
     * @return the score of the game
     */
    public int getScore() {
        return score.getScore();
    }

    /**
     * Method that replace
     * @param Board with Board
     */
    public void setBoard(ColoredBall[][] Board) {
        this.board = Board;
    }
    /**
     * Method that replace
     * @param score with score
     */
    public void setScore(int score) {
        this.score.setScore(score);
    }

    public int getCountScore() {
        return score.getCountScore();
    }

    public ArrayList<BallColor> getListBall() {
        return listBall;
    }
}


