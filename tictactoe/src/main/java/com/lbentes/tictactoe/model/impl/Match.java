package com.lbentes.tictactoe.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.lbentes.tictactoe.exceptions.InsufficientPlayersException;
import com.lbentes.tictactoe.exceptions.MaxPlayersAchievedException;
import com.lbentes.tictactoe.exceptions.PlayerAlreadyOnMatchException;
import com.lbentes.tictactoe.exceptions.BoardNotProvidedException;
import com.lbentes.tictactoe.model.IMatch;
import com.lbentes.tictactoe.model.IBoard;
import com.lbentes.tictactoe.model.Player;

/**
 * The implementation of the Match. Basically groups all entities involved in a game match and provides a utility to
 * check if we have a winner or the match should be finished.
 * 
 * @author lbentes
 *
 */
public class Match implements IMatch {

    public static final int MAX_PLAYERS = 3;
    private List<Player> players = new ArrayList<Player>();
    private IBoard board = null;
    private int currentPlayer = 0;

    public Match(IBoard board) {
        this.board = board;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.lbentes.tictactoe.model.impl.IMatch#addPlayer(com.lbentes.tictactoe.model.Player)
     */
    public void addPlayer(Player player) throws MaxPlayersAchievedException, PlayerAlreadyOnMatchException {
        if (players.size() == MAX_PLAYERS) {
            throw new MaxPlayersAchievedException(MAX_PLAYERS);
        }
        if (players.contains(player)) {
            throw new PlayerAlreadyOnMatchException(player.getName());
        }
        players.add(player);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.lbentes.tictactoe.model.impl.IMatch#getBoard()
     */
    public IBoard getBoard() throws BoardNotProvidedException {
        if (board == null) {
            throw new BoardNotProvidedException();
        }
        return board;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.lbentes.tictactoe.model.impl.IMatch#getNextPlayer()
     */
    public Player getNextPlayer() throws InsufficientPlayersException {
        if (players.size() < MAX_PLAYERS) {
            throw new InsufficientPlayersException(players.size(), MAX_PLAYERS);
        }
        currentPlayer = (currentPlayer + 1) % (MAX_PLAYERS);
        return players.get(currentPlayer);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.lbentes.tictactoe.model.impl.IMatch#getInitialPlayer()
     */
    public Player getInitialPlayer() {
        Random random = new Random();
        currentPlayer = random.nextInt(MAX_PLAYERS);
        return players.get(currentPlayer);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.lbentes.tictactoe.model.impl.IMatch#playersCount()
     */
    public int playersCount() {
        return players.size();
    }

    private boolean hasFullRowOrColumn(String symbol) {
        int dimension = board.getGameBoard().length;
        int i = 0;
        int j = 0;
        for (i = 0; i < dimension; i++) {
            for (j = 0; j < dimension; j++) {
                if (!board.getGameBoard()[i][j].equals(symbol) && !board.getGameBoard()[j][i].equals(symbol)) {
                    break;
                }
            }
            if (j == dimension) {
                return true;
            }
        }
        return false;
    }

    private boolean hasFullDiagonal(String symbol) {
        int dimension = board.getGameBoard().length;
        for (int i = 0; i < dimension; i++) {
            if (!board.getGameBoard()[i][i].equals(symbol)
                    && !board.getGameBoard()[dimension - 1 - i][i].equals(symbol)) {
                return false;
            }
        }
        return true;
    }

    public Player checkWinner() {
        for (Player player : players) {
            if (hasFullRowOrColumn(player.getSymbol()) || hasFullDiagonal(player.getSymbol())) {
                return player;
            }
        }
        return null;
    }

}
