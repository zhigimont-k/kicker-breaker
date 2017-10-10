package kickerbreaker.view;

/**
 * Created by karina on 08-10-2017.
 */

import javax.swing.ImageIcon;

public class Enemy extends Sprite {

    private boolean destroyed;

    public Enemy(int x, int y) {

        this.x = x;
        this.y = y;

        ImageIcon ii = new ImageIcon((getClass().getResource("img/brick.png")));
        image = ii.getImage();

        i_width = image.getWidth(null);
        i_height = image.getHeight(null);

        destroyed = false;
    }

    public boolean isDestroyed() {

        return destroyed;
    }

    public void setDestroyed(boolean val) {

        destroyed = val;
    }
}
