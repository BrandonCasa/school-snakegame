import java.util.ArrayList;
import java.util.List;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class SnakeGame extends Canvas implements Runnable {

  /**
   * Run, Tick, Render, Entities and Drawables
   */
  private boolean isRunning = true;
  private Thread thread;

  private static final long NANOSECOND = 1000000000;
  private static final double OPTIMAL_TICKS = 50.0;
  private static final double OPTIMAL_TIME = NANOSECOND / OPTIMAL_TICKS;

  private long lastLoopTime = System.nanoTime();
  private long currentTime;
  private double deltaTime;
  private long secondTimer = System.currentTimeMillis();

  private final List<Entity> entities = new ArrayList<>();
  private final List<Drawable> drawables = new ArrayList<>();

  /**
   * Window Constants
   */
  private static final int WIDTH = 500;
  private static final int HEIGHT = 500;
  private static final String TITLE = "Snake Game";

  /**
   * Entities
   */
  private final FPSViewer fpsViewer = new FPSViewer();
  private final Grid grid = new Grid();
  private static final Snake snake = new Snake();

  // there are more here, stripped for this review

  /**
   * Data Contaienrs
   */
  // there are more here, stripped for this review

  /**
   * Main Function, instantiating SnakeGame
   */
  public static void main(String[] args) {
    new SnakeGame();
  }

  /**
   * SnakeGame constructor, initailized by main ()
   */
  public SnakeGame() {
    // fps tracking
    entities.add(fpsViewer);
    drawables.add(fpsViewer);

    // grid
    entities.add(grid);
    drawables.add(grid);

    // snake
    entities.add(snake);
    drawables.add(snake);

    this.addKeyListener(snake);

    Window window = new Window(WIDTH, HEIGHT, TITLE, this);

    thread = new Thread(this);
    thread.start();
  }

  /**
   * SnakeGame Loop
   */
  public void run() {
    while (isRunning) {
      // get delta time
      currentTime = System.nanoTime();
      deltaTime += (currentTime - lastLoopTime) / OPTIMAL_TIME;
      lastLoopTime = currentTime;

      while (deltaTime >= 1) {
        update();
        deltaTime--;
      }

      // update the game
      render();

      // reset if a second has passed
      if (System.currentTimeMillis() - secondTimer > 335) {
        updatePerSecond();
        secondTimer += 335;
      }
    }
  }

  /**
   * SnakeGame Updates for all entities
   */
  private void update() {
    for (Entity e : entities) {
      e.tick();
    }
  }

  private void updatePerSecond() {
    for (Entity e : entities) {
      e.second();
    }
  }

  /**
   * Renders all graphics for all drawables
   */
  private void render() {
    BufferStrategy bufferstrategy = getBufferStrategy();

    if (bufferstrategy == null) {
      createBufferStrategy(3);
      return;
    }

    Graphics g = bufferstrategy.getDrawGraphics();

    g.setColor(Color.white);
    g.fillRect(0, 0, getWidth(), getHeight());

    for (Drawable d : drawables) {
      d.draw(g);
    }

    g.dispose();
    bufferstrategy.show();
  }
}