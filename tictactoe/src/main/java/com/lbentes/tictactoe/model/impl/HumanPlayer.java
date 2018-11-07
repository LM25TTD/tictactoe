package com.lbentes.tictactoe.model.impl;

import com.lbentes.tictactoe.exceptions.BoardNotProvidedException;
import com.lbentes.tictactoe.exceptions.PositionAlreadyFilledException;
import com.lbentes.tictactoe.model.Coordinate;
import com.lbentes.tictactoe.model.IBoard;
import com.lbentes.tictactoe.model.Player;
import com.lbentes.tictactoe.view.IView;

/**
 * This is the Human player that will depends from the inputs of the user to the UI. The class will catch the position
 * and draw it symbol to the corresponding position on the board.
 * 
 * @author lbentes
 *
 */
public class HumanPlayer extends Player {


    public HumanPlayer(String name, String symbol) {
        super(name, symbol);
    }

    @Override
    public void doNextAction(IView view, IBoard board) throws BoardNotProvidedException {
        if (board == null) {
            throw new BoardNotProvidedException();
        }
        while (true) {
            try {
                Coordinate coordinate = view.getInputCoordinates(this.getName());
                board.writeToPosition(coordinate.getX(), coordinate.getY(), this.getSymbol());
                break;
            } catch (PositionAlreadyFilledException e) {
                view.notifyPositionAlreadyFilled();
            } catch (ArrayIndexOutOfBoundsException e) {
                view.notifyInvalidInput();
            }
        }
    }

}
