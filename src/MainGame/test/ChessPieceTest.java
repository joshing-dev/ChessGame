package maingame.test;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test the {@code ChessPiece} class
 *
 * @author Zachary Kurmas & Hans Dulimarta
 */

public abstract class ChessPieceTest {

    private static final int BOARD_SIZE = 8;

    /* These three variables are accessible from your child test classes */
    protected IChessPiece[][] board;
    protected IChessPiece piece;
    protected static Random random;

    static {
        random = new Random();
    }

    @Before
    public void makeBoard() {
        // Don't put any pieces on the board.
        board = new IChessPiece[BOARD_SIZE][BOARD_SIZE];
        piece = make(Player.WHITE);
    }

    protected abstract IChessPiece make(Player p);

    protected abstract Move getValidMove(int fromRow, int fromCol);


    @Test(expected = IllegalArgumentException.class)
    public void complainsIfFromLocIsNull() throws Throwable {
        piece.isValidMove(getValidMove(0, 3), board);
    }

    @Test(expected = IllegalArgumentException.class)
    public void complainsIfFromLocIsDifferentObject() throws Throwable {
        board[1][3] = make(Player.WHITE);
        assertFalse("ChessPiece Test 2", piece.isValidMove(getValidMove(1, 3), board));
    }

    @Test
    public void complainsIfTargetOccupiedBySamePlayer() throws Throwable {
        Move move = getValidMove(2, 4);
        board[move.toRow][move.toColumn] = make(Player.WHITE);
        board[move.fromRow][move.fromColumn] = piece;
        assertFalse("ChessPiece Test 3", piece.isValidMove(move, board));
    }

    @Test
    public void canCapture() throws Throwable {
        for (int k = 0; k < 50; k++) {
            Move move = getValidMove(3, 4);
            board[move.toRow][move.toColumn] = make(piece.player().next());
            board[move.fromRow][move.fromColumn] = piece;
            assertTrue("ChessPiece Test 4", piece.isValidMove(move, board));
            board[move.fromRow][move.fromColumn] = null;
            board[move.toRow][move.toColumn] = null;
        }
    }


}