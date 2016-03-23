package maingame.model;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/*************************************************************
 * Created by Cameron Sprowls && Josh Eldridge on 3/17/2016.
 *************************************************************/
public class Rook extends ChessPiece {

    public Rook(Player p)
    {
        super(p);
    }

    @Override
    public String type()
    {
        return null;
    }

    @Override
    public boolean isValidMove (Move m, IChessPiece[][] board)
    {
        if (!super.isValidMove(m, board))
            return false;
        else
        {
            //see if they aren't in the same row/col
            if((m.fromRow == m.toRow && m.fromColumn != m.toColumn) ||
                    (m.fromRow != m.toRow && m.fromColumn == m.toColumn))
                return true;

            return false;
        }
    }
}
