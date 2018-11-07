package com.lbentes.tictactoe.exceptions;

/**
 * Thrown when a plyer is added more than one time to a match.
 * 
 * @author lbentes
 *
 */
public class PlayerAlreadyOnMatchException extends Exception {

    private static final long serialVersionUID = -6152392459831932856L;

    public PlayerAlreadyOnMatchException(String name) {
        super(String.format("The player %s is already on match.", name));
    }
}
