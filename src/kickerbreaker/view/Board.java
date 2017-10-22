package kickerbreaker.view;

/**
 * Created by karina on 03-10-2017.
 */

import kickerbreaker.model.Const;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {

    public String score = "Score: ";
    public String message = "Game Over";
    public BallSprite ball;
    public JLabel scoreLabel;
    public JLabel level;
    public JLabel goals;
    public JLabel combo;
    public PlayerSprite player;
    public GateSprite enemyGate;
    public GateSprite playerGate;
    public boolean ingame = true;
    public ArrayList<EnemySprite> enemies = new ArrayList<EnemySprite>();
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
        playerGate = new GateSprite("player");
        enemyGate = new GateSprite("enemy");
        player = new PlayerSprite();
        ball = new BallSprite();
        combo = new JLabel("");
        scoreLabel = new JLabel("");
        level = new JLabel("");
        goals = new JLabel("");
        add(combo);
        add(scoreLabel);
        add(level);
        add(goals);

        playerGate.setX(Const.WIDTH / 2 - playerGate.getWidth() / 2);
        playerGate.setY(Const.HEIGHT - playerGate.getHeight());

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
                Const.HEIGHT - playerGate.getHeight(),
                playerGate.getWidth(), playerGate.getHeight(), this);
        g2d.drawImage(enemyGate.getImage(), Const.WIDTH / 2 - enemyGate.getWidth() / 2,
                0,
                enemyGate.getWidth(), enemyGate.getHeight(), this);

        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).isVisible()) {
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


    public void clearEnemyList() {
        enemies.clear();
    }


}
