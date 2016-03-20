package MainGame.test;

import MainGame.model.Queen;
import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Eldridge on 3/20/2016.
 */
public class QueenTest extends ChessPieceTest {
    @Override
    protected IChessPiece make(Player p) {
        return new Queen(p);
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
    public void canMoveInAllDiagonalDirections() throws Exception {
        board[4][4] = piece;
        // Up left
        assertTrue("Queen Diagonal test", piece.isValidMove(new Move(4, 4, 3, 3), board));
        // Up right
        assertTrue("Queen Diagonal test", piece.isValidMove(new Move(4, 4, 3, 5), board));
        // Down right
        assertTrue("Queen Diagonal test", piece.isValidMove(new Move(4, 4, 5, 5), board));
        // Down left
        assertTrue("Queen Diagonal test", piece.isValidMove(new Move(4, 4, 5, 3), board));
        // Test a longer diagonal
        assertTrue("Queen Diagonal test", piece.isValidMove(new Move(4, 4, 7, 1), board));
    }

    @Test
    public void cannotMoveToRandomLocations() throws Exception {
        board[4][4] = piece;
        assertFalse("Queen Rand Move Test", piece.isValidMove(new Move(4, 4, 1, 3), board));
        assertFalse("Queen Rand Move Test", piece.isValidMove(new Move(4, 4, 5, 1), board));
        assertFalse("Queen Rand Move Test", piece.isValidMove(new Move(4, 4, 1, 2), board));
    }

    @Test
    public void canMoveInRightLong() throws Exception {
        board[1][1] = piece;
        assertTrue("Queen Test 1", piece.isValidMove(new Move(1, 1, 1, 6), board));
    }

    @Test
    public void canMoveInLeftLong() throws Exception {
        board[4][4] = piece;
        assertTrue("Queen Test 2", piece.isValidMove(new Move(4, 4, 4, 1), board));
    }
    @Test
    public void canMoveInUpLong() throws Exception {
        board[1][1] = piece;
        assertTrue("Queen Test 3", piece.isValidMove(new Move(1, 1, 3, 1), board));
    }

    @Test
    public void canMoveInDownLong() throws Exception {
        board[1][1] = piece;
        assertTrue("Queen Test 4", piece.isValidMove(new Move(1, 1, 0, 1), board));
    }

    @Test
    public void canMoveInRightShort() throws Exception {
        board[1][1] = piece;
        assertTrue("Queen Test 5", piece.isValidMove(new Move(1, 1, 1, 2), board));
    }

    @Test
    public void canMoveInLeftShort() throws Exception {
        board[1][1] = piece;
        assertTrue("Queen Test 6", piece.isValidMove(new Move(1, 1, 1, 0), board));
    }

    @Test
    public void canMoveInUpShort() throws Exception {
        board[1][1] = piece;
        assertTrue("Queen Test 7", piece.isValidMove(new Move(1, 1, 0, 1), board));
    }

    @Test
    public void canMoveInDownShort() throws Exception {
        board[1][1] = piece;
        assertTrue("Queen Test 8", piece.isValidMove(new Move(1, 1, 2, 1), board));
    }
}
