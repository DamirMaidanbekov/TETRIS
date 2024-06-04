import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public abstract class Tetromino {
    protected int[][] shape;
    protected int x, y;
    protected Color color;

    public Tetromino(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
        this.x = 3; // начальная позиция по X
        this.y = 0; // начальная позиция по Y
    }

    // Поворот фигуры
    public void rotate() {
        int[][] rotatedShape = new int[shape[0].length][shape.length];
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                rotatedShape[j][shape.length - 1 - i] = shape[i][j];
            }
        }
        shape = rotatedShape;
    }

    // Отображение фигуры
    public void draw(Graphics g) {
        g.setColor(color);
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    g.fillRect((x + j) * 30, (y + i) * 30, 30, 30);
                    g.setColor(Color.GRAY);
                    g.drawRect((x + j) * 30, (y + i) * 30, 30, 30);
                    g.setColor(color);
                }
            }
        }
    }

    // Перемещение фигуры
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
}

// Класс для фигуры I
class TetrominoI extends Tetromino {
    public TetrominoI() {
        super(new int[][] {
                {1, 1, 1, 1}
        }, Color.CYAN);
    }
}

// Класс для фигуры O
class TetrominoO extends Tetromino {
    public TetrominoO() {
        super(new int[][] {
                {1, 1},
                {1, 1}
        }, Color.YELLOW);
    }
}

// Класс для фигуры T
class TetrominoT extends Tetromino {
    public TetrominoT() {
        super(new int[][] {
                {0, 1, 0},
                {1, 1, 1}
        }, Color.MAGENTA);
    }
}

// Класс для фигуры S
class TetrominoS extends Tetromino {
    public TetrominoS() {
        super(new int[][] {
                {0, 1, 1},
                {1, 1, 0}
        }, Color.GREEN);
    }
}

// Класс для фигуры Z
class TetrominoZ extends Tetromino {
    public TetrominoZ() {
        super(new int[][] {
                {1, 1, 0},
                {0, 1, 1}
        }, Color.RED);
    }
}

// Класс для фигуры J
class TetrominoJ extends Tetromino {
    public TetrominoJ() {
        super(new int[][] {
                {1, 0, 0},
                {1, 1, 1}
        }, Color.BLUE);
    }
}

// Класс для фигуры L
class TetrominoL extends Tetromino {
    public TetrominoL() {
        super(new int[][] {
                {0, 0, 1},
                {1, 1, 1}
        }, Color.ORANGE);
    }
}

// Генератор случайных фигур
class TetrominoFactory {
    private static final Random random = new Random();

    public static Tetromino getRandomTetromino() {
        int i = random.nextInt(7);
        if (i == 0) {
            return new TetrominoI();
        } else if (i == 1) {
            return new TetrominoO();
        } else if (i == 2) {
            return new TetrominoT();
        } else if (i == 3) {
            return new TetrominoS();
        } else if (i == 4) {
            return new TetrominoZ();
        } else if (i == 5) {
            return new TetrominoJ();
        } else if (i == 6) {
            return new TetrominoL();
        }
        return null;
    }
}
