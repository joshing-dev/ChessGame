package maingame.test;

import maingame.model.*;
import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/************************************************************
 * Created by Cameron Sprowls and Josh Eldridge on 3/22/2016.
 ***********************************************************/
public class GameEngineTest{
    IChessPiece wp;
    IChessPiece wr;
    IChessPiece wk;
    IChessPiece wb;
    IChessPiece wq;

    IChessPiece bp;
    IChessPiece br;
    IChessPiece bk;
    IChessPiece bb;
    IChessPiece bq;
    IChessPiece[][] board;
    ChessModel model;

    @Test
    public void movePawn() throws Exception{
        wp = new Pawn(Player.WHITE);
        board = new IChessPiece[8][8];
        model = new ChessModel(new int[]{0, 3}, new IChessPiece[]{wp});
        assertTrue("Test moving pawn with the model",model.isValidMove(new Move(0,3, 1,3)));
    }

    @Test
    public void moveBlackPieceAfterWhitePiece() throws Exception{
        wp = new Pawn(Player.WHITE);
        br = new Rook(Player.BLACK);
        board = new IChessPiece[8][8];
        model = new ChessModel(new int[]{0,3, 4,4}, new IChessPiece[]{wp, br});
        //move WHITE pawn
        model.move(new Move(0,3, 1,3));

        //see if BLACK rook can now move
        assertTrue("Test moving pawn with the model",model.isValidMove(new Move(4,4, 4,5)));
    }

    @Test
    public void moveSomePiecesBackAndForth() throws Exception {
        wp = new Pawn(Player.WHITE);
        wr = new Rook(Player.WHITE);
        wb = new Bishop(Player.WHITE);

        br = new Rook(Player.BLACK);
        bk = new Knight(Player.BLACK);
        bq = new Queen(Player.BLACK);

        board = new IChessPiece[8][8];
        model = new ChessModel(new int[]{1,0,  0,0,  0,2,    6,0,  1,7,  7,3}, new IChessPiece[]{wp, wr, wb,  br, bk, bq});

        //move white, then black, then white, then black, try to take a piece as white
        model.move(new Move(1,0, 2,0)); //white's turn
        model.move(new Move(6,0, 5,0)); //black's turn
        model.move(new Move(0,0, 1,0)); //white's turn
        model.move(new Move(7,3, 7,1)); //black's turn
        //try to take queen with rook
        assertTrue("Try to take black queen with rook", model.isValidMove(new Move(1,0, 1,3)));


    }
}
