package com.lbentes.tictactoe.view;

import com.lbentes.tictactoe.model.Coordinate;
import com.lbentes.tictactoe.model.IBoard;

/**
 * View layer, abstracts the necessary behavior of a View.
 * 
 * @author lbentes
 *
 */
public interface IView {
    void showOnView(String content);

    void printBoard(IBoard board);

    String requestInput();

    void showWelcomeMessage(int boardDimension, int playerCount, String firstPlayerName);

    void notifyInvalidInput();

    Coordinate getInputCoordinates(String playerName);

    void notifyPositionAlreadyFilled();

    void notifyDraw();

    void notifyPlayerWon(String playerName);

    void notifyComputerPlayerMove(String playerName, int x, int y);
}
