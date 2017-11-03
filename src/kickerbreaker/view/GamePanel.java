package kickerbreaker.view;

/**
 * Created by karina on 03-10-2017.
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 500;

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
    public List<EnemySprite> enemies = new ArrayList<EnemySprite>();
    private Background background;

    public GamePanel() {

    }


    @Override
    public void addNotify() {

        super.addNotify();
        gameInit();
    }

    private void gameInit() {
        background = new Background();
        playerGate = new GateSprite();
        playerGate.setImage(new ImageIcon((getClass().getResource("img/player gate.png"))).getImage());
        enemyGate = new GateSprite();
        enemyGate.setImage(new ImageIcon((getClass().getResource("img/enemy gate.png"))).getImage());
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

        playerGate.setX(WIDTH / 2 - playerGate.getWidth() / 2);
        playerGate.setY(HEIGHT - playerGate.getHeight());

        enemyGate.setX(WIDTH / 2 - playerGate.getWidth() / 2);

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
        g2d.drawImage(playerGate.getImage(), WIDTH / 2 - playerGate.getWidth() / 2,
                HEIGHT - playerGate.getHeight(),
                playerGate.getWidth(), playerGate.getHeight(), this);
        g2d.drawImage(enemyGate.getImage(), WIDTH / 2 - enemyGate.getWidth() / 2,
                0,
                enemyGate.getWidth(), enemyGate.getHeight(), this);

        for (EnemySprite enemySprite : enemies) {
            if (enemySprite.isVisible()) {
                g2d.drawImage(enemySprite.getImage(), enemySprite.getX(),
                        enemySprite.getY(), enemySprite.getWidth(),
                        enemySprite.getHeight(), this);
            }
        }
    }

    private void gameFinished(Graphics2D g2d) {

        Font font = new Font("Verdana", Font.BOLD, 18);
        FontMetrics metr = this.getFontMetrics(font);

        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(message,
                (WIDTH - metr.stringWidth(message)) / 2,
                WIDTH / 2);
        g2d.drawString(score,
                (WIDTH - metr.stringWidth(message)) / 2,
                WIDTH / 2 + 20);
    }


    public void clearEnemyList() {
        enemies.clear();
    }


}
