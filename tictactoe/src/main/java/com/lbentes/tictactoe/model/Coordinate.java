package com.lbentes.tictactoe.model;

/**
 * Represents a coordinate X,Y on the game board.
 * 
 * @author lbentes
 *
 */
public class Coordinate {

    private int x;
    private int y;


    public Coordinate() {

    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }



}
