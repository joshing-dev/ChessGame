package MainGame.model;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/**
 * Created by Eldridge on 3/20/2016.
 */
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
            int rowDiff = Math.abs(m.fromRow - m.toRow);
            int colDiff = Math.abs(m.fromColumn - m.toColumn);
            // Diagonal up left
            if((m.toColumn < m.fromColumn && m.toRow < m.fromRow) &&
                    rowDiff == colDiff)
                return true;
            // Diagonal up right
            if((m.toColumn > m.fromColumn && m.toRow < m.fromRow) &&
                    rowDiff == colDiff)
                return true;
            // Diagonal down left
            if((m.toColumn < m.fromColumn && m.toRow > m.fromRow) &&
                    rowDiff == colDiff)
                return true;
            // Diagonal down right
            if((m.toColumn > m.fromColumn && m.toRow > m.fromRow) &&
                    rowDiff == colDiff)
                return true;

            return false;
        }
    }
}
