package kickerbreaker.view;

/**
 * Created by karina on 02-10-2017.
 */

import kickerbreaker.model.Const;

import javax.swing.*;

public class BallSprite extends Sprite {

    public BallSprite() {

        ImageIcon ii = new ImageIcon((getClass().getResource("img/ball.png")));
        image = ii.getImage();

        i_width = image.getWidth(null);
        i_height = image.getHeight(null);

        resetState();

    }


    public void resetState() {

        x = Const.INIT_BALL_X;
        y = Const.INIT_BALL_Y;
    }
}
