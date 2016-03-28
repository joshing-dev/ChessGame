package maingame.model;

import gvprojects.chess.model.IChessModel;
import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

import java.util.ArrayList;


/*********************************************************************
 * Created by Cameron Sprowls and Josh Eldridge on 3/20/2016.
 *********************************************************************/
public class ChessModel implements IChessModel {

    private int numRows, numColumns;
    private Player currentPlayer;
    private IChessPiece[][] board;
    private ArrayList<Attacker> attackerCoords;
    private int[] kingCoords;

    private class Attacker
    {
        private int x, y;

        private Attacker(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        private int getX()
        {
            return this.x;
        }

        private int getY()
        {
            return this.y;
        }
    }

    public ChessModel()
    {
        numRows = 8;
        numColumns = 8;
        currentPlayer = Player.WHITE;
        board = new IChessPiece[numRows][numColumns];
        board[0][0] = new Rook(Player.WHITE);
        board[0][1] = new Knight(Player.WHITE);
        board[0][2] = new Bishop(Player.WHITE);
        board[0][3] = new Queen(Player.WHITE);
        board[0][4] = new King(Player.WHITE);
        board[0][5] = new Bishop(Player.WHITE);
        board[0][6] = new Knight(Player.WHITE);
        board[0][7] = new Rook(Player.WHITE);
        for(int i = 0; i <= 7; i++)
        {
            board[1][i] = new Pawn(Player.WHITE);
            board[6][i] = new Pawn(Player.BLACK);
        }
        board[7][0] = new Rook(Player.BLACK);
        board[7][1] = new Knight(Player.BLACK);
        board[7][2] = new Bishop(Player.BLACK);
        board[7][3] = new Queen(Player.BLACK);
        board[7][4] = new King(Player.BLACK);
        board[7][5] = new Bishop(Player.BLACK);
        board[7][6] = new Knight(Player.BLACK);
        board[7][7] = new Rook(Player.BLACK);

    }

    public ChessModel (int[] positions, IChessPiece[] pieces){
        numRows = 8;
        numColumns = 8;
        currentPlayer = Player.WHITE;
        board = new IChessPiece[numRows][numColumns];
        int counter = 0;
        for(IChessPiece i : pieces){
            board[positions[counter]][positions[counter+1]] = i;
            counter += 2;
        }
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
    public boolean isValidMove(Move move) throws IndexOutOfBoundsException {
        if((board[move.fromRow][move.fromColumn].player() == this.currentPlayer() &&
                board[move.fromRow][move.fromColumn].isValidMove(move, board)) &&
                !stillInCheck(move))
        return true;
        else
        return false;
    }

    @Override
    public void move(Move move) throws IllegalArgumentException, IllegalStateException {
        if(isValidMove(move))
        {
            board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
            board[move.fromRow][move.fromColumn] = null;
            if(currentPlayer() == Player.WHITE)
                currentPlayer = Player.BLACK;
            else
                currentPlayer = Player.WHITE;
        }
        else
            throw new IllegalStateException();
    }

    @Override
    public boolean isComplete()
    {
        if(inCheck())
        {
            boolean checkmate = true;
            int kingRow = kingCoords[0];
            int kingCol = kingCoords[1];
            if(!(stillInCheck(new Move(kingRow, kingCol, kingRow - 1, kingCol -1)) ||
            stillInCheck(new Move(kingRow, kingCol, kingRow - 1, kingCol)) ||
            stillInCheck(new Move(kingRow, kingCol, kingRow - 1, kingCol +1)) ||
            stillInCheck(new Move(kingRow, kingCol, kingRow, kingCol -1)) ||
            stillInCheck(new Move(kingRow, kingCol, kingRow, kingCol + 1)) ||
            stillInCheck(new Move(kingRow, kingCol, kingRow + 1, kingCol -1)) ||
            stillInCheck(new Move(kingRow, kingCol, kingRow + 1, kingCol)) ||
            stillInCheck(new Move(kingRow, kingCol, kingRow + 1, kingCol +1))))
            {
                checkmate = false;
                return checkmate;
                // this is a test commit
            }
                for(int x = 0; x < numRows(); x++)
                {
                    for (int y = 0; y < numColumns(); y++)
                    {
                        if (board[x][y] != null)
                        {
                            //this 100% NEEDS to be here
                            if (board[x][y].player() == currentPlayer)
                            {
                                for(Attacker a : attackerCoords)
                                {
                                    if(board[x][y].isValidMove(new Move(x, y, a.getX(),a.getY()), board))
                                    {
                                        checkmate = false;
                                        return checkmate;
                                    }
                                }

                            }
                        }
                    }
                }

            return checkmate;
        }
        else return false;

         /*
        Call in check like we always do, if in check get the attackers coordinates
        First see if king can move one in any direction and check stillInCheck with the moves again
        then check to see if any piece can take the attacker piece, using the coordinates
        TODO: finally check to see if any piece can move in between the king and attacker, sigh i don't want to do this one

        Any of these will return false for checkmate if they return true, order probably doesn't matter
         */
    }

    @Override
    public boolean inCheck()
    {
        int kingx = 0;
        int kingy = 0;
        //search for the king
        for(int x = 0; x < numRows(); x++){
            for(int y = 0; y < numColumns(); y++){
                if(board[x][y] instanceof King && (board[x][y].player() == currentPlayer())){
                    kingx = x;
                    kingy = y;
                    kingCoords= new int[]{x, y};
                }
            }
        }

        for(int x = 0; x < numRows(); x++)
        {
            for(int y = 0; y < numColumns(); y++)
            {
                if(board[x][y] != null)
                {
                    attackerCoords = new ArrayList<>();
                    if(board[x][y].isValidMove(new Move(x,y,kingx,kingy), board))
                    {
                        System.out.println("inCheck() returning true");
                        attackerCoords.add(new Attacker(x,y));
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean stillInCheck(Move m)
    {

        if(board[m.fromRow][m.fromColumn].isValidMove(m, board))
        {
            IChessPiece tempFrom = board[m.fromRow][m.fromColumn];
            IChessPiece tempTo =  board[m.toRow][m.toColumn];

            board[m.toRow][m.toColumn] = board[m.fromRow][m.fromColumn];
            board[m.fromRow][m.fromColumn] = null;
            if(inCheck())
            {
                board[m.toRow][m.toColumn] = tempTo;
                board[m.fromRow][m.fromColumn] = tempFrom;
                System.out.println("stillInCheck() Returning True");
                return true;
            }
            else
            {
                board[m.toRow][m.toColumn] = tempTo;
                board[m.fromRow][m.fromColumn] = tempFrom;
                System.out.println("stillInCheck() Returning false");
                System.out.println(m.fromRow + " " + m.fromColumn + " " + m.toRow + " " + m.toColumn);
                return false;
            }
        }
        return false;
    }

    @Override
    public Player currentPlayer() {
        return currentPlayer;
    }
}
