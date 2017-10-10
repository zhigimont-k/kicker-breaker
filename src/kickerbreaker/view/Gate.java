package kickerbreaker.view;

import javax.swing.*;

/**
 * Created by karina on 10-10-2017.
 */
public class Gate extends Sprite {
    private boolean destroyed;

    public Gate() {

        ImageIcon ii = new ImageIcon((getClass().getResource("img/gate.png")));
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
