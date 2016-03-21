package MainGame.test;

import MainGame.model.Rook;
import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

import org.junit.Test;

import static org.junit.Assert.*;

/*********************************************************************
 * Created by Cameron Sprowls and Josh Eldridge on 3/17/2016.
 *******************************************************************/
public class RookTest extends ChessPieceTest{
    protected IChessPiece make(Player p) {
        return new Rook(p);
    }

    @Override
    protected Move getValidMove(int row, int col) {
        int newRow = row + 1;
        if (newRow >= board.length) {
            newRow = row - 1;
        }
        return new Move(row, col, newRow, col);
    }

    @Test
    public void canMoveInRightLong() throws Exception {
        board[1][1] = piece;
        assertTrue("Rook Test 1", piece.isValidMove(new Move(1, 1, 1, 6), board));
    }

    @Test
    public void canMoveInLeftLong() throws Exception {
        board[4][4] = piece;
        assertTrue("Rook Test 2", piece.isValidMove(new Move(4, 4, 4, 1), board));
    }
    @Test
    public void canMoveInUpLong() throws Exception {
        board[1][1] = piece;
        assertTrue("Rook Test 3", piece.isValidMove(new Move(1, 1, 3, 1), board));
    }

    @Test
    public void canMoveInDownLong() throws Exception {
        board[1][1] = piece;
        assertTrue("Rook Test 4", piece.isValidMove(new Move(1, 1, 0, 1), board));
    }

    @Test
    public void canMoveInRightShort() throws Exception {
        board[1][1] = piece;
        assertTrue("Rook Test 5", piece.isValidMove(new Move(1, 1, 1, 2), board));
    }

    @Test
    public void canMoveInLeftShort() throws Exception {
        board[1][1] = piece;
        assertTrue("Rook Test 6", piece.isValidMove(new Move(1, 1, 1, 0), board));
    }

    @Test
    public void canMoveInUpShort() throws Exception {
        board[1][1] = piece;
        assertTrue("Rook Test 7", piece.isValidMove(new Move(1, 1, 0, 1), board));
    }

    @Test
    public void canMoveInDownShort() throws Exception {
        board[1][1] = piece;
        assertTrue("Rook Test 8", piece.isValidMove(new Move(1, 1, 2, 1), board));
    }

    @Test
    public void cantMoveDiagonal() throws Exception {
        board[4][4] = piece;
        assertFalse("Rook Test 9", piece.isValidMove(new Move(4, 4, 3, 3), board));
        assertFalse("Rook Test 9", piece.isValidMove(new Move(4, 4, 5, 5), board));
        assertFalse("Rook Test 9", piece.isValidMove(new Move(4, 4, 5, 3), board));
        assertFalse("Rook Test 9", piece.isValidMove(new Move(4, 4, 3, 5), board));
    }

    @Test
    public void complainsIfTryingToMoveThroughPiece() throws Exception {
        board[1][1] = piece;
        board[1][4] = piece;
        assertFalse("Pawn Test move through piece -invalid-", piece.isValidMove(new Move(1,1,1,6), board));
    }
}
