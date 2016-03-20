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

    public int valueOf(IChessPiece p)
    {
        if (p instanceof Pawn)
            return 1; /* Pawn is worth 1 point */
        else if (p instanceof Queen)
            return 9; /* queen is the most valuable */
        else if (p instanceof  King)
        {
            return 9000;
        }
        else if(p instanceof Bishop)
        {
            return 1337;
        }
        else if(p instanceof Rook)
        {
            return 555555;
        }
        else if(p instanceof Knight)
        {
            return 12345;
        }
        else return 0;

    }

    public boolean isValidMove (Move m, IChessPiece[][] board) throws IndexOutOfBoundsException, IllegalArgumentException
    {

        // Add a rule to see if its a knight and if not check to see if the piece is moving through another one
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











