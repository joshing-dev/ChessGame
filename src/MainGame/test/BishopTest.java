package MainGame.test;

import MainGame.model.Bishop;
import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/************************************************************
 * Created by Cameron Sprowls and Josh Eldridge on 3/19/2016.
 **********************************************************/
public class BishopTest extends ChessPieceTest {
    @Override
    protected IChessPiece make(Player p) {
        return new Bishop(p);
    }

    @Override
    protected Move getValidMove(int row, int col) {
        int newRow = row + 1;
        if (newRow >= board.length) {
            newRow = row - 1;
        }
        int newCol = col + 1;
        if (newCol >= board.length) {
            newCol = newCol - 1;
        }
        return new Move(row, col, newRow, newCol);
    }

    @Test
    public void canMoveInAllDiagonalDirections() throws Exception{
        board[4][4]  = piece;
        // Up left
        assertTrue("Bishop Diagonal test", piece.isValidMove(new Move(4, 4, 3, 3), board));
        // Up right
        assertTrue("Bishop Diagonal test", piece.isValidMove(new Move(4, 4, 3, 5), board));
        // Down right
        assertTrue("Bishop Diagonal test", piece.isValidMove(new Move(4, 4, 5, 5), board));
        // Down left
        assertTrue("Bishop Diagonal test", piece.isValidMove(new Move(4, 4, 5, 3), board));
        // Test a longer diagonal
        assertTrue("Bishop Diagonal test", piece.isValidMove(new Move(4, 4, 7, 1), board));
    }

    @Test
    public void cannotMoveToRandomLocations() throws Exception{
        board[4][4] = piece;
        assertFalse("Bishop Rand Move Test", piece.isValidMove(new Move(4, 4, 1, 3), board));
        assertFalse("Bishop Rand Move Test", piece.isValidMove(new Move(4, 4, 5, 1), board));
        assertFalse("Bishop Rand Move Test", piece.isValidMove(new Move(4, 4, 1, 2), board));

    }
}
