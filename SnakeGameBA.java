import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.Image; // for adding images to the game
import javax.swing.ImageIcon;
import java.util.Map;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SnakeGameBA extends JFrame {

	/**
		For direction
	*/

	boolean leftDirection = false; 
	boolean rightDirection = false; 
	boolean upDirection = false; 
	boolean downDirection = false; 

	// body components of the snake
	private Image ball; 
	private Image apple; 
	private Image head; 

    static int gridSize = 28;
    static int gridCount = 32;
    static List<Map.Entry<Integer, Integer>> snakeCorners = new ArrayList<>();
    static int applesEaten = 0;
    static final int appleInitialX = (int) (Math.random() * gridCount);
    static final int appleInitialY = (int) (Math.random() * gridCount);
    static Map.Entry<Integer, Integer> currentAppleLocationInitial = Map.entry(appleInitialY, appleInitialX);

    public SnakeGameBA() {
        super("Snake Game");

        setSize((gridSize * gridCount) + 16, (gridSize * gridCount) + 32 + 4);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    void drawApple(Graphics g) {

        g.drawImage(apple, appleInitialX, appleInitialY, null);

    }

    void drawLines(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(new Line2D.Double(0, 31, (gridSize * gridCount) + 8, 31));
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

	/**
		Load images dots etc.
	*/

	public void loadImages(){
		ImageIcon im1 = new ImageIcon("images/dot.png");
        ball = im1.getImage();

        ImageIcon im2 = new ImageIcon("images/apple.png");
        apple = im2.getImage();

        ImageIcon im3 = new ImageIcon("images/head.png");
        head = im3.getImage();
	}


    public void paint(Graphics g) {
        super.paint(g);

        drawLines(g);
        drawApple(g);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
        new SnakeGameBA().setVisible(true);
        }
        });
    }
}

