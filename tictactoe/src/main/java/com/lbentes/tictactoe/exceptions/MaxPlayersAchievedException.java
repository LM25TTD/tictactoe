package com.lbentes.tictactoe.exceptions;

import com.lbentes.tictactoe.model.impl.Match;

/**
 * Thrown when a player is tryied to be added in a match that haves {@link Match#MAX_PLAYERS}.
 * 
 * @author lbentes
 *
 */
public class MaxPlayersAchievedException extends Exception {

    private static final long serialVersionUID = -8895097832031687717L;

    public MaxPlayersAchievedException(int maxPlayers) {
        super(String.format("The maximum of %d players is achieved", maxPlayers));
    }
}
