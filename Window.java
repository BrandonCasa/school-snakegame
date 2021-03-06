import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Window {

  public Window(int width, int height, String title, SnakeGame game) {
    game.setPreferredSize(new Dimension(width, height));
    game.setMaximumSize(new Dimension(width, height));
    game.setMinimumSize(new Dimension(width, height));

    JFrame frame = new JFrame(title);
    frame.add(game);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.setFocusable(true);
    frame.toFront();
    frame.requestFocus();
    System.out.println("Game Started");
  }
}