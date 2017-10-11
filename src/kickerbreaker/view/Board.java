package kickerbreaker.view;

/**
 * Created by karina on 03-10-2017.
 */

import kickerbreaker.model.Const;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel implements Const {

    public String score = "Score: ";
    public String message = "Game Over";
    public BallSprite ball;
    public JLabel scoreLabel;
    public JLabel level;
    public JLabel goals;
    public PlayerSprite player;
    public GateSprite enemyGate;
    public GateSprite playerGate;
    public boolean ingame = true;
    public boolean levelStart = false;
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
        playerGate = new GateSprite(0);
        enemyGate = new GateSprite(1);
        player = new PlayerSprite();
        ball = new BallSprite();
        scoreLabel = new JLabel("Score: 0");
        level = new JLabel("Level 1");
        goals = new JLabel("Goals: 0");
        add(scoreLabel);
        add(level);
        add(goals);

        playerGate.setX(Const.WIDTH / 2 - playerGate.getWidth() / 2);
        playerGate.setY((int)(Const.HEIGHT - playerGate.getHeight() * 1.5));

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
                (int)(Const.HEIGHT - playerGate.getHeight() * 1.5),
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
