package com.lbentes.tictactoe.model;

import java.util.List;

import com.lbentes.tictactoe.exceptions.PositionAlreadyFilledException;

/**
 * Abstracts a game board with some utility behaviors.
 * 
 * @author lbentes
 *
 */
public interface IBoard {

    String[][] getGameBoard();

    boolean isGameBoardEmpty();

    boolean isGameBoardFull();

    List<Coordinate> getEmptyIndexes();

    void writeToPosition(int x, int y, String symbol) throws PositionAlreadyFilledException;

}
