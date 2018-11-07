package com.lbentes.tictactoe.exceptions;

/**
 * Thrown when a player tries to write a already filled position on the board.
 * 
 * @author lbentes
 *
 */
public class PositionAlreadyFilledException extends Exception {

    private static final long serialVersionUID = 552717490029711724L;

    public PositionAlreadyFilledException(int x, int y) {
        super(String.format("The position %d,%d is already filled", x, y));
    }
}
