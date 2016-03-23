package maingame.model;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/************************************************************
 * Created by Cameron Sprowls and Josh Eldridge on 3/19/2016.
 **********************************************************/
public class Bishop extends ChessPiece {

    public Bishop(Player p)
    {
        super(p);
    }

    @Override
    public String type() {
        return null;
    }

    @Override
    public boolean isValidMove (Move m, IChessPiece[][] board)
    {
        if (!super.isValidMove(m, board))
            return false;
        // Can only move in 4 diagonal directions
        // We need to make sure it IS a diagonal line though.. like +1/-1 on each row/col
        else
        {
            if(Math.abs(m.fromColumn - m.toColumn) == Math.abs(m.fromRow - m.toRow)){
                return true;
            }


            return false;
        }
    }
}
