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
  private BufferedImage imgUp = null;
  private BufferedImage imgRight = null;
  private BufferedImage imgDown = null;
  private BufferedImage imgLeft = null;

  public Snake() {
    try {
      imgUp = ImageIO.read(getClass().getClassLoader().getResource("images/headUp.png"));
      imgRight = ImageIO.read(getClass().getClassLoader().getResource("images/headRight.png"));
      imgDown = ImageIO.read(getClass().getClassLoader().getResource("images/headDown.png"));
      imgLeft = ImageIO.read(getClass().getClassLoader().getResource("images/headLeft.png"));
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
          direction = 1;
        }
        if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_LEFT) {
          direction = 3;
        }
        if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_UP) {
          direction = 0;
        }
        if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_DOWN) {
          direction = 2;
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
    if (direction == 0) {
      g.drawImage(imgUp, x, y, 25, 25, null);
    } else if (direction == 1) {
      g.drawImage(imgRight, x, y, 25, 25, null);
    } else if (direction == 2) {
      g.drawImage(imgDown, x, y, 25, 25, null);
    } else if (direction == 3) {
      g.drawImage(imgLeft, x, y, 25, 25, null);
    }
  }

  /**
   * From interface Entitiy
   */
  public void tick() {
  }

  public void second() {
    // Up
    if (direction == 0) {
      y -= 25;
    }
    // Down
    if (direction == 2) {
      y += 25;
    }
    // Right
    if (direction == 1) {
      x += 25;
    }
    // Left
    if (direction == 3) {
      x -= 25;
    }
  }
}