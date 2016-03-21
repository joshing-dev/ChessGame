package MainGame.test;

import MainGame.model.Pawn;
import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/************************************************************
 * Created by Cameron Sprowls and Josh Eldridge on 3/19/2016.
 **********************************************************/
public class PawnTest extends ChessPieceTest
{
    protected IChessPiece make(Player p) {
        return new Pawn(p);
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
    public void canMoveForwardOneAtATime() throws Exception {
        board[1][1] = piece;
        assertTrue("Pawn Test 1 space -valid-", piece.isValidMove(new Move(1, 1, 2, 1), board));
        board[2][1] = piece;
        assertTrue("Pawn Test 1 space -valid-", piece.isValidMove(new Move(2, 1, 3, 1), board));
        board[3][1] = piece;
        assertTrue("Pawn Test 1 space -valid-", piece.isValidMove(new Move(3, 1, 4, 1), board));
        board[4][1] = piece;
        assertTrue("Pawn Test 1 space -valid-", piece.isValidMove(new Move(4, 1, 5, 1), board));
        board[5][1] = piece;
        assertTrue("Pawn Test 1 space -valid-", piece.isValidMove(new Move(5, 1, 6, 1), board));
        board[6][1] = piece;
        assertTrue("Pawn Test 1 space -valid-", piece.isValidMove(new Move(6, 1, 7, 1), board));
    }

    @Test
    public void canMoveForward2Spaces() throws Exception {
        board[1][1] = piece;
        assertTrue("Pawn Test 2 spaces from start -valid-", piece.isValidMove(new Move(1, 1, 3, 1), board));
        assertFalse("Pawn Test 2 spaces after two space move -invalid-", piece.isValidMove(new Move(1, 1, 5, 1), board));
    }

    @Test
    public void canMoveForwardMoreTests() throws Exception {
        board [0][5] = piece;
        assertTrue("Pawn Test 2 spaces from start-valid-", piece.isValidMove(new Move(0, 5, 2, 5), board));
        board[2][5] = piece;
        assertTrue("Pawn Test 1 space after 2 space move -valid-", piece.isValidMove(new Move(2, 5, 3, 5), board));
        board[3][5] = piece;
        assertFalse("Pawn Test 2 spaces after one space move -invalid-", piece.isValidMove(new Move(3, 5, 5, 5), board));
    }

    @Test
    public void complainsIfTryingToMoveThree() throws Exception {
        board[1][1] = piece;
        assertFalse("Pawn Test 3 spaces forward -invalid-", piece.isValidMove(new Move(1, 1, 4, 1), board));
    }

    @Test
    public void complainsIfTryingToMoveToTheSide() throws Exception {
        board[1][1] = piece;
        assertFalse("Pawn Test 3 spaces forward -invalid-", piece.isValidMove(new Move(1, 1, 1, 2), board));
        board[1][2] = piece;
        assertFalse("Pawn Test 3 spaces forward -invalid-", piece.isValidMove(new Move(1, 1, 1, 5), board));
        board[1][4] = piece;
        assertFalse("Pawn Test 3 spaces forward -invalid-", piece.isValidMove(new Move(1, 1, 1, 4), board));
        board[1][7] = piece;
        assertFalse("Pawn Test 3 spaces forward -invalid-", piece.isValidMove(new Move(1, 1, 1, 7), board));
    }

    @Test
    public void complainsIfTryingToMoveThroughPiece() throws Exception {
        //this is the same piece...
        board[1][1] = piece;
        board[1][2] = piece;
        assertFalse("Pawn Test move through piece -invalid-", piece.isValidMove(new Move(1,1,1,3), board));
    }



    //check in passing XD
    // Oh god
    // Let's not ever promote
    //check promotion
}
