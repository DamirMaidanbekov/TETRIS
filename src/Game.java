import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {
    private final int WIDTH = 10;
    private final int HEIGHT = 20;
    private Grid grid;
    private Tetromino currentTetromino;
    private Timer timer;
    private boolean isGameOver;

    public Game() {
        grid = new Grid(WIDTH, HEIGHT);
        timer = new Timer(500, this); // Установка скорости игры
        timer.start();
        setFocusable(true);
        addKeyListener(new TAdapter());
        spawnNewTetromino();
        isGameOver = false;
    }

    // Метод генерации новой фигуры
    public void spawnNewTetromino() {
        currentTetromino = TetrominoFactory.getRandomTetromino();
        if (grid.checkCollision(currentTetromino, currentTetromino.x, currentTetromino.y)) {
            isGameOver = true;
            timer.stop();
        }
    }

    // Обновление игры
    public void update() {
        if (isGameOver) {
            return;
        }
        if (!grid.checkCollision(currentTetromino, currentTetromino.x, currentTetromino.y + 1)) {
            currentTetromino.move(0, 1);
        } else {
            grid.placeTetromino(currentTetromino);
            grid.clearFullLines();
            spawnNewTetromino();
        }
        repaint();
    }

    // Отображение игры
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        grid.draw(g);
        if (currentTetromino != null) {
            currentTetromino.draw(g);
        }
    }

    // Обработка событий таймера
    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }

    // Обработка событий клавиатуры
    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (isGameOver) {
                return;
            }
            int keycode = e.getKeyCode();
            if (keycode == KeyEvent.VK_LEFT) {
                if (!grid.checkCollision(currentTetromino, currentTetromino.x - 1, currentTetromino.y)) {
                    currentTetromino.move(-1, 0);
                }
            } else if (keycode == KeyEvent.VK_RIGHT) {
                if (!grid.checkCollision(currentTetromino, currentTetromino.x + 1, currentTetromino.y)) {
                    currentTetromino.move(1, 0);
                }
            } else if (keycode == KeyEvent.VK_DOWN) {
                if (!grid.checkCollision(currentTetromino, currentTetromino.x, currentTetromino.y + 1)) {
                    currentTetromino.move(0, 1);
                }
            } else if (keycode == KeyEvent.VK_UP) {
                currentTetromino.rotate();
                if (grid.checkCollision(currentTetromino, currentTetromino.x, currentTetromino.y)) {
                    currentTetromino.rotate(); // Вращаем назад если коллизия
                    currentTetromino.rotate();
                    currentTetromino.rotate();
                }
            } else if (keycode == KeyEvent.VK_SPACE) {
                while (!grid.checkCollision(currentTetromino, currentTetromino.x, currentTetromino.y + 1)) {
                    currentTetromino.move(0, 1);
                }
                update();
            }
            repaint();
        }
    }
}
