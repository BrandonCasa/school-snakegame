// test
import java.awt.*;
import java.util.*;
import javax.swing.ImageIcon;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

abstract class Renderable {

    abstract void render(Graphics2D g);
}

enum CellState {
    EMPTY(null),
    BODY("images/body.jpeg"),
    APPLE("images/apple.jpeg"),
    HEAD("images/head.jpeg");

    private ImageIcon icon;

    CellState(String file) {
        if (file != null)
            this.icon = new ImageIcon(file);
    }

    public Image getImage() {
        return icon != null ? icon.getImage() : null;
    }
}

class Position {
    private final int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

class Cell extends Renderable {

    private final int x, y, size, offset;
    private CellState state;

    public Cell(int x, int y, int size, int offset) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.offset = offset;
        this.state = CellState.EMPTY;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    @Override
    public void render(Graphics2D g) {
        int baseX = offset + size * x;
        int baseY = offset + 25 + size * y;
        g.drawImage(state.getImage(), baseX, baseY, size, size, null);
        g.setColor(Color.BLACK);
        g.drawRect(baseX, baseY, size, size);
    }
}

class Grid extends Renderable {

    private final int height, width;
    private final Cell[][] grid;
    private final Random rand;
    private Position applePos, snakePos;

    public Grid(int height, int width, int cellSize, int offset) {
        this.height = height;
        this.width = width;
        this.grid = new Cell[height][width];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Cell(j, i, cellSize, offset);
            }
        }
        this.rand = new Random();
        updateApple();
        updateSnake();
    }

    private Position getEmptyPosition() {
        int x, y;
        do {
            x = rand.nextInt(width);
            y = rand.nextInt(height);
        } while (grid[y][x].getState() != CellState.EMPTY);
        return new Position(x, y);
    }

    private void updateApple() {
        Position next = getEmptyPosition();
        updatePos(next, CellState.APPLE);
        if (applePos != null)
            updatePos(applePos, CellState.EMPTY);
        this.applePos = next;
    }

    private void updateSnake() {
        snakePos = getEmptyPosition();
        updatePos(snakePos, CellState.HEAD);
    }

    private void updatePos(Position pos, CellState state) {
        int x = pos.getX(), y = pos.getY();
        grid[y][x].setState(state);
    }

    @Override
    public void render(Graphics2D g) {
        for (Cell[] row : grid) for (Cell c : row)
            c.render(g);
    }
}

public class SnakeGameBA extends JFrame {

    /**
     For direction
     */
    boolean leftDirection = false;
	boolean rightDirection = false;
	boolean upDirection = false;
	boolean downDirection = false;
    // body components of the snake
    private final Grid grid;
    static int GRID_WIDTH = 25, GRID_HEIGHT = 22, CELL_SIZE = 25;
    static List<Map.Entry<Integer, Integer>> snakeCorners = new ArrayList<>();
    static int applesEaten = 0;
    static final int appleInitialX = (int) (Math.random() * GRID_WIDTH);
    static final int appleInitialY = (int) (Math.random() * GRID_WIDTH);
    static Map.Entry<Integer, Integer> currentAppleLocationInitial = Map.entry(appleInitialY, appleInitialX);

    public SnakeGameBA() {
        super("Snake Game");
        setSize(
            GRID_WIDTH * CELL_SIZE + CELL_SIZE,
            GRID_HEIGHT * CELL_SIZE + CELL_SIZE + 25
        );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        this.grid = new Grid(GRID_HEIGHT, GRID_WIDTH, CELL_SIZE, CELL_SIZE / 2);
//        loadImages();
    }

//    void drawApple(Graphics g) {
//
//        g.drawImage(apple, appleInitialX, appleInitialY, null);
//
//    }

//    void drawLines(Graphics g) {
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.draw(new Line2D.Double(0, 31, (gridSize * gridCount) + 8, 31));
//        g2d.draw(new Line2D.Double((gridSize * gridCount) + 8, 31, (gridSize * gridCount) + 8,
//                (gridSize * gridCount) + gridSize));
//        g2d.draw(new Line2D.Double(0, (gridSize * gridCount) + gridSize, (gridSize * gridCount) + 8,
//                (gridSize * gridCount) + gridSize));
//        for (int x = 0; x < gridCount; x++) {
//            for (int y = 1; y <= gridCount; y++) {
//                g2d.draw(new Line2D.Double(((x * gridSize) + 8), y * gridSize, ((x * gridSize) + 8) + gridSize, y * gridSize));
//                g2d.draw(new Line2D.Double(((x * gridSize) + 8), y * gridSize, ((x * gridSize) + 8), y * gridSize + gridSize));
//            }
//        }
//    }

    /**
     Load images dots etc.
     */

//    public void loadImages(){
//        ImageIcon im1 = new ImageIcon("images/dot.jpeg");
//        body = im1.getImage();
//
//        ImageIcon im2 = new ImageIcon("images/apple.jpeg");
//        apple = im2.getImage();
//
//        ImageIcon im3 = new ImageIcon("images/head.jpeg");
//        head = im3.getImage();
//    }


    public void paint(Graphics g) {
        super.paint(g);
        grid.render((Graphics2D) g);
//        drawLines(g);
//        drawApple(g);
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
