import java.awt.Color;
import java.awt.Graphics;

public class FPSViewer implements Drawable, Entity {
  // executions and renderings per second
  private int ticks;
  private int frames;

  private int averageTicks;
  private int averageFrames;

  /**
   * From interface Drawable
   */
  public void draw(Graphics g) {
    frames++;
    g.setColor(Color.black);
    g.drawString("FPS: " + averageFrames + " Ticks: " + averageTicks, 380, 16);
  }

  /**
   * From interface Entitiy
   */
  public void tick() {
    ticks++;
  }

  public void second() {
    averageFrames = frames;
    averageTicks = ticks;
    frames = 0;
    ticks = 0;
  }
}