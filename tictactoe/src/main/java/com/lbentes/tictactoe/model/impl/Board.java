package com.lbentes.tictactoe.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.lbentes.tictactoe.exceptions.PositionAlreadyFilledException;
import com.lbentes.tictactoe.model.Coordinate;
import com.lbentes.tictactoe.model.IBoard;

/**
 * This class is the implementation of the board. This implementation is a wrapper from a matrix of String were the
 * player will write ("draw") it symbols.
 * 
 * @author lbentes
 *
 */
public final class Board implements IBoard {

    private static final String EMPTY = "";
    private String[][] gameBoard = null;
    private int filledCount = 0;

    public Board(int dimension) {
        initialize(dimension);
    }

    private void initialize(int dimension) {
        gameBoard = new String[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                gameBoard[i][j] = EMPTY;
            }
        }
    }

    public String[][] getGameBoard() {
        return gameBoard;
    }

    public boolean isGameBoardEmpty() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (!gameBoard[i][j].equals(EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void writeToPosition(int x, int y, String symbol) throws PositionAlreadyFilledException {
        int bound = gameBoard.length - 1;
        if (x > bound || y > bound) {
            throw new ArrayIndexOutOfBoundsException(String.format("Position %d,%d", x, y));
        }
        if (!gameBoard[x][y].equals(EMPTY)) {
            throw new PositionAlreadyFilledException(x, y);
        }
        gameBoard[x][y] = symbol;
        filledCount++;
    }

    public boolean isGameBoardFull() {
        return filledCount == (gameBoard.length * gameBoard.length);
    }

    public List<Coordinate> getEmptyIndexes() {
        List<Coordinate> freeSlots = new ArrayList<Coordinate>();
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[i][j].equals(EMPTY)) {
                    freeSlots.add(new Coordinate(i, j));
                }
            }
        }
        return freeSlots;
    }

}
