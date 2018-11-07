package com.lbentes.tictactoe.bootstrap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.lbentes.tictactoe.controller.GameController;
import com.lbentes.tictactoe.exceptions.GameSettingMissingOrInvalidException;
import com.lbentes.tictactoe.exceptions.InsufficientPlayersException;
import com.lbentes.tictactoe.exceptions.MaxPlayersAchievedException;
import com.lbentes.tictactoe.exceptions.PlayerAlreadyOnMatchException;
import com.lbentes.tictactoe.exceptions.BoardNotProvidedException;
import com.lbentes.tictactoe.model.IMatch;
import com.lbentes.tictactoe.model.Player;
import com.lbentes.tictactoe.model.IBoard;
import com.lbentes.tictactoe.model.impl.Match;
import com.lbentes.tictactoe.model.impl.Board;
import com.lbentes.tictactoe.model.impl.ComputerPlayer;
import com.lbentes.tictactoe.model.impl.HumanPlayer;
import com.lbentes.tictactoe.view.IView;
import com.lbentes.tictactoe.view.impl.ConsoleView;

/**
 * This class is the entry point of the game. Here we will instantiate all necessary objects to execute the program and
 * inject it as necessary (Dependency Injection principle).
 * 
 * @author lbentes
 *
 */
public class Bootstrap {
    private static final String PLAYER_1_LABEL = "Player 1";
    private static final String PLAYER_2_LABEL = "Player 2";
    private static final String PLAYER_3_LABEL = "Player 3";


    public static void main(String[] args) {
        // Use classloader for dev environment
        // ClassLoader cl = Bootstrap.class.getClassLoader();

        // Create game settings container
        IGameSettings settings = new GameSettings();

        // Create view layer
        IView view = new ConsoleView();

        try {
            // Load game settings
            // settings.loadSettings(cl.getResource(GameSettings.CONFIG_FILE_NAME).getFile());
            settings.loadSettings(GameSettings.CONFIG_FILE_NAME);

            // Create Players
            List<Player> players = new ArrayList<Player>();
            players.add(new HumanPlayer(PLAYER_1_LABEL, settings.getPlayerOneSymbol()));
            players.add(new HumanPlayer(PLAYER_2_LABEL, settings.getPlayerTwoSymbol()));
            players.add(new ComputerPlayer(PLAYER_3_LABEL, settings.getPlayerThreeSymbol()));

            // Create game components
            IBoard board = new Board(settings.getBoardDimension());
            IMatch match = new Match(board);

            // Create game controller and start the match
            GameController controller = new GameController(view, settings);
            controller.startMatch(match, players);
        } catch (IOException e) {
            view.showOnView(e.getMessage());
        } catch (GameSettingMissingOrInvalidException e) {
            view.showOnView(e.getMessage());
        } catch (MaxPlayersAchievedException e) {
            view.showOnView(e.getMessage());
        } catch (PlayerAlreadyOnMatchException e) {
            view.showOnView(e.getMessage());
        } catch (InsufficientPlayersException e) {
            view.showOnView(e.getMessage());
        } catch (BoardNotProvidedException e) {
            view.showOnView(e.getMessage());
        }
    }

}
