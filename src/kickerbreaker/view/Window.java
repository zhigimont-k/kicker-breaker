package kickerbreaker.view;

import javax.swing.*;

/**
 * Created by karina on 02-10-2017.
 */
public class Window extends JFrame {
    public Window() {
        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("KickerBreaker");
        setVisible(true);
    }
}
