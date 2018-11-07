package com.lbentes.tictactoe.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

import com.lbentes.tictactoe.bootstrap.IGameSettings;
import com.lbentes.tictactoe.controller.GameController;
import com.lbentes.tictactoe.exceptions.BoardNotProvidedException;
import com.lbentes.tictactoe.exceptions.GameSettingMissingOrInvalidException;
import com.lbentes.tictactoe.exceptions.InsufficientPlayersException;
import com.lbentes.tictactoe.exceptions.MaxPlayersAchievedException;
import com.lbentes.tictactoe.exceptions.PlayerAlreadyOnMatchException;
import com.lbentes.tictactoe.exceptions.PositionAlreadyFilledException;
import com.lbentes.tictactoe.model.Coordinate;
import com.lbentes.tictactoe.model.IBoard;
import com.lbentes.tictactoe.model.IMatch;
import com.lbentes.tictactoe.model.Player;
import com.lbentes.tictactoe.model.impl.ComputerPlayer;
import com.lbentes.tictactoe.model.impl.HumanPlayer;
import com.lbentes.tictactoe.view.IView;

public class GameControllerTest {

    class ViewMock implements IView {

        public void showOnView(String content) {
            // TODO Auto-generated method stub

        }

        public void printBoard(IBoard board) {
            // TODO Auto-generated method stub

        }

        public String requestInput() {
            return "1,1";
        }

        public void showWelcomeMessage(int boardDimension, int playerCount, String firstPlayerName) {
            // TODO Auto-generated method stub

        }

        public void notifyInvalidInput() {
            // TODO Auto-generated method stub

        }

        public Coordinate getInputCoordinates(String playerName) {
            return new Coordinate(1, 1);
        }

        public void notifyPositionAlreadyFilled() {
            // TODO Auto-generated method stub

        }

        public void notifyDraw() {
            // TODO Auto-generated method stub

        }

        public void notifyPlayerWon(String playerName) {
            // TODO Auto-generated method stub

        }

        public void notifyComputerPlayerMove(String playerName, int x, int y) {
            // TODO Auto-generated method stub

        }

    }

    class GameSettingsMock implements IGameSettings {

        public int getBoardDimension() {
            return 3;
        }

        public String getPlayerOneSymbol() {
            return "X";
        }

        public String getPlayerTwoSymbol() {
            return "O";
        }

        public String getPlayerThreeSymbol() {
            return "W";
        }

        public void loadSettings(String fileName) throws IOException, GameSettingMissingOrInvalidException {}

    }

    private String[][] emptyGameBoard = {{"", "", ""}, {"", "", ""}, {"", "", ""}};

    class BoarkMock implements IBoard {

        private boolean isBoardFull;

        public BoarkMock(String[][] gameBoard, boolean isBoardFull) {
            this.gameBoard = gameBoard;
            this.isBoardFull = isBoardFull;
        }

        String[][] gameBoard = null;

        public String[][] getGameBoard() {
            return gameBoard;
        }

        public boolean isGameBoardEmpty() {
            return false;
        }

        public boolean isGameBoardFull() {
            return isBoardFull;
        }

        public List<Coordinate> getEmptyIndexes() {
            return null;
        }

        public void writeToPosition(int x, int y, String symbol) throws PositionAlreadyFilledException {
            gameBoard[x][y] = symbol;
        }

    }


    class MatchMock implements IMatch {
        private int winnerIndex;
        private List<Player> players = new ArrayList<Player>();
        private IBoard board = null;

        public MatchMock(int winnerIndex, boolean isBoardFull) {
            this.winnerIndex = winnerIndex;
            board = new BoarkMock(emptyGameBoard, isBoardFull);
        }

        public void addPlayer(Player player) throws MaxPlayersAchievedException, PlayerAlreadyOnMatchException {
            players.add(player);
        }

        public IBoard getBoard() throws BoardNotProvidedException {
            return board;
        }

        public Player getNextPlayer() throws InsufficientPlayersException {
            return players.get(1);
        }

        public Player getInitialPlayer() {
            return players.get(0);
        }

        public Player checkWinner() {
            if (this.winnerIndex > -1)
                return players.get(this.winnerIndex);
            return null;
        }

        public int playersCount() {
            return 3;
        }

    }

    @Test
    public void TestGameControllerFinishWithWinner() throws MaxPlayersAchievedException, PlayerAlreadyOnMatchException,
            InsufficientPlayersException, BoardNotProvidedException {
        IGameSettings settings = new GameSettingsMock();
        GameController controller = new GameController(new ViewMock(), settings);
        List<Player> players = new ArrayList<Player>();
        players.add(new HumanPlayer("Player 1", settings.getPlayerOneSymbol()));
        players.add(new HumanPlayer("Player 2", settings.getPlayerTwoSymbol()));
        players.add(new ComputerPlayer("Player 3", settings.getPlayerThreeSymbol()));

        Player winner = controller.startMatch(new MatchMock(0, false), players);
        assertNotNull(winner);
        assertEquals("Player 1", winner.getName());
    }

    @Test
    public void TestGameControllerFinishWithDraw() throws MaxPlayersAchievedException, PlayerAlreadyOnMatchException,
            InsufficientPlayersException, BoardNotProvidedException {
        IGameSettings settings = new GameSettingsMock();
        GameController controller = new GameController(new ViewMock(), settings);
        List<Player> players = new ArrayList<Player>();
        players.add(new HumanPlayer("Player 1", settings.getPlayerOneSymbol()));
        players.add(new HumanPlayer("Player 2", settings.getPlayerTwoSymbol()));
        players.add(new ComputerPlayer("Player 3", settings.getPlayerThreeSymbol()));

        Player winner = controller.startMatch(new MatchMock(-1, true), players);
        assertNull(winner);
    }

}
