package kickerbreaker.controller;

import kickerbreaker.model.Player;
import kickerbreaker.view.*;

import java.awt.event.KeyEvent;

/**
 * Created by karina on 22-10-2017.
 */
public class PlayerController {
    public Player model;
    public PlayerSprite sprite;
    public PlayerController(Player model, PlayerSprite sprite){
        this.model = model;
        this.sprite = sprite;
    }


    public void move() {

        sprite.setX(sprite.getX()+model.getDx());

        if (sprite.getX() <= 0) {
            sprite.setX(0);
        }

        if (sprite.getX() >= GamePanel.WIDTH - Sprite.SPRITE_WIDTH) {
            sprite.setX(GamePanel.WIDTH - Sprite.SPRITE_WIDTH);
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            model.setDx(-1);
        }

        if (key == KeyEvent.VK_RIGHT) {
            model.setDx(1);
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            model.setDx(0);
        }

        if (key == KeyEvent.VK_RIGHT) {
            model.setDx(0);
        }
    }

}
