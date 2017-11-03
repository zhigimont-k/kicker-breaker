package kickerbreaker.view;

/**
 * Created by karina on 02-10-2017.
 */

import javax.swing.ImageIcon;

public class EnemySprite extends Sprite {
    private ImageIcon ii;
    private boolean visible;

    public EnemySprite(int x, int y, int hp) {

        this.x = x;
        this.y = y;

        setSprite(hp);
        image = ii.getImage();

        i_width = image.getWidth(null);
        i_height = image.getHeight(null);

        visible = true;
    }

    public void setSprite(int hp){
        if (hp == 1){
            ii = new ImageIcon((getClass().getResource("img/enemy.png")));
        }
        if (hp == 2){
            ii = new ImageIcon((getClass().getResource("img/enemy2.png")));
        }
        if (hp >= 3){
            ii = new ImageIcon((getClass().getResource("img/enemy3.png")));
        }
        image = ii.getImage();
    }

    public boolean isVisible() {

        return visible;
    }

    public void setVisible(boolean value) {

        visible = value;
    }
}
