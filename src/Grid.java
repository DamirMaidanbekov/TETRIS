import java.awt.Color;
import java.awt.Graphics;

public class Grid {
    private int[][] grid;
    private final int width;
    private final int height;
    private final int cellSize = 30;

    // Инициализирует игровое поле с заданной шириной и высотой
    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new int[height][width];
    }

    // Отображает игровое поле и клетки с фигурами на графическом контексте Graphics
    public void draw(Graphics g) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x] == 0) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                    g.setColor(Color.GRAY);
                    g.drawRect(x * cellSize, y * cellSize, cellSize, cellSize);
                } else {
                    g.setColor(Color.BLUE);
                    g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                    g.setColor(Color.GRAY);
                    g.drawRect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    // Проверяет, занята ли клетка по координатам x и y
    public boolean isCellOccupied(int x, int y) {
        return grid[y][x] != 0;
    }

    // Устанавливает значение клетки по координатам x и y
    public void setCell(int x, int y, int value) {
        grid[y][x] = value;
    }

    // Очищает заполненные линии и возвращает количество очищенных линий
    public int clearFullLines() {
        int linesCleared = 0;
        for (int y = 0; y < height; y++) {
            boolean lineFull = true;
            for (int x = 0; x < width; x++) {
                if (grid[y][x] == 0) {
                    lineFull = false;
                    break;
                }
            }
            if (lineFull) {
                linesCleared++;
                removeLine(y);
            }
        }
        return linesCleared;
    }

    // Удаляет указанную линию и сдвигает остальные вниз
    private void removeLine(int line) {
        for (int y = line; y > 0; y--) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = grid[y - 1][x];
            }
        }
        for (int x = 0; x < width; x++) {
            grid[0][x] = 0;
        }
    }

    // Проверяет коллизию фигуры с границами и другими фигурами
    public boolean checkCollision(Tetromino tetromino, int newX, int newY) {
        for (int y = 0; y < tetromino.shape.length; y++) {
            for (int x = 0; x < tetromino.shape[y].length; x++) {
                if (tetromino.shape[y][x] != 0) {
                    int gridX = newX + x;
                    int gridY = newY + y;
                    if (gridX < 0 || gridX >= width || gridY >= height || isCellOccupied(gridX, gridY)) return true;
                }
            }
        }
        return false;
    }

    // Закрепляет фигуру на игровом поле
    public void placeTetromino(Tetromino tetromino) {
        for (int y = 0; y < tetromino.shape.length; y++) {
            for (int x = 0; x < tetromino.shape[y].length; x++) {
                if (tetromino.shape[y][x] != 0) {
                    setCell(tetromino.x + x, tetromino.y + y, tetromino.shape[y][x]);
                }
            }
        }
    }
}
