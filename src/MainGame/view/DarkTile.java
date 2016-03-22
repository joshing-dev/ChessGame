package MainGame.view;

import java.awt.Color;

public class DarkTile extends Tile {

   private final static Color paint = new Color(200, 100, 50);

   @Override
   protected Color backColor() {
      return paint;
   }

}
