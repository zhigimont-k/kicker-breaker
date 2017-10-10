package kickerbreaker.view;

import kickerbreaker.controller.Controller;
import kickerbreaker.model.Const;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
        //setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Const.WIDTH, Const.FULL_HEIGHT);
        setLocationRelativeTo(null);

        //setContentPane(new JLabel(new ImageIcon(getClass().getResource("img/background.png"))));

        Controller controller = new Controller();
        score = new JLabel("Score: ");
        level = new JLabel("Level: ");
        goals = new JLabel("Goals: ");
        score.setBounds(10, 40, this.getWidth(), this.getHeight());
        //add(score);
        //add(level);
        //controller.board.add(score);
        add(controller.board);
        setTitle("KickerBreaker");
        //setResizable(false);
        //pack();
        setVisible(true);
    }
}
