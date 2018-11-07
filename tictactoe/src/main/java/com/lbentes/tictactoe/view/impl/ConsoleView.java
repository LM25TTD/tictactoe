package com.lbentes.tictactoe.view.impl;

import java.util.Scanner;

import com.lbentes.tictactoe.model.Coordinate;
import com.lbentes.tictactoe.model.IBoard;
import com.lbentes.tictactoe.view.IView;

/**
 * Implementation of the View with input and output based on system console.
 * 
 * @author lbentes
 *
 */
public class ConsoleView implements IView {

    private Scanner scan = new Scanner(System.in);

    public void showOnView(String content) {
        System.out.println(content);
    }

    public String requestInput() {
        return scan.nextLine();
    }

    public void printBoard(IBoard board) {
        System.out.println();
        int dimension = board.getGameBoard().length;
        for (int i = 0; i < dimension; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(" | ");
            for (int j = 0; j < dimension; j++) {
                String value = board.getGameBoard()[i][j];
                sb.append(value.isEmpty() ? " " : value);
                sb.append(" | ");
            }
            System.out.println(sb.toString().trim());
        }
    }

    public void showWelcomeMessage(int boardDimension, int playerCount, String firstPlayerName) {
        showOnView("---- Welcome to TicTacToe Match ----");
        showOnView(String.format("Board size is %d.", boardDimension));
        showOnView(String.format("Number of players is %d.", playerCount));
        showOnView(String.format("First player to play is %s.", firstPlayerName));
    }

    private Coordinate parseCoordinates(String input) {
        Coordinate result = new Coordinate();
        String[] coordinates = input.split(",");
        result.setX(Integer.parseInt(coordinates[0]));
        result.setY(Integer.parseInt(coordinates[1]));
        return result;
    }

    public void notifyInvalidInput() {
        showOnView("Invalid input, try again");
    }

    public Coordinate getInputCoordinates(String playerName) {
        Coordinate coordinate = null;
        showOnView(String.format("What is %s position to fill? (x,y)", playerName));
        boolean failOnAction = true;
        do {
            try {
                String input = requestInput();
                coordinate = parseCoordinates(input);
                failOnAction = false;
            } catch (Exception e) {
                notifyInvalidInput();
                failOnAction = true;
            }
        } while (failOnAction);
        return coordinate;
    }

    public void notifyPositionAlreadyFilled() {
        showOnView("This position is already filled, try again");
    }

    public void notifyDraw() {
        System.out.println();
        System.out.println();
        showOnView("---- It's a draw! ----");
        showOnView("---- Game Finished! ----");
    }

    public void notifyPlayerWon(String playerName) {
        System.out.println();
        System.out.println();
        showOnView(String.format("---- %s won the game! ----", playerName));
        showOnView("---- Game Finished! ----");
    }

    public void notifyComputerPlayerMove(String playerName, int x, int y) {
        showOnView(String.format("The AI %s filled the position (%d,%d)", playerName, x, y));
    }

}
