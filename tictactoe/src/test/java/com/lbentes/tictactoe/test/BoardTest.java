package com.lbentes.tictactoe.test;

import static org.junit.Assert.*;
import org.junit.Test;

import com.lbentes.tictactoe.exceptions.PositionAlreadyFilledException;
import com.lbentes.tictactoe.model.IBoard;
import com.lbentes.tictactoe.model.impl.Board;

/**
 * Test cases for {@link Board} class
 * 
 * @author lbentes
 *
 */
public class BoardTest {

    @Test
    public void CreateNewBoardShouldBeEmpty() {
        IBoard board = new Board(4);
        assertEquals(true, board.isGameBoardEmpty());
    }

    @Test
    public void BoardShouldBeFull() throws PositionAlreadyFilledException {
        IBoard board = new Board(3);
        board.writeToPosition(0, 0, "X");
        board.writeToPosition(0, 1, "X");
        board.writeToPosition(0, 2, "X");
        board.writeToPosition(1, 0, "X");
        board.writeToPosition(1, 1, "X");
        board.writeToPosition(1, 2, "X");
        board.writeToPosition(2, 0, "X");
        board.writeToPosition(2, 1, "X");
        board.writeToPosition(2, 2, "X");
        assertEquals(false, board.isGameBoardEmpty());
        assertEquals(true, board.isGameBoardFull());
    }

    @Test
    public void BoardShouldBeNotEmptyAndNotFull() throws PositionAlreadyFilledException {
        IBoard board = new Board(3);
        board.writeToPosition(0, 0, "X");
        board.writeToPosition(0, 1, "X");

        assertEquals(false, board.isGameBoardEmpty());
        assertEquals(false, board.isGameBoardFull());
    }

    @Test
    public void BoardCheckEmptyIndexes() throws PositionAlreadyFilledException {
        IBoard board = new Board(3);
        int boardDim = board.getGameBoard().length * board.getGameBoard().length;

        assertEquals(boardDim, board.getEmptyIndexes().size());

        board.writeToPosition(0, 0, "X");
        board.writeToPosition(0, 1, "X");

        assertEquals(boardDim - 2, board.getEmptyIndexes().size());
    }

    @Test
    public void CreateNewBoardShouldHaveCorrectDimension() {
        int dim = 4;
        IBoard board = new Board(dim);
        assertEquals(dim, board.getGameBoard().length);
    }

    @Test
    public void BoardShouldNotBeEmpty() throws PositionAlreadyFilledException {
        IBoard board = new Board(4);
        board.writeToPosition(0, 0, "X");
        assertEquals(false, board.isGameBoardEmpty());
    }

    @Test(expected = PositionAlreadyFilledException.class)
    public void BoardShouldThrowsAlreadyFilled() throws PositionAlreadyFilledException {
        IBoard board = new Board(4);
        board.writeToPosition(0, 0, "X");
        board.writeToPosition(0, 0, "O");// Exception point
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void BoardShouldThrowsOutOfBounds() throws PositionAlreadyFilledException {
        int dim = 4;
        IBoard board = new Board(dim);
        board.writeToPosition(0, 0, "X");
        board.writeToPosition(dim, dim, "O");// Exception point
    }

}
