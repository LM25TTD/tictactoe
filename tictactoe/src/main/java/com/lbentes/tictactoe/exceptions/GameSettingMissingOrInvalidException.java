package com.lbentes.tictactoe.exceptions;

/**
 * Thrown when some game setting is not properly obtained.
 * 
 * @author lbentes
 *
 */
public class GameSettingMissingOrInvalidException extends Exception {

    private static final long serialVersionUID = -8895097832031687717L;

    public GameSettingMissingOrInvalidException(String property) {
        super(String.format("The game configuration %s is missing or invalid", property));
    }
}
