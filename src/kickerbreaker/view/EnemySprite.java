package kickerbreaker.view;

/**
 * Created by karina on 02-10-2017.
 */

import javax.swing.ImageIcon;

public class EnemySprite extends Sprite {

    private boolean destroyed;

    public EnemySprite(int x, int y) {

        this.x = x;
        this.y = y;

        ImageIcon ii = new ImageIcon((getClass().getResource("img/enemy.png")));
        image = ii.getImage();

        i_width = image.getWidth(null);
        i_height = image.getHeight(null);

        destroyed = false;
    }

    public boolean isDestroyed() {

        return destroyed;
    }

    public void setDestroyed(boolean value) {

        destroyed = value;
    }
}
