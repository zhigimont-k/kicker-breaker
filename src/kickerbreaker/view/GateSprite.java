package kickerbreaker.view;

import javax.swing.*;

/**
 * Created by karina on 03-10-2017.
 */
public class GateSprite extends Sprite {
    public GateSprite(int i) {
        ImageIcon ii;
        switch (i) {
            case 0:
                ii = new ImageIcon((getClass().getResource("img/player gate.png")));
                break;
            default:
                ii = new ImageIcon((getClass().getResource("img/enemy gate.png")));
                break;
        }
        image = ii.getImage();

        i_width = image.getWidth(null);
        i_height = image.getHeight(null);

    }
}
