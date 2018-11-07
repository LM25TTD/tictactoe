package com.lbentes.tictactoe.bootstrap;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.lbentes.tictactoe.exceptions.GameSettingMissingOrInvalidException;

/**
 * Implements the IGameSettings by reading and parsing a .properties file to generate
 * the full game settings.
 * 
 * @author lbentes
 *
 */
public class GameSettings implements IGameSettings {

    public static final String CONFIG_FILE_NAME = "game.properties";
    private static final String PROP_BOARD_DIMENSION = "board-dimension";
    private static final String PROP_PLAYER_ONE_SYMBOL = "player-1-symbol";
    private static final String PROP_PLAYER_TWO_SYMBOL = "player-2-symbol";
    private static final String PROP_PLAYER_THREE_SYMBOL = "player-3-symbol";

    private int boardDimension;
    private String playerOneSymbol;
    private String playerTwoSymbol;
    private String playerThreeSymbol;

    /*
     * (non-Javadoc)
     * 
     * @see com.lbentes.tictactoe.bootstrap.IGameSettings#getBoardDimension()
     */
    public int getBoardDimension() {
        return boardDimension;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.lbentes.tictactoe.bootstrap.IGameSettings#getPlayerOneSymbol()
     */
    public String getPlayerOneSymbol() {
        return playerOneSymbol;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.lbentes.tictactoe.bootstrap.IGameSettings#getPlayerTwoSymbol()
     */
    public String getPlayerTwoSymbol() {
        return playerTwoSymbol;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.lbentes.tictactoe.bootstrap.IGameSettings#getPlayerThreeSymbol()
     */
    public String getPlayerThreeSymbol() {
        return playerThreeSymbol;
    }

    private void settingCheck(String setting, String propertyName) throws GameSettingMissingOrInvalidException {
        if (setting == null || setting.trim().isEmpty() || setting.trim().length() > 1) {
            throw new GameSettingMissingOrInvalidException(propertyName);
        }
    }

    public void loadSettings(String fileName) throws IOException, GameSettingMissingOrInvalidException {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(fileName);
            prop.load(input);

            String dimString = prop.getProperty(PROP_BOARD_DIMENSION);
            try {
                boardDimension = Integer.valueOf(dimString);
                if (boardDimension < 3 || boardDimension > 10) {
                    throw new Exception("Board dimension must be between 3 and 10");
                }
            } catch (Exception e) {
                GameSettingMissingOrInvalidException ex =
                        new GameSettingMissingOrInvalidException(PROP_BOARD_DIMENSION);
                ex.addSuppressed(e);
                throw ex;
            }

            playerOneSymbol = prop.getProperty(PROP_PLAYER_ONE_SYMBOL);
            settingCheck(playerOneSymbol, PROP_PLAYER_ONE_SYMBOL);

            playerTwoSymbol = prop.getProperty(PROP_PLAYER_TWO_SYMBOL);
            settingCheck(playerTwoSymbol, PROP_PLAYER_TWO_SYMBOL);

            playerThreeSymbol = prop.getProperty(PROP_PLAYER_THREE_SYMBOL);
            settingCheck(playerThreeSymbol, PROP_PLAYER_THREE_SYMBOL);

        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
