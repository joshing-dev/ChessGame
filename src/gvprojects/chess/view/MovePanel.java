package gvprojects.chess.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MovePanel extends JPanel {

   private ArrayList<String> moves;
   private JScrollPane scroll;
   private JPanel content;
   private GridBagConstraints cons;
   private int row, col;

   public MovePanel() {
      moves = new ArrayList<String>();
      content = new JPanel();
      scroll = new JScrollPane(content, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      add(scroll);
      content.setLayout(new GridBagLayout());
      cons = new GridBagConstraints();
      cons.gridx = 0;
      cons.gridy = 0;
      content.add(new JLabel("White"), cons);
      cons.gridx++;
      content.add(new JLabel("   "), cons);
      cons.gridx++;
      content.add(new JLabel("Black"), cons);
      row = 1;
      col = 0;
   }

   public void addMove(String s) {
      cons.gridx = col;
      cons.gridy = row;
      content.add(new JLabel(s), cons);
      col += 2;
      if (col > 2) {
         row++;
         col = 0;
      }
   }

}
