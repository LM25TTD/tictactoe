package com.lbentes.tictactoe.exceptions;

import com.lbentes.tictactoe.model.impl.Match;

/**
 * Thrown when the match have less than {@link Match#MAX_PLAYERS}
 * 
 * @author lbentes
 *
 */
public class InsufficientPlayersException extends Exception {

    private static final long serialVersionUID = 8820552292890222606L;

    public InsufficientPlayersException(int currentPlayers, int neededPlayers) {
        super(String.format("The match contains only %d players and need %d", currentPlayers, neededPlayers));
    }
}
