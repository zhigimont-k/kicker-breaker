package kickerbreaker.view;

import javax.swing.*;

/**
 * Created by karina on 03-10-2017.
 */
public class GateSprite extends Sprite {
    private String type;
    public GateSprite(String type) {
        ImageIcon ii;
        if (type.toLowerCase() == "player"){
            ii = new ImageIcon((getClass().getResource("img/player gate.png")));
        } else {
            ii = new ImageIcon((getClass().getResource("img/enemy gate.png")));
        }
        image = ii.getImage();

        i_width = image.getWidth(null);
        i_height = image.getHeight(null);

    }
}
