package kickerbreaker.view;

/**
 * Created by karina on 02-10-2017.
 */
import kickerbreaker.model.Const;

import javax.swing.ImageIcon;

public class BallSprite extends Sprite implements Const {

    private int xdir;
    private int ydir;

    public BallSprite() {



        ImageIcon ii = new ImageIcon((getClass().getResource("img/ball.png")));
        image = ii.getImage();

        i_width = image.getWidth(null);
        i_height = image.getHeight(null);
        xdir = 1;
        ydir = -1;

        resetState();
    }

    public void move() {

        x += xdir;
        y += ydir;

        if (x == 0) {
            setXDir(1);
        }

        if (x >= Const.WIDTH - Const.BALL_WIDTH * 1.5) {
            setXDir(-1);
        }

        if (y == 0) {
            setYDir(1);
        }

        if (y >= Const.HEIGHT - Const.BALL_HEIGHT * 1.5) {
            setYDir(-1);
        }
    }

    public void resetState() {

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