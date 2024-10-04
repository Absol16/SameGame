package com.example.samegamefx.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board(5, 5);
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[0].length; j++) {
                board.getBoard()[i][j] = new ColoredBall(j, i, ColorEnum.G);
            }
        }
    }

    //====== Method Start ========
    @Test
    void startEasy() {
        Board boardV2 = new Board(4, 4);
        int cpt = 0;
        int cptG = 0;
        int cptB = 0;
        int cptY = 0;
        int cptR = 0;
        int cptP = 0;
        Difficulty level = Difficulty.EASY;
        boardV2.start(level);
        for (int i = 0; i < boardV2.getBoard().length; i++) {
            for (int j = 0; j < boardV2.getBoard()[0].length; j++) {
                if (boardV2.getBoard()[i][j].getColor().equals(ColorEnum.G) && cptG == 0) {
                    cptG++;
                    cpt++;
                } else if (boardV2.getBoard()[i][j].getColor().equals(ColorEnum.G) && cptB == 0) {
                    cptB++;
                    cpt++;
                } else if (boardV2.getBoard()[i][j].getColor().equals(ColorEnum.G) && cptY == 0) {
                    cptY++;
                    cpt++;
                } else if (boardV2.getBoard()[i][j].getColor().equals(ColorEnum.G) && cptR == 0) {
                    cptR++;
                    cpt++;
                } else if (boardV2.getBoard()[i][j].getColor().equals(ColorEnum.G) && cptP == 0) {
                    cptP++;
                    cpt++;
                }
            }
        }
        assertEquals(0, cpt);
    }

    @Test
    void
    startMedium() {
        Board boardV2 = new Board(10, 10);
        int cpt = 0;
        Difficulty level = Difficulty.MEDIUM;
        boardV2.start(level);
        for (int i = 0; i < boardV2.getBoard().length; i++) {
            for (int j = 0; j < boardV2.getBoard()[0].length; j++) {
                if (cpt == 0 && boardV2.getBoard()[i][j].getColor().equals(ColorEnum.G)){
                    cpt++;
                }
            }
            assertEquals(1, cpt);
        }
    }

    @Test
    void startHard() {
        Board boardV2 = new Board(10, 10);
        int cpt = 0;

        Difficulty level = Difficulty.HARD;
        boardV2.start(level);
        for (int i = 0; i < boardV2.getBoard().length; i++) {
            for (int j = 0; j < boardV2.getBoard()[0].length; j++) {
                if (cpt == 0 &&boardV2.getBoard()[i][j].getColor().equals(ColorEnum.P)) {
                    cpt++;
                }
            }
        }
        assertEquals(1, cpt);
    }

    //====== Method reset ========
    @Test
    void resetBoard() {
        board.reset(5, 5);
        assertEquals(board.getHeight(), 5);
        assertEquals(board.getWidth(), 5);
    }

    @Test
    void resetBoardWhenHeightLow() {
        board.reset(1, 5);
        assertEquals(board.getHeight(), 1);
        assertEquals(board.getWidth(), 5);
    }

    @Test
    void resetBoardWhenHeightHigh() {
        board.reset(100, 5);
        assertEquals(board.getHeight(), 100);
        assertEquals(board.getWidth(), 5);
    }

    //====== Method Delete ========
    @Test
    // all ball have same color, so it deletes them all.
    void deleteBall() {
        int x = 3;
        int y = 4;
        board.deleteBall(y, x);
        assertEquals(ColorEnum.NONE, board.getBoard()[1][1].getColor());
    }

    @Test
    void deleteBallOnCorner() {
        int x = 4;
        int y = 4;
        board.deleteBall(y, x);
        assertTrue(board.getNbColorDelete() != 0);
    }

    @Test
    void deleteBallOnOneRow() {
        Board boardV2;
        boardV2 = new Board(1, 5);
        for (int i = 0; i < boardV2.getBoard().length; i++) {
            for (int j = 0; j < boardV2.getBoard()[0].length; j++) {
                boardV2.getBoard()[i][j] = new ColoredBall(j, i, ColorEnum.G);
            }
        }
        int x = 4;
        int y = 0;
        boardV2.deleteBall(y, x);
        assertEquals(ColorEnum.NONE, boardV2.getBoard()[0][0].getColor());
    }

    @Test
    void deleteBallOnOneColumn() {
        Board boardV2;
        boardV2 = new Board(5, 1);
        for (int i = 0; i < boardV2.getBoard().length; i++) {
            for (int j = 0; j < boardV2.getBoard()[0].length; j++) {
                boardV2.getBoard()[i][j] = new ColoredBall(j, i, ColorEnum.G);
            }
        }
        int x = 0;
        int y = 4;
        boardV2.deleteBall(y, x);
        assertEquals(ColorEnum.NONE, boardV2.getBoard()[0][0].getColor());
    }

    @Test
    void deleteBallOnDeletedBall() {
        Board boardV2 = new Board(3, 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardV2.getBoard()[i][j] = new ColoredBall(j, i, ColorEnum.G);
                if (i == 2)
                    boardV2.getBoard()[i][j] = new ColoredBall(j, i, ColorEnum.NONE);
            }
        }
        int x = 2;
        int y = 2;
        boardV2.deleteBall(y, x);
        assertEquals(0, boardV2.getScore());
    }

    //====== Method gameIsFinish ========
    @Test
    //all ball have same color, so it deletes them all and finish the game.
    void gameIsFinishWhenDelete() {
        int x = 2;
        int y = 3;
        board.deleteBall(y, x);
        assertTrue(board.gameIsFinish());
    }

    @Test
