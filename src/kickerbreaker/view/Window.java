package kickerbreaker.view;

import kickerbreaker.controller.Controller;
import kickerbreaker.model.Const;

import javax.swing.*;

/**
 * Created by karina on 10-10-2017.
 */
public class Window extends JFrame {
    public Board board;
    public Window() {

        initUI();
    }

    private void initUI() {
        Controller controller = new Controller();
        //board = new Board();
        add(controller.board);
        setTitle("KickerBreaker");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Const.WIDTH, Const.FULL_HEIGHT);
        setLocationRelativeTo(null);
        //setResizable(false);
        setVisible(true);
    }
}
