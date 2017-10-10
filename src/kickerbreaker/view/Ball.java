package kickerbreaker.view;

/**
 * Created by karina on 08-10-2017.
 */
import kickerbreaker.model.Const;

import javax.swing.ImageIcon;

public class Ball extends Sprite implements Const {

    private int xdir;
    private int ydir;

    public Ball() {

        xdir = 1;
        ydir = -1;

        ImageIcon ii = new ImageIcon((getClass().getResource("img/ball.png")));
        image = ii.getImage();

        i_width = image.getWidth(null);
        i_height = image.getHeight(null);

        resetState();
    }

    public void move() {

        x += xdir;
        y += ydir;

        if (x == 0) {
            setXDir(1);
        }

        if (x == WIDTH - i_width) {
            setXDir(-1);
        }

        if (y == 0) {
            setYDir(1);
        }

        if (y == HEIGHT - i_height) {
            setYDir(-1);
        }
    }

    private void resetState() {

        x = INIT_BALL_X;
        y = INIT_BALL_Y;
    }

    public void setXDir(int x) {
        xdir = x;
    }

    public void setYDir(int y) {
        ydir = y;
    }

    public int getYDir() {
        return ydir;
    }
}
