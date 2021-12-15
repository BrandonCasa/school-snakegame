import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

public class Snake implements Drawable, Entity {
  private int score = 0;
  private int direction = 0;
  private int appleX = (int) (25 * (Math.ceil(Math.abs((Math.random() * 450.0) / 25))));
  private int appleY = (int) (25 * (Math.ceil(Math.abs((Math.random() * 450.0) / 25))));
  private ArrayList<Integer> pastX = new ArrayList<Integer>(
      Arrays.asList(((int) (25 * (Math.ceil(Math.abs((Math.random() * 200.0) / 25))))) + 125));
  private ArrayList<Integer> pastY = new ArrayList<Integer>(
      Arrays.asList(((int) (25 * (Math.ceil(Math.abs((Math.random() * 200.0) / 25))))) + 125));
  private BufferedImage imgUp = null;
  private BufferedImage imgRight = null;
  private BufferedImage imgDown = null;
  private BufferedImage imgLeft = null;
  private BufferedImage imgApple = null;

  public Snake() {
    try {
      imgUp = ImageIO.read(getClass().getClassLoader().getResource("images/headUp.png"));
      imgRight = ImageIO.read(getClass().getClassLoader().getResource("images/headRight.png"));
      imgDown = ImageIO.read(getClass().getClassLoader().getResource("images/headDown.png"));
      imgLeft = ImageIO.read(getClass().getClassLoader().getResource("images/headLeft.png"));
      imgApple = ImageIO.read(getClass().getClassLoader().getResource("images/apple.png"));
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
        // System.out.println(event);
      }
    });
  }

  /**
   * From interface Drawable
   */
  public void draw(Graphics g) {
    // Draw Snake
    if (direction == 0) {
      g.drawImage(imgUp, pastX.get(pastX.size() - 1), pastY.get(pastY.size() - 1), 25, 25, null);
    } else if (direction == 1) {
      g.drawImage(imgRight, pastX.get(pastX.size() - 1), pastY.get(pastY.size() - 1), 25, 25, null);
    } else if (direction == 2) {
      g.drawImage(imgDown, pastX.get(pastX.size() - 1), pastY.get(pastY.size() - 1), 25, 25, null);
    } else if (direction == 3) {
      g.drawImage(imgLeft, pastX.get(pastX.size() - 1), pastY.get(pastY.size() - 1), 25, 25, null);
    }

    // Draw Apple
    g.drawImage(imgApple, appleX, appleY, 25, 25, null);

    // Draw Score
    g.drawString("Score: " + score, 380, 492);
  }

  /**
   * From interface Entitiy
   */
  public void tick() {
    if (pastX.get(pastX.size() - 1) == appleX && pastY.get(pastY.size() - 1) == appleY) {
      score++;
      appleX = (int) (25 * (Math.ceil(Math.abs((Math.random() * 450.0) / 25))));
      appleY = (int) (25 * (Math.ceil(Math.abs((Math.random() * 450.0) / 25))));
      pastX.add(pastX.get(pastX.size() - 1));
      pastY.add(pastY.get(pastY.size() - 1));
    }
  }

  public void second() {
    // Up
    if (direction == 0 && pastY.get(pastY.size() - 1) >= 50) {
      pastY.set(pastY.size() - 1, pastY.get(pastY.size() - 1) - 25);
    }
    // Down
    if (direction == 2 && pastY.get(pastY.size() - 1) <= 425) {
      pastY.set(pastY.size() - 1, pastY.get(pastY.size() - 1) + 25);
    }
    // Right
    if (direction == 1) {
      pastX.set(pastX.size() - 1, pastX.get(pastX.size() - 1) + 25);
    }
    // Left
    if (direction == 3) {
      pastX.set(pastX.size() - 1, pastX.get(pastX.size() - 1) - 25);
    }
  }
}