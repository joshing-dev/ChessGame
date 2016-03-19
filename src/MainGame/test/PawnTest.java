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
    public void canMoveForward() throws Exception {
        board[1][1] = piece;
        // -_-
        assertTrue("Pawn Test 1 space -valid-", piece.isValidMove(new Move(1, 1, 2, 1), board));
    }

    @Test
    public void canMoveForward2() throws Exception {
        board[1][1] = piece;
        assertTrue("Pawn Test 2 spaces -sometimes valid-", piece.isValidMove(new Move(1, 1, 2, 1), board));
    }

    @Test
    public void canMoveForward3() throws Exception {
        board[1][1] = piece;
        assertFalse("Pawn Test 3 spaces forward -invalid-", piece.isValidMove(new Move(1, 1, 4, 1), board));
    }

    @Test
    public void complainsIfTryingToMoveBack() throws Exception {

    }

    @Test
    public void complainsIfTryingToMoveTwoSpacesAfterFirstMove() throws Exception{

    }



    //check in passing XD

    //check promotion
}
