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
        else if(p instanceof Bishop)
        {
            return 3;
        }
        else if(p instanceof Rook)
        {
            return 5;
        }
        else if(p instanceof Knight)
        {
            return 3;
        }
        else return 0;

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
        if(board[m.toRow][m.toColumn] != null) 
        {
            if (board[m.fromRow][m.fromColumn].player() == (board[m.toRow][m.toColumn].player()))
                return false;
        }
        if(!(board[m.fromRow][m.fromColumn] instanceof Knight))
        {
            return !(pieceInPath(m.fromRow, m.fromColumn, m.toRow, m.toColumn, board));
        }
        
        
        return true;
    }
    public boolean pieceInPath(int x1, int y1, int x2, int y2, IChessPiece[][] board)
    {
        int xtemp = x1, ytemp = y1;
        if (x1 < x2) {
            xtemp += 1;
        }
        if (x1 > x2) {
            xtemp -= 1;
        }
        if (y1 < y2) {
            ytemp += 1;
        }
        if (y1 > y2) {
            ytemp -= 1;
        }
        if((xtemp == x2 ) && (ytemp == y2))
            return false;
        if(board[xtemp][ytemp] != null)
        {
            return true;
        }
        else{
            if((x1 != x2 ) || (y1 != y2))
            {
                return pieceInPath(xtemp, ytemp, x2, y2, board);
            }
        }
        return false;
    } //  <--------------  8=============D  Fuck. You.

    public abstract String type();
}











