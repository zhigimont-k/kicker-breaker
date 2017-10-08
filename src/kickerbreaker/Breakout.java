package kickerbreaker;

/**
 * Created by karina on 08-10-2017.
 */
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Breakout extends JFrame {

    public Breakout() {

        initUI();
    }

    private void initUI() {

        add(new Board());
        setTitle("KickerBreaker");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Commons.WIDTH, Commons.HEIGTH);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Breakout game = new Breakout();
                game.setVisible(true);
            }
        });
    }
}
