package kickerbreaker;

/**
 * Created by karina on 08-10-2017.
 */
import kickerbreaker.controller.Controller;
import kickerbreaker.view.Window;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Breakout extends JFrame {



    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Window game = new Window();
                //Controller controller = new Controller();
                game.setVisible(true);
            }
        });
    }
}
