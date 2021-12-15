import java.awt.Color;
import java.awt.Graphics;

public class Grid implements Drawable, Entity {
  /**
   * From interface Drawable
   */
  public void draw(Graphics g) {
    // Draw Grid
    for (int x = 25; x <= 450; x += 25)
      for (int y = 25; y <= 450; y += 25)
        g.drawRect(x, y, 25, 25);
  }

  /**
   * From interface Entitiy
   */
  public void tick() {
  }

  public void second() {
  }
}