package com.lbentes.tictactoe.model;

import com.lbentes.tictactoe.exceptions.BoardNotProvidedException;
import com.lbentes.tictactoe.view.IView;

/**
 * Is a base player of the system. All specializations must implements the {@link #doNextAction(IView, IBoard)} that is
 * the player action over the board.
 * 
 * @author lbentes
 *
 */
public abstract class Player {

    private String name;
    private String symbol;

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        if (this.name == null)
            return super.equals(obj);
        return this.name.equals(((Player) obj).getName());
    }

    @Override
    public int hashCode() {
        if (this.name == null)
            return super.hashCode();
        return this.name.hashCode();
    }

    public abstract void doNextAction(IView view, IBoard board) throws BoardNotProvidedException;

}