// Board is full of ball
    void gameIsNotFinish() {
        assertFalse(board.gameIsFinish());
    }

    @Test
    void gameIsNotFinishWhenDelete() {
        Board boardV2 = new Board(5, 5);
        for (int i = 0; i < boardV2.getBoard().length; i++) {
            for (int j = 0; j < boardV2.getBoard()[0].length; j++) {
                if (i == 2 && j == 2) {
                    boardV2.getBoard()[i][j] = new ColoredBall(j, i, ColorEnum.G);
                }
                boardV2.getBoard()[i][j] = new ColoredBall(j, i, ColorEnum.G);
            }
        }
        board.deleteBall(2, 2);
        assertFalse(boardV2.gameIsFinish());
    }

    //====== Method clearList ========
    @Test
    void listClearEasy() {
        board.start(Difficulty.EASY);
        board.clearList();
        assertEquals(0, board.getListBall().size());
    }

    @Test
    void listClearHard() {
        board.start(Difficulty.HARD);
        board.clearList();
        assertEquals(0, board.getListBall().size());
    }

    @Test
    void listClearMedium() {
        board.start(Difficulty.MEDIUM);
        board.clearList();
        assertEquals(0, board.getListBall().size());
    }

    //====== Method getNbColorDelete ========
    @Test
    void getNbColorDelete_With_No_DeletedBall() {
        assertEquals(0, board.getNbColorDelete());
    }

    @Test
    void getNbColorDelete_With_DeletedBall() {
        int x = 2;
        int y = 3;
        board.deleteBall(y, x);
        assertEquals(25, board.getNbColorDelete());
    }

    @Test
    void compactBoardDown() {
        Board boardV2= new Board(5,5);
        int i;
        int j;
        for (i = 0; i < boardV2.getBoard().length; i++) {
            for (j = 0; j < boardV2.getBoard()[0].length; j++) {
                if(i==4){
                    boardV2.getBoard()[i][j] = new ColoredBall(j, i, ColorEnum.G);
                }
                else boardV2.getBoard()[i][j] = new ColoredBall(j, i, ColorEnum.P);
            }
        }
        int x = 4;
        int y = 4;
        boardV2.deleteBall(y, x);
        assertEquals(ColorEnum.NONE,boardV2.getBoard()[0][0].getColor());
    }

    @Test
    void compactColumnBoard() {
        Board boardV2= new Board(5,5);
        int i;
        int j;
        for (i = 0; i < boardV2.getBoard().length; i++) {
            for (j = 0; j < boardV2.getBoard()[0].length; j++) {
                if(j==4){
                    boardV2.getBoard()[i][j] = new ColoredBall(j, i, ColorEnum.G);
                }
                else boardV2.getBoard()[i][j] = new ColoredBall(j, i, ColorEnum.P);
            }
        }
        int x = 1;
        int y = 1;
        boardV2.deleteBall(y, x);
        assertEquals(ColorEnum.NONE,boardV2.getBoard()[3][3].getColor());
    }

    //====== Method getter and setter ========
    @Test
    void getHeightIsTrue() {
        assertEquals(5, board.getHeight());
    }

    @Test
    void getHeightIsFalse() {
        assertFalse(board.getHeight() != 5);
    }

    @Test
    void getBoard() {
        assertEquals(board.getBoard(), board.getBoard());
    }

    @Test
    void getWidthIsTrue() {
        assertEquals(5, board.getWidth());
    }

    @Test
    void getWidthIsFalse() {
        assertFalse(board.getWidth() != 5);
    }

    @Test
    void getScore_is_true() {
        board.setScore(5);
        assertEquals(5, board.getScore());
    }

    @Test
    void getScore_is_false() {
        board.setScore(5);
        assertFalse(board.getScore() != 5);
    }
}