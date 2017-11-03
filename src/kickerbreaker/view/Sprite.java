package kickerbreaker.view;

/**
 * Created by karina on 02-10-2017.
 */

import javax.swing.*;
import java.awt.*;

public class Sprite extends JPanel {


    public static final int SPRITE_WIDTH = 41;
    public static final int BALL_WIDTH = 20;
    public static final int BALL_HEIGHT = 21;
    public static final int INIT_PLAYER_X = 200;
    public static final int INIT_PLAYER_Y = 420;
    public static final int INIT_BALL_X = 230;
    public static final int INIT_BALL_Y = 355;

    protected int x;
    protected int y;
    protected int i_width;
    protected int i_height;
    protected Image image;

    public void setImage(Image image){
        this.image = image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return i_width;
    }

    public int getHeight() {
        return i_height;
    }

    public Image getImage() {
        return image;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y,
                image.getWidth(null), image.getHeight(null));
    }
}
