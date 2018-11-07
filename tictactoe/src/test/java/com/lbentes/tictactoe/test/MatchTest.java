package com.lbentes.tictactoe.test;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

import com.lbentes.tictactoe.exceptions.BoardNotProvidedException;
import com.lbentes.tictactoe.exceptions.InsufficientPlayersException;
import com.lbentes.tictactoe.exceptions.MaxPlayersAchievedException;
import com.lbentes.tictactoe.exceptions.PlayerAlreadyOnMatchException;
import com.lbentes.tictactoe.exceptions.PositionAlreadyFilledException;
import com.lbentes.tictactoe.model.Coordinate;
import com.lbentes.tictactoe.model.IBoard;
import com.lbentes.tictactoe.model.Player;
import com.lbentes.tictactoe.model.impl.HumanPlayer;
import com.lbentes.tictactoe.model.impl.Match;

/**
 * Test cases for {@link Match} class.
 * 
 * @author lbentes
 *
 */
public class MatchTest {

    private String[][] horizontalFilledGameBoard = {{"O", "X", "W"}, {"O", "O", "O"}, {"X", "X", "W"}};
    private String[][] verticalFilledGameBoard = {{"O", "X", "W"}, {"O", "W", "X"}, {"O", "X", "W"}};
    private String[][] primaryDiagonalFilledGameBoard = {{"O", "X", "W"}, {"W", "O", "X"}, {"X", "X", "O"}};
    private String[][] secondaryDiagonalFilledGameBoard = {{"W", "X", "O"}, {"W", "O", "X"}, {"O", "X", "W"}};
    private String[][] drawGameBoard = {{"W", "X", "O"}, {"W", "W", "X"}, {"O", "X", "O"}};

    class BoarkMock implements IBoard {

        public BoarkMock(String[][] gameBoard) {
            this.gameBoard = gameBoard;
        }

        String[][] gameBoard = null;

        public String[][] getGameBoard() {
            return gameBoard;
        }

        public boolean isGameBoardEmpty() {
            return false;
        }

        public boolean isGameBoardFull() {
            return false;
        }

        public List<Coordinate> getEmptyIndexes() {
            return null;
        }

        public void writeToPosition(int x, int y, String symbol) throws PositionAlreadyFilledException {}

    }

    @Test
    public void TestGetPlayersCountShouldReturnProperly()
            throws MaxPlayersAchievedException, PlayerAlreadyOnMatchException {
        Match match = new Match(new BoarkMock(horizontalFilledGameBoard));
        match.addPlayer(new HumanPlayer("Player 1", "X"));
        match.addPlayer(new HumanPlayer("Player 2", "O"));
        match.addPlayer(new HumanPlayer("Player 3", "W"));

        assertEquals(3, match.playersCount());
    }


    @Test
    public void TestCheckWinnerForHorizontalFullShouldReturnPlayer()
            throws MaxPlayersAchievedException, PlayerAlreadyOnMatchException {
        Match match = new Match(new BoarkMock(horizontalFilledGameBoard));
        match.addPlayer(new HumanPlayer("Player 1", "X"));
        match.addPlayer(new HumanPlayer("Player 2", "O"));
        match.addPlayer(new HumanPlayer("Player 3", "W"));

        Player winner = match.checkWinner();
        assertNotNull(winner);
        assertEquals("O", winner.getSymbol());
        assertEquals("Player 2", winner.getName());
    }

    @Test
    public void TestCheckWinnerForVerticalFullShouldReturnPlayer()
            throws MaxPlayersAchievedException, PlayerAlreadyOnMatchException {
        Match match = new Match(new BoarkMock(verticalFilledGameBoard));
        match.addPlayer(new HumanPlayer("Player 1", "X"));
        match.addPlayer(new HumanPlayer("Player 2", "O"));
        match.addPlayer(new HumanPlayer("Player 3", "W"));

        Player winner = match.checkWinner();
        assertNotNull(winner);
        assertEquals("O", winner.getSymbol());
        assertEquals("Player 2", winner.getName());
    }

