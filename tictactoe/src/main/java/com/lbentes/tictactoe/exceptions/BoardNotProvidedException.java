package com.lbentes.tictactoe.exceptions;

/**
 * Throw when a board instance is not provided.
 * 
 * @author lbentes
 *
 */
public class BoardNotProvidedException extends Exception {

    private static final long serialVersionUID = 4643908528827981809L;

    public BoardNotProvidedException() {
        super("Board not provided for this match!");
    }
}
