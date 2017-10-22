package kickerbreaker.view;

/**
 * Created by karina on 02-10-2017.
 */

import javax.swing.*;
import java.awt.*;

public class Sprite extends JPanel {

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
