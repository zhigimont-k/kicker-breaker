package kickerbreaker.view;

/**
 * Created by karina on 02-10-2017.
 */
import kickerbreaker.model.Const;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Player extends Sprite implements Const {

    private int dx;

    public Player() {

        ImageIcon ii = new ImageIcon((getClass().getResource("img/player.png")));
        image = ii.getImage();

        i_width = image.getWidth(null);
        i_height = image.getHeight(null);

        resetState();
    }

    public void move() {

        x += dx;

        if (x <= 0) {
            x = 0;
        }

        if (x >= WIDTH - i_width) {
            x = WIDTH - i_width;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    private void resetState() {

        x = INIT_PLAYER_X;
        y = INIT_PLAYER_Y;
    }
}
