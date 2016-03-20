package MainGame.test;

import MainGame.model.Knight;
import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Cameron Sprowls and Josh Eldridge on 3/19/2016.
 */
public class KnightTest extends ChessPieceTest {
    @Override
    protected IChessPiece make(Player p) {
        return new Knight(p);
    }

    @Override
    protected Move getValidMove(int row, int col) {
        int newRow = row + 2;
        if (newRow >= board.length) {
            newRow = row - 2;
        }
        int newCol = col + 1;
        if(newCol >= board.length)
        {
            newCol = col - 1;
        }
        return new Move(row, col, newRow, newCol);
    }

    @Test
    public void canMoveUpRight() throws Exception {
        board[4][4] = piece;
        assertTrue("Rook Test 1", piece.isValidMove(new Move(4, 4, 2, 5), board));
    }

    @Test
    public void canMoveUpLeft() throws Exception {
        board[4][4] = piece;
        assertTrue("Rook Test 1", piece.isValidMove(new Move(4, 4, 2, 3), board));
    }
    @Test
    public void canMoveDownRight() throws Exception {
        board[4][4] = piece;
        assertTrue("Rook Test 1", piece.isValidMove(new Move(4, 4, 6, 5), board));
    }

    @Test
    public void canMoveDownLeft() throws Exception {
        board[4][4] = piece;
        assertTrue("Rook Test 1", piece.isValidMove(new Move(4, 4, 6, 3), board));
    }

    @Test
    public void canMoveRightUp() throws Exception {
        board[4][4] = piece;
        assertTrue("Rook Test 1", piece.isValidMove(new Move(4, 4, 3, 6), board));
    }

    @Test
    public void canMoveInRightDown() throws Exception {
        board[4][4] = piece;
        assertTrue("Rook Test 1", piece.isValidMove(new Move(4, 4, 5, 6), board));
    }

    @Test
    public void canMoveLeftUp() throws Exception {
        board[4][4] = piece;
        assertTrue("Rook Test 1", piece.isValidMove(new Move(4, 4, 3, 2), board));
    }

    @Test
    public void canMoveLeftDown() throws Exception {
        board[4][4] = piece;
        assertTrue("Rook Test 1", piece.isValidMove(new Move(4, 4, 5, 2), board));
    }

    @Test
    public void cannotMoveToRandomLocations() throws Exception {
        board[4][4] = piece;
        assertFalse("Knight Rand Move Test", piece.isValidMove(new Move(4, 4, 1, 3), board));
        assertFalse("Knight Rand Move Test", piece.isValidMove(new Move(4, 4, 5, 1), board));
        assertFalse("Knight Rand Move Test", piece.isValidMove(new Move(4, 4, 1, 2), board));
    }
}
