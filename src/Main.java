import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.EventQueue;

public class Main extends JFrame {

    public Main() {
        initUI();
    }

    private void initUI() {
        Game game = new Game();
        add(game);
        setTitle("Tetris");
        setSize(300, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Main ex = new Main();
            ex.setVisible(true);
        });
    }
}
