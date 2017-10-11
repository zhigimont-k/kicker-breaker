package kickerbreaker.view;

import kickerbreaker.model.Const;

import javax.swing.*;

/**
 * Created by karina on 02-10-2017.
 */
public class Window extends JFrame {
    public JLabel score;
    public JLabel level;
    public JLabel goals;
    public Window() {
        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Const.WIDTH, Const.HEIGHT);
        setLocationRelativeTo(null);


        setTitle("KickerBreaker");
        setVisible(true);
    }
}
