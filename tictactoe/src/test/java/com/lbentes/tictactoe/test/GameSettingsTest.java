package com.lbentes.tictactoe.test;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

import com.lbentes.tictactoe.bootstrap.GameSettings;
import com.lbentes.tictactoe.exceptions.GameSettingMissingOrInvalidException;

/**
 * Test cases for {@link GameSettings} class.
 * 
 * @author lbentes
 *
 */
public class GameSettingsTest {

    @Test
    public void LoadFromFileShouldLoadAll() throws IOException, GameSettingMissingOrInvalidException {
        GameSettings settings = new GameSettings();
        ClassLoader cl = getClass().getClassLoader();
        settings.loadSettings(cl.getResource(GameSettings.CONFIG_FILE_NAME).getFile());
        assertEquals(4, settings.getBoardDimension());
        assertEquals("X", settings.getPlayerOneSymbol());
        assertEquals("O", settings.getPlayerTwoSymbol());
        assertEquals("W", settings.getPlayerThreeSymbol());
    }

    @Test(expected = GameSettingMissingOrInvalidException.class)
    public void LoadFromFileMissingDimShouldThrowsException() throws IOException, GameSettingMissingOrInvalidException {
        GameSettings settings = new GameSettings();
        ClassLoader cl = getClass().getClassLoader();
        settings.loadSettings(cl.getResource("game-no-dim.properties").getFile());
    }

    @Test(expected = GameSettingMissingOrInvalidException.class)
    public void LoadFromFileInvalidDimShouldThrowsException() throws IOException, GameSettingMissingOrInvalidException {
        GameSettings settings = new GameSettings();
        ClassLoader cl = getClass().getClassLoader();
        settings.loadSettings(cl.getResource("game-invalid-dim.properties").getFile());
    }

    @Test(expected = GameSettingMissingOrInvalidException.class)
    public void LoadFromFileMissingPlayerOneSymbolShouldThrowsException()
            throws IOException, GameSettingMissingOrInvalidException {
        GameSettings settings = new GameSettings();
        ClassLoader cl = getClass().getClassLoader();
        settings.loadSettings(cl.getResource("game-no-symb-one.properties").getFile());
    }

    @Test(expected = GameSettingMissingOrInvalidException.class)
    public void LoadFromFileMissingPlayerTwoSymbolShouldThrowsException()
            throws IOException, GameSettingMissingOrInvalidException {
        GameSettings settings = new GameSettings();
        ClassLoader cl = getClass().getClassLoader();
        settings.loadSettings(cl.getResource("game-no-symb-two.properties").getFile());
    }

    @Test(expected = GameSettingMissingOrInvalidException.class)
    public void LoadFromFileMissingPlayerThreeSymbolShouldThrowsException()
            throws IOException, GameSettingMissingOrInvalidException {
        GameSettings settings = new GameSettings();
        ClassLoader cl = getClass().getClassLoader();
        settings.loadSettings(cl.getResource("game-no-symb-three.properties").getFile());
    }

}
