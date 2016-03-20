package MainGame.model;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/************************************************************
 * Created by Cameron Sprowls and Josh Eldridge on 3/19/2016.
 ***********************************************************/
public class King extends ChessPiece{
    public King(Player p)
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
            //if they are anymore then one space away from the king... fail
            if((m.toColumn > m.fromColumn+1 || m.toColumn < m.fromColumn-1) ||
                    m.toRow > m.fromRow+1 || m.toRow < m.fromRow-1)
                return false;

            return true;
        }
    }
}
