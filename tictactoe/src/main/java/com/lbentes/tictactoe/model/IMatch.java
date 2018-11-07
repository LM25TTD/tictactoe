package com.lbentes.tictactoe.model;

import com.lbentes.tictactoe.exceptions.InsufficientPlayersException;
import com.lbentes.tictactoe.exceptions.MaxPlayersAchievedException;
import com.lbentes.tictactoe.exceptions.PlayerAlreadyOnMatchException;
import com.lbentes.tictactoe.exceptions.BoardNotProvidedException;

/**
 * Abstracts a Match with some utility behaviors.
 * 
 * @author lbentes
 *
 */
public interface IMatch {

    void addPlayer(Player player) throws MaxPlayersAchievedException, PlayerAlreadyOnMatchException;

    IBoard getBoard() throws BoardNotProvidedException;

    Player getNextPlayer() throws InsufficientPlayersException;

    Player getInitialPlayer();

    Player checkWinner();

    int playersCount();

}
