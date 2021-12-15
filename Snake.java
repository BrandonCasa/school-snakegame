import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

public class Snake implements Drawable, Entity {
  private int score;
  private int averageFrames;
  private int direction = 0;
  private int x = ((int) (25 * (Math.ceil(Math.abs((Math.random() * 200.0) / 25))))) + 125;
  private int y = ((int) (25 * (Math.ceil(Math.abs((Math.random() * 200.0) / 25))))) + 125;
  private BufferedImage img = null;

  public Snake() {
    try {
      img = ImageIO.read(getClass().getClassLoader().getResource("images/headUp.png"));
    } catch (IOException e) {
    }

    GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(false);
    System.out.println("Keyboard hook successfully started, press [escape] key to shutdown. Connected keyboards:");
    for (Entry<Long, String> keyboard : GlobalKeyboardHook.listKeyboards().entrySet()) {
      System.out.format("%d: %s\n", keyboard.getKey(), keyboard.getValue());
    }

    keyboardHook.addKeyListener(new GlobalKeyAdapter() {

      @Override
      public void keyPressed(GlobalKeyEvent event) {
        if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_RIGHT) {

        }
        if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_LEFT) {

        }
        if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_UP) {

        }
        if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_DOWN) {

        }

        if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_ESCAPE) {
          keyboardHook.shutdownHook();
          System.exit(0);
        }
      }

      @Override
      public void keyReleased(GlobalKeyEvent event) {
        System.out.println(event);
      }
    });
  }

  /**
   * From interface Drawable
   */
  public void draw(Graphics g) {
    // Draw Snake
    g.drawImage(img, x, y, 25, 25, null);
  }

  /**
   * From interface Entitiy
   */
  public void tick() {
  }

  public void second() {
  }
}