package com.lbentes.tictactoe.controller;

import java.util.List;

import com.lbentes.tictactoe.bootstrap.IGameSettings;
import com.lbentes.tictactoe.exceptions.BoardNotProvidedException;
import com.lbentes.tictactoe.exceptions.InsufficientPlayersException;
import com.lbentes.tictactoe.exceptions.MaxPlayersAchievedException;
import com.lbentes.tictactoe.exceptions.PlayerAlreadyOnMatchException;
import com.lbentes.tictactoe.model.IMatch;
import com.lbentes.tictactoe.model.Player;
import com.lbentes.tictactoe.view.IView;

/**
 * Centralize the game state and execution verifying if it can be finished with a winner or with draw. Contains the main
 * loop of the game.
 * 
 * @author lbentes
 *
 */
public class GameController {

    private IView view;
    private IGameSettings settings;
    private IMatch match;
    private Player winner = null;

    public GameController(IView view, IGameSettings settings) {
        this.view = view;
        this.settings = settings;
    }

    private boolean gameFinished() throws BoardNotProvidedException {
        winner = match.checkWinner();
        return match.getBoard().isGameBoardFull() || winner != null;
    }

    public Player startMatch(IMatch newMatch, List<Player> players) throws MaxPlayersAchievedException,
            PlayerAlreadyOnMatchException, InsufficientPlayersException, BoardNotProvidedException {
        match = newMatch;

        for (Player player : players) {
            match.addPlayer(player);
        }

        Player initial = match.getInitialPlayer();
        view.showWelcomeMessage(settings.getBoardDimension(), match.playersCount(), initial.getName());
        initial.doNextAction(view, match.getBoard());
        view.printBoard(match.getBoard());

        do {
            Player player = match.getNextPlayer();
            player.doNextAction(view, match.getBoard());
            view.printBoard(match.getBoard());
        } while (!gameFinished());

        if (winner == null) {
            view.notifyDraw();
        } else {
            view.notifyPlayerWon(winner.getName());
        }
        return winner;
    }

}
