package MainGame.test;

import MainGame.model.King;
import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Doomninja on 3/19/2016.
 */
public class KingTest extends ChessPieceTest {
    protected IChessPiece make(Player p) {
        return new King(p);
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
    public void canMoveOneSpaceInAllDirections() throws Exception{
        board[4][4]  = piece;
        assertTrue("King Test 1 space up", piece.isValidMove(new Move(4, 4, 5, 4), board));
        assertTrue("King Test 1 space down", piece.isValidMove(new Move(4, 4, 3, 4), board));
        assertTrue("King Test 1 space right", piece.isValidMove(new Move(4, 4, 4, 5), board));
        assertTrue("King Test 1 space left", piece.isValidMove(new Move(4, 4, 4, 3), board));

        assertTrue("King Test 1 space up-right", piece.isValidMove(new Move(4, 4, 5, 5), board));
        assertTrue("King Test 1 space up-left", piece.isValidMove(new Move(4, 4, 5, 3), board));
        assertTrue("King Test 1 space down-right", piece.isValidMove(new Move(4, 4, 3, 5), board));
        assertTrue("King Test 1 space down-left", piece.isValidMove(new Move(4, 4, 3, 3), board));
    }

    @Test
    public void cannotMoveOtherThanRightNextToItself() throws Exception{
        assertFalse("King Test 1 space up", piece.isValidMove(new Move(4, 4, 5, 4), board));
    }
}