    @Test
    public void TestCheckWinnerForPrimaryDiagonalFullShouldReturnPlayer()
            throws MaxPlayersAchievedException, PlayerAlreadyOnMatchException {
        Match match = new Match(new BoarkMock(primaryDiagonalFilledGameBoard));
        match.addPlayer(new HumanPlayer("Player 1", "X"));
        match.addPlayer(new HumanPlayer("Player 2", "O"));
        match.addPlayer(new HumanPlayer("Player 3", "W"));

        Player winner = match.checkWinner();
        assertNotNull(winner);
        assertEquals("O", winner.getSymbol());
        assertEquals("Player 2", winner.getName());
    }

    @Test
    public void TestCheckWinnerForSecondaryDiagonalFullShouldReturnPlayer()
            throws MaxPlayersAchievedException, PlayerAlreadyOnMatchException {
        Match match = new Match(new BoarkMock(secondaryDiagonalFilledGameBoard));
        match.addPlayer(new HumanPlayer("Player 1", "X"));
        match.addPlayer(new HumanPlayer("Player 2", "O"));
        match.addPlayer(new HumanPlayer("Player 3", "W"));

        Player winner = match.checkWinner();
        assertNotNull(winner);
        assertEquals("O", winner.getSymbol());
        assertEquals("Player 2", winner.getName());
    }

    @Test
    public void TestCheckWinnerForDrawShouldNotReturnPlayer()
            throws MaxPlayersAchievedException, PlayerAlreadyOnMatchException {
        Match match = new Match(new BoarkMock(drawGameBoard));
        match.addPlayer(new HumanPlayer("Player 1", "X"));
        match.addPlayer(new HumanPlayer("Player 2", "O"));
        match.addPlayer(new HumanPlayer("Player 3", "W"));

        Player winner = match.checkWinner();
        assertNull(winner);
    }

    @Test(expected = BoardNotProvidedException.class)
    public void TestBoardNotFilledShouldReturnException()
            throws MaxPlayersAchievedException, PlayerAlreadyOnMatchException, BoardNotProvidedException {
        Match match = new Match(null);
        match.addPlayer(new HumanPlayer("Player 1", "X"));
        match.addPlayer(new HumanPlayer("Player 2", "O"));
        match.addPlayer(new HumanPlayer("Player 3", "W"));
        match.getBoard();
    }

    @Test(expected = PlayerAlreadyOnMatchException.class)
    public void TestAddDuplicatedPlayerShouldReturnPlayerAlreadyOnMatchException()
            throws MaxPlayersAchievedException, PlayerAlreadyOnMatchException {
        Match match = new Match(new BoarkMock(drawGameBoard));
        match.addPlayer(new HumanPlayer("Player 1", "X"));
        match.addPlayer(new HumanPlayer("Player 1", "X"));
    }

    @Test(expected = MaxPlayersAchievedException.class)
    public void TestAddExcessivePlayersShouldReturnMaxPlayersAchievedException()
            throws MaxPlayersAchievedException, PlayerAlreadyOnMatchException {
        Match match = new Match(new BoarkMock(drawGameBoard));
        match.addPlayer(new HumanPlayer("Player 1", "X"));
        match.addPlayer(new HumanPlayer("Player 2", "O"));
        match.addPlayer(new HumanPlayer("Player 3", "W"));
        match.addPlayer(new HumanPlayer("Player 4", "T"));
    }


    @Test
    public void TestGetNextPlayerShouldBeCircularOnList()
            throws MaxPlayersAchievedException, PlayerAlreadyOnMatchException, InsufficientPlayersException {
        Match match = new Match(new BoarkMock(drawGameBoard));
        match.addPlayer(new HumanPlayer("Player 1", "X"));
        match.addPlayer(new HumanPlayer("Player 2", "O"));
        match.addPlayer(new HumanPlayer("Player 3", "W"));

        Player next = match.getNextPlayer();
        assertEquals("Player 2", next.getName());
        next = match.getNextPlayer();
        assertEquals("Player 3", next.getName());
        next = match.getNextPlayer();
        assertEquals("Player 1", next.getName());
    }

    @Test(expected = InsufficientPlayersException.class)
    public void TestGetNextPlayerInsufficientPlayerShouldReturnException()
            throws MaxPlayersAchievedException, PlayerAlreadyOnMatchException, InsufficientPlayersException {
        Match match = new Match(new BoarkMock(drawGameBoard));
        match.addPlayer(new HumanPlayer("Player 1", "X"));
        match.addPlayer(new HumanPlayer("Player 2", "O"));

        match.getNextPlayer();
    }
}
