package maingame.view;

import java.awt.Color;

public class WhiteTile extends Tile {

   private final static Color paint = new Color(240, 240, 160);

   @Override
   protected Color backColor() {
      return paint;
   }

}
