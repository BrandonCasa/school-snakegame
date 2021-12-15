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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Snake implements Drawable, Entity, KeyListener {
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
  private BufferedImage imgBody = null;

  public Snake() {
    try {
      imgUp = ImageIO.read(getClass().getClassLoader().getResource("images/headUp.png"));
      imgRight = ImageIO.read(getClass().getClassLoader().getResource("images/headRight.png"));
      imgDown = ImageIO.read(getClass().getClassLoader().getResource("images/headDown.png"));
      imgLeft = ImageIO.read(getClass().getClassLoader().getResource("images/headLeft.png"));
      imgApple = ImageIO.read(getClass().getClassLoader().getResource("images/apple.png"));
      imgBody = ImageIO.read(getClass().getClassLoader().getResource("images/body.png"));
    } catch (IOException e) {
    }
  }

  /**
   * From interface Drawable
   */
  public void draw(Graphics g) {

    // Draw Body
    for (int i = 0; i < pastX.size() - 1; i++) {
      g.drawImage(imgBody, pastX.get(i), pastY.get(i), 25, 25, null);
    }

    // Draw Head
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
    // If on apple
    if (pastX.get(pastX.size() - 1) == appleX && pastY.get(pastY.size() - 1) == appleY) {
      score++;
      appleX = (int) (25 * (Math.ceil(Math.abs((Math.random() * 450.0) / 25))));
      appleY = (int) (25 * (Math.ceil(Math.abs((Math.random() * 450.0) / 25))));
    }
  }

  public void second() {
    // Up
    if (direction == 0) {
      if (pastY.get(pastY.size() - 1) >= 50) {
        if (pastX.size() > score && score != 0) {
          pastX.remove(0);
          pastY.remove(0);
        }
        if (score > 0) {
          pastX.add(pastX.get(pastX.size() - 1));
          pastY.add(pastY.get(pastY.size() - 1));
        }
        for (int i = 0; i < pastX.size() - 1; i++) {
          if (pastX.get(pastX.size() - 1) == pastX.get(i) && pastY.get(pastY.size() - 1) - 25 == pastY.get(i)) {
            System.out.println("Final Score: " + score);
            System.exit(0);
          }
        }
        pastY.set(pastY.size() - 1, pastY.get(pastY.size() - 1) - 25);
      } else {
        System.out.println("Final Score: " + score);
        System.exit(0);
      }
    }
    // Down
    if (direction == 2) {
      if (pastY.get(pastY.size() - 1) <= 425) {
        if (pastX.size() > score && score != 0) {
          pastX.remove(0);
          pastY.remove(0);
        }
        if (score > 0) {
          pastX.add(pastX.get(pastX.size() - 1));
          pastY.add(pastY.get(pastY.size() - 1));
        }
        for (int i = 0; i < pastX.size() - 1; i++) {
          if (pastX.get(pastX.size() - 1) == pastX.get(i) && pastY.get(pastY.size() - 1) + 25 == pastY.get(i)) {
            System.out.println("Final Score: " + score);
            System.exit(0);
          }
        }
        pastY.set(pastY.size() - 1, pastY.get(pastY.size() - 1) + 25);
      } else {
        System.out.println("Final Score: " + score);
        System.exit(0);
      }
    }
    // Right
    if (direction == 1) {
      if (pastX.get(pastX.size() - 1) <= 425) {
        if (pastX.size() > score && score != 0) {
          pastX.remove(0);
          pastY.remove(0);
        }
        if (score > 0) {
          pastX.add(pastX.get(pastX.size() - 1));
          pastY.add(pastY.get(pastY.size() - 1));
        }
        for (int i = 0; i < pastX.size() - 1; i++) {
          if (pastX.get(pastX.size() - 1) + 25 == pastX.get(i) && pastY.get(pastY.size() - 1) == pastY.get(i)) {
            System.out.println("Final Score: " + score);
            System.exit(0);
          }
        }
        pastX.set(pastX.size() - 1, pastX.get(pastX.size() - 1) + 25);
      } else {
        System.out.println("Final Score: " + score);
        System.exit(0);
      }
    }
    // Left
    if (direction == 3) {
      if (pastX.get(pastX.size() - 1) >= 50) {
        if (pastX.size() > score && score != 0) {
          pastX.remove(0);
          pastY.remove(0);
        }
        if (score > 0) {
          pastX.add(pastX.get(pastX.size() - 1));
          pastY.add(pastY.get(pastY.size() - 1));
        }
        for (int i = 0; i < pastX.size() - 1; i++) {
          if (pastX.get(pastX.size() - 1) - 25 == pastX.get(i) && pastY.get(pastY.size() - 1) == pastY.get(i)) {
            System.out.println("Final Score: " + score);
            System.exit(0);
          }
        }
        pastX.set(pastX.size() - 1, pastX.get(pastX.size() - 1) - 25);
      } else {
        System.out.println("Final Score: " + score);
        System.exit(0);
      }
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      if (direction != 3) {
        direction = 1;
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      if (direction != 1) {
        direction = 3;
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      if (direction != 2) {
        direction = 0;
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      if (direction != 0) {
        direction = 2;
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
      System.out.println("Final Score: " + score);
      System.exit(0);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }
}