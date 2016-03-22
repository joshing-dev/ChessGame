package MainGame.view;

import MainGame.presenter.ChessListener;

public interface IView {
   void movePiece(int r1, int c1, int r2, int c2);

   void setChessMoveListener(ChessListener listener);

   void showStatusMessage(String msg);

   void showErrorMessage(String msg);

   void addMoveRecord(String str);
}
