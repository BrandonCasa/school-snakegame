import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

interface OnSnakeEventListener {
  void onKeyPressed(int key);
}

public class EventHandler implements KeyListener {
  private OnSnakeEventListener sListener;

  public EventHandler(OnSnakeEventListener sListener) {
    this.sListener = sListener;
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {

    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      System.out.println("Right key pressed");
      sListener.onKeyPressed(e.getKeyCode());
    }
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      System.out.println("Left key pressed");
      sListener.onKeyPressed(e.getKeyCode());
    }

  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      System.out.println("Right key Released");
    }
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      System.out.println("Left key Released");
    }
  }

}
