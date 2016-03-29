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
            if(!stillInCheck(new Move(kingRow, kingCol, kingRow - 1, kingCol -1)) ||
            !stillInCheck(new Move(kingRow, kingCol, kingRow - 1, kingCol)) ||
            !stillInCheck(new Move(kingRow, kingCol, kingRow - 1, kingCol +1)) ||
            !stillInCheck(new Move(kingRow, kingCol, kingRow, kingCol -1)) ||
            !stillInCheck(new Move(kingRow, kingCol, kingRow, kingCol + 1)) ||
            !stillInCheck(new Move(kingRow, kingCol, kingRow + 1, kingCol -1)) ||
            !stillInCheck(new Move(kingRow, kingCol, kingRow + 1, kingCol)) ||
            !stillInCheck(new Move(kingRow, kingCol, kingRow + 1, kingCol +1)))
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


            /*
            So get the path between king coords and attacker(s) coords, and make sure you arent trying to move onto the
            king and onto the attacker, only between the two
            So we need to get a diagonal, vertical, or horizontal path, i'm thinking something like the logic from bishop and rook
            for determining the path
             */
            ArrayList<Integer> something = new ArrayList<>();
            for(int x = 0; x < numRows(); x++) {
                for (int y = 0; y < numColumns(); y++) {
                    if (board[x][y] != null) {
                        if (board[x][y].player() == currentPlayer) {
                            for (Attacker a : attackerCoords) {
                                //I need to try to move every piece between the two pieces
                                //if there can be a piece in the way, checkmate should be false
                                if(board[a.getX()][a.getY()] instanceof Pawn ||
                                        board[a.getX()][a.getY()] instanceof Knight ||
                                        board[a.getX()][a.getY()] instanceof King ||
                                        board[x][y] instanceof  King){
                                    //#doNothing
                                    continue;
                                }else {
                                    int rowDiff = a.getX() - kingRow;
                                    int colDiff = a.getY() - kingCol;
                                    while (rowDiff != 0 && colDiff != 0) {
                                        //a bishop, so add the spaces


                                        //rook or queen
                                        if (rowDiff == 0) {
                                            if (board[x][y].isValidMove(new Move(x, y, kingRow + rowDiff, kingCol + colDiff), board)) {
                                                checkmate = false;
                                            }
                                            if (colDiff < 0) {
                                                colDiff+=1;
                                            } else {
                                                colDiff-=1;
                                            }
                                            continue;
                                        }
                                        if (colDiff == 0) {
                                            if (board[x][y].isValidMove(new Move(x, y, kingRow + rowDiff, kingCol + colDiff), board)) {
                                                checkmate = false;
                                            }
                                            if (rowDiff < 0) {
                                                rowDiff++;
                                            } else {
                                                rowDiff--;
                                            }
                                            continue;
                                        }
                                        if (rowDiff == colDiff) {
                                            if (board[x][y].isValidMove(new Move(x, y, kingRow + rowDiff, kingCol + colDiff), board)) {
                                                checkmate = false;
                                            }
                                            if (rowDiff < 0) {
                                                rowDiff++;
                                            } else {
                                                rowDiff--;
                                            }
                                            if (colDiff < 0) {
                                                colDiff++;
                                            } else {
                                                colDiff--;
                                            }
                                        }
                                    }
                                }


                                //THE SCOPE LEVEL IS OVER 9000
                                // Wat. oh okay
                            }
                        }
                    }
                }
            }




                return checkmate;
            }


        else return false;
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
        System.out.println("Does it ever hit this point?");
        return true;
    }

    @Override
    public Player currentPlayer() {
        return currentPlayer;
    }



}
