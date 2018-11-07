package com.lbentes.tictactoe.model.impl;

import java.util.List;
import java.util.Random;

import com.lbentes.tictactoe.exceptions.BoardNotProvidedException;
import com.lbentes.tictactoe.exceptions.PositionAlreadyFilledException;
import com.lbentes.tictactoe.model.Coordinate;
import com.lbentes.tictactoe.model.IBoard;
import com.lbentes.tictactoe.model.Player;
import com.lbentes.tictactoe.view.IView;

/**
 * Very very simple AI player who just get the empty positions of the board and randomize the choice of someone.
 * 
 * @author lbentes
 *
 */
public class ComputerPlayer extends Player {


    public ComputerPlayer(String name, String symbol) {
        super(name, symbol);
    }

    public Coordinate predictMove(IBoard board) {
        List<Coordinate> freeSlots = board.getEmptyIndexes();
        if (freeSlots.size() > 1) {
            Random random = new Random();
            return freeSlots.get(random.nextInt(freeSlots.size() - 1));
        }
        return freeSlots.get(0);
    }

    @Override
    public void doNextAction(IView view, IBoard board) throws BoardNotProvidedException {
        if (board == null) {
            throw new BoardNotProvidedException();
        }
        while (true) {
            try {
                Coordinate coordinate = predictMove(board);
                view.notifyComputerPlayerMove(getName(), coordinate.getX(), coordinate.getY());
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
