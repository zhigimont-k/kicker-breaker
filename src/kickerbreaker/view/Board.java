package kickerbreaker.view;

/**
 * Created by karina on 08-10-2017.
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
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

public class Board extends JPanel implements Const {

    public String message = "Game Over";
    public Ball ball;
    public Player player;
    public Gate enemyGate;
    public Gate playerGate;
    public Enemy enemies[];
    public boolean ingame = true;

    public Board() {


    }


    @Override
    public void addNotify() {

        super.addNotify();
        gameInit();
    }

    private void gameInit() {

        ball = new Ball();
        player = new Player();
        playerGate = new Gate();
        enemyGate = new Gate();

        playerGate.setX(Const.WIDTH / 2 - playerGate.getWidth() / 2);
        playerGate.setY(BOTTOM_EDGE);

        enemyGate.setX(Const.WIDTH / 2 - playerGate.getWidth() / 2);
        //enemyGate.setY(0);

        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                enemies[k] = new Enemy(j * 40 + 30, i * 10 + 50);
                k++;
            }
        }
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

        for (int i = 0; i < N_OF_BRICKS; i++) {
            if (!enemies[i].isDestroyed()) {
                g2d.drawImage(enemies[i].getImage(), enemies[i].getX(),
                        enemies[i].getY(), enemies[i].getWidth(),
                        enemies[i].getHeight(), this);
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
    }


    }
