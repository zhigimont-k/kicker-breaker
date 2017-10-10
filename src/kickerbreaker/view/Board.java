package kickerbreaker.view;

/**
 * Created by karina on 03-10-2017.
 */
import kickerbreaker.model.Const;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class Board extends JPanel implements Const {

    public String score = "Score: ";
    public String message = "Game Over";
    public Ball ball;
    public Player player;
    public Gate enemyGate;
    public Gate playerGate;
    public boolean ingame = true;
    public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private Background background;

    public Board() {

    }


    @Override
    public void addNotify() {

        super.addNotify();
        gameInit();
    }

    private void gameInit() {
        background = new Background();
        playerGate = new Gate(0);
        enemyGate = new Gate(1);
        player = new Player();
        ball = new Ball();

        playerGate.setX(Const.WIDTH / 2 - playerGate.getWidth() / 2);
        playerGate.setY(BOTTOM_EDGE);

        enemyGate.setX(Const.WIDTH / 2 - playerGate.getWidth() / 2);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        if (ingame) {

            drawObjects(g2d);
        } else {

            gameFinished(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics2D g2d) {
        g2d.drawImage(background.getImage(), background.getX(), background.getY(),
                background.getWidth(), background.getHeight(), this);
        g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(),
                ball.getWidth(), ball.getHeight(), this);
        g2d.drawImage(player.getImage(), player.getX(), player.getY(),
                player.getWidth(), player.getHeight(), this);
        g2d.drawImage(playerGate.getImage(), Const.WIDTH / 2 - playerGate.getWidth() / 2,
                BOTTOM_EDGE,
                playerGate.getWidth(), playerGate.getHeight(), this);
        g2d.drawImage(enemyGate.getImage(), Const.WIDTH / 2 - enemyGate.getWidth() / 2,
                0,
                enemyGate.getWidth(), enemyGate.getHeight(), this);

        for (int i = 0; i < enemies.size(); i++) {
            if (!enemies.get(i).isDestroyed()) {
                g2d.drawImage(enemies.get(i).getImage(), enemies.get(i).getX(),
                        enemies.get(i).getY(), enemies.get(i).getWidth(),
                        enemies.get(i).getHeight(), this);
            }
        }
    }

    private void gameFinished(Graphics2D g2d) {

        Font font = new Font("Verdana", Font.BOLD, 18);
        FontMetrics metr = this.getFontMetrics(font);

        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(message,
                (Const.WIDTH - metr.stringWidth(message)) / 2,
                Const.WIDTH / 2);
        g2d.drawString(score,
                (Const.WIDTH - metr.stringWidth(message)) / 2,
                Const.WIDTH / 2 + 20);
    }

    public void clearEnemyList(){
        for (int index = enemies.size() - 1; index <= 0; index--){
            enemies.remove(index);
        }
    }


    }
