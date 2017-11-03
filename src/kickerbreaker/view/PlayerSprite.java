package kickerbreaker.view;

/**
 * Created by karina on 02-10-2017.
 */

import javax.swing.*;

public class PlayerSprite extends Sprite {


    public PlayerSprite() {

        ImageIcon ii = new ImageIcon((getClass().getResource("img/player.png")));
        image = ii.getImage();

        i_width = image.getWidth(null);
        i_height = image.getHeight(null);

        resetState();
    }

    public void resetState() {

        x = INIT_PLAYER_X;
        y = INIT_PLAYER_Y;
    }
}
