package kickerbreaker;

/**
 * Created by karina on 01-10-2017.
 */

import kickerbreaker.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class KickerBreaker extends JFrame {
    InputStream in;

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Controller controller = new Controller();
                //game.setVisible(true);
            }
        });
    }
}
