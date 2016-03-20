package MainGame.model;

import gvprojects.chess.model.IChessModel;
import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/**
 * Created by Eldridge on 3/20/2016.
 */
public class ChessModel implements IChessModel {

    private int numRows, numColumns;
    private Player currentPlayer;
    private IChessPiece[][] board;
    private boolean inCheck;

    public ChessModel()
    {
        numRows = 8;
        numColumns = 8;
    }
    @Override
    public int numRows() {
        return numRows;
    }

    @Override
    public int numColumns() {
        return numColumns;
    }

    @Override
    public IChessPiece pieceAt(int row, int col) {
        return board[row][col];
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public boolean isValidMove(Move move) {
        return false;
    }

    @Override
    public void move(Move move) {

    }

    @Override
    public boolean inCheck() {
        return false;
    }

    @Override
    public Player currentPlayer() {
        return null;
    }
}
