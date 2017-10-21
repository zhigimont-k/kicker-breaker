package kickerbreaker.view;

import javax.swing.*;

/**
 * Created by karina on 09-10-2017.
 */
public class Background extends Sprite {
    public Background() {
        ImageIcon ii = new ImageIcon((getClass().getResource("img/background.png")));
        image = ii.getImage();

        i_width = image.getWidth(null);
        i_height = image.getHeight(null);

    }
}
