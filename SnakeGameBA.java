import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.SwingUtilities;

public class SnakeGameBA extends JFrame implements KeyListener {
  static int gridSize = 28;
  static int gridCount = 32;
  static int applesEaten = 0;
  static final int appleInitialX = (int) (Math.random() * 10.0) * gridSize;
  static final int appleInitialY = (int) (Math.random() * 10.0) * gridSize;
  static final int snakeInitialX = (int) (Math.random() * 10.0) * gridSize;
  static final int snakeInitialY = (int) (Math.random() * 10.0) * gridSize;
  static List<Map.Entry<Integer, Integer>> snakeCorners = new ArrayList<>(
      Arrays.asList(Map.entry(snakeInitialX, snakeInitialY)));
  static Map.Entry<Integer, Integer> currentAppleLocationInitial = Map.entry(appleInitialY, appleInitialX);
  static BufferedImage bodyImg = null;
  static BufferedImage headImg = null;
  static int direction = 0; // 0=Left, 1=Up, 2=Right, 3=Down
  static Timer timer = new Timer();

  public SnakeGameBA() {
    super("Lines Drawing Demo");
    addKeyListener(this);

    setSize((gridSize * gridCount) + 16, (gridSize * gridCount) + 32 + 4);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    try {
      bodyImg = ImageIO.read(getClass().getClassLoader().getResource("images/body.jpeg"));
      headImg = ImageIO.read(getClass().getClassLoader().getResource("images/head.jpeg"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

public void paint(Graphics g){
        // clears the screen
        super.paint(g);
        // render Mickey
        AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
        tx.rotate(Math.PI);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(mickeyImg, tx, null);
    }

  void drawLines(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.draw(new Line2D.Double((gridSize * gridCount) + 8, 31, (gridSize * gridCount) + 8,
        (gridSize * gridCount) + gridSize));
    g2d.draw(new Line2D.Double(0, (gridSize * gridCount) + gridSize, (gridSize * gridCount) + 8,
        (gridSize * gridCount) + gridSize));
    for (int x = 0; x < gridCount; x++) {
      for (int y = 1; y <= gridCount; y++) {
        g2d.draw(new Line2D.Double(((x * gridSize) + 8), y * gridSize, ((x * gridSize) + 8) + gridSize, y * gridSize));
        g2d.draw(new Line2D.Double(((x * gridSize) + 8), y * gridSize, ((x * gridSize) + 8), y * gridSize + gridSize));
      }
    }
  }

  public void drawSnakePart(Graphics g, Map.Entry<Integer, Integer> location) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.drawImage(bodyImg, location.getKey() + 8, gridSize + location.getValue(), gridSize, gridSize, null);
    return;
  }

  public void drawSnakeHead(Graphics g, Map.Entry<Integer, Integer> location) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.drawImage(headImg, location.getKey() + 8, gridSize + location.getValue(), gridSize, gridSize, null);
    return;
  }

  public void paint(Graphics g) {
    super.paint(g);

    drawLines(g);

    /* DRAWING CORNERS
    int done = 0;
    for (int i = 0; i < snakeCorners.size() - 1; i++) {
      if (done <= applesEaten) {
        int index = snakeCorners.size() - done;
        Map.Entry<Integer, Integer> location = snakeCorners.get(index);
        drawSnakePart(g, location);
        done++;
      }
    }
    drawSnakeHead(g, snakeCorners.get(snakeCorners.size() - 1));
    */
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new SnakeGameBA().setVisible(true);
      }
    });
  }

  @Override
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new SnakeGameBA().setVisible(true);
      }
    });
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      System.out.println("Right key pressed");
      Map.Entry<Integer, Integer> currentSnakeCorner = snakeCorners.get(snakeCorners.size() - 1);
      int newKey = currentSnakeCorner.getKey() + 28;
      currentSnakeCorner = Map.entry(newKey, currentSnakeCorner.getValue());
      snakeCorners.add(currentSnakeCorner);
