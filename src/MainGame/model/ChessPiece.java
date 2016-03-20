package MainGame.model;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/***************************************************
 * Created by Cameron Sprowls and Josh Eldridge on 3/17/2016.
 *************************************************/

public abstract class ChessPiece implements IChessPiece{
    private Player owner;

    protected ChessPiece (Player p)
    {
        owner = p;
    }

    public Player player() {
        return owner;
    }

    public boolean isValidMove (Move m, IChessPiece[][] board) throws IndexOutOfBoundsException, IllegalArgumentException
    {


        if(!(board[m.fromRow][m.fromColumn] == (this)))
        {
            throw new IllegalArgumentException();
        }

        //the two locations are the same
        if(m.fromColumn == m.toColumn && m.fromRow == m.toRow)
            return false;

        //make sure they aren't moving to a space of the same player
        if(board[m.toRow][m.toColumn] != null) {
            if (board[m.fromRow][m.fromColumn].player() == (board[m.toRow][m.toColumn].player()))
                return false;
        }

        return true;
    }

    public abstract String type();
}











