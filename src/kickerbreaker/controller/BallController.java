package kickerbreaker.controller;

import kickerbreaker.model.Ball;
import kickerbreaker.view.*;

/**
 * Created by karina on 22-10-2017.
 */
public class BallController {
    Ball model;
    BallSprite sprite;

    public BallController(Ball model, BallSprite sprite){
        this.model = model;
        this.sprite = sprite;
    }

    public void move() {

        sprite.setX(sprite.getX() + model.getXDir());
        sprite.setY(sprite.getY() + model.getYDir());

        if (sprite.getX() == 0) {
            model.setXDir(1);
        }

        if (sprite.getX() >= GamePanel.WIDTH - Sprite.BALL_WIDTH) {
            model.setXDir(-1);
        }

        if (sprite.getY() == 0) {
            model.setYDir(1);
        }

        if (sprite.getY() >= GamePanel.HEIGHT - Sprite.BALL_HEIGHT) {
            model.setYDir(-1);
        }
    }


}
