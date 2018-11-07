package com.lbentes.tictactoe.bootstrap;

import java.io.IOException;

import com.lbentes.tictactoe.exceptions.GameSettingMissingOrInvalidException;

/**
 * Wraps all game configurations to be used inside the program.
 * 
 * @author lbentes
 *
 */
public interface IGameSettings {

    int getBoardDimension();

    String getPlayerOneSymbol();

    String getPlayerTwoSymbol();

    String getPlayerThreeSymbol();

    void loadSettings(String fileName) throws IOException, GameSettingMissingOrInvalidException;

}
