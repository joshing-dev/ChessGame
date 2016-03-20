package MainGame.model;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/**
 * Created by Eldridge on 3/20/2016.
 */
public class Queen extends ChessPiece {

    public Queen(Player p)
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
            // Can only move in 4 diagonal directions and straight lines
            // We need to make sure it IS a diagonal line though.. like +1/-1 on each row/col
            // Soooo implement both rook and bishop logic
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

            //see if they aren't in the same row/col(1,1,1,6)
            if((m.fromRow == m.toRow && m.fromColumn != m.toColumn) ||
                    (m.fromRow != m.toRow && m.fromColumn == m.toColumn))
                return true;

            return false;
        }
    }
}
