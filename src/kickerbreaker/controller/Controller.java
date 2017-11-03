package kickerbreaker.controller;

import kickerbreaker.model.Enemy;
import kickerbreaker.model.Model;
import kickerbreaker.view.EnemySprite;
import kickerbreaker.view.GamePanel;
import kickerbreaker.view.Window;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by karina on 04-10-2017.
 */
public class Controller {

    public static final int DELAY = 1000;
    public static final int PERIOD = 10;
    public static final int MAX_GOALS = 5;

    private GamePanel gamePanel;
    private Model model;
    private Window window;
    private BallController ballController;
    private PlayerController playerController;

    private Timer timer;

    public Controller() {
        this.model = new Model();
        this.window = new Window();
        this.gamePanel = new GamePanel();
        XMLReader reader = new XMLReader(model);
        window.add(gamePanel);
        gamePanel.setPreferredSize(new Dimension(gamePanel.WIDTH, gamePanel.HEIGHT));
        window.setContentPane(gamePanel);
        window.pack();
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        model.generateCurrentLevel();
        addEnemiesOnBoard();
        updateStat();
        ballController = new BallController(model.ball, gamePanel.ball);
        playerController = new PlayerController(model.player, gamePanel.player);
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), DELAY, PERIOD);
        gamePanel.addKeyListener(new TAdapter());
        gamePanel.setFocusable(true);
        gamePanel.setDoubleBuffered(true);
    }

    private void stopGame() {

        gamePanel.ingame = false;
        timer.cancel();
    }

    private void checkCollision() {

        if (model.getEnemyGoals() == MAX_GOALS) {
            gamePanel.combo.setVisible(false);
            gamePanel.scoreLabel.setVisible(false);
            gamePanel.level.setVisible(false);
            gamePanel.goals.setVisible(false);
            gamePanel.score = "Score: " + model.getScore();
            stopGame();
        }

        if ((gamePanel.playerGate.getRect()).intersects(gamePanel.ball.getRect()) && !model.playerGate.isHit) {
            model.addEnemyGoal();
            model.playerGate.isHit = true;
            updateStat();
        }

        if (!((gamePanel.playerGate.getRect()).intersects(gamePanel.ball.getRect()))) {
            model.playerGate.isHit = false;
        }

        if ((gamePanel.enemyGate.getRect()).intersects(gamePanel.ball.getRect()) && !model.enemyGate.isHit) {
            model.addPlayerGoal();
            model.enemyGate.isHit = true;
            updateStat();
        }

        if (!((gamePanel.enemyGate.getRect()).intersects(gamePanel.ball.getRect()))) {
            model.enemyGate.isHit = false;
        }

        for (int i = 0, j = 0; i < model.getCurrentLevel().enemyList.size(); i++) {

            if (model.getCurrentLevel().enemyList.get(i).isDestroyed()) {
                j++;
            }

            if (j == model.getCurrentLevel().enemyList.size() || model.getPlayerGoals() == MAX_GOALS) {

                if (model.getCurrentLevelNumber() < model.getNumberOfLevels() - 1) {
                    model.getCurrentLevel().clearEnemyList();
                    gamePanel.clearEnemyList();
                    model.nullifyGoals();
                    model.breakCombo();
                    model.levelUp();
                    updateStat();
                    model.generateCurrentLevel();
                    addEnemiesOnBoard();
                    gamePanel.player.resetState();
                    ballController.sprite.resetState();
                } else {
                    gamePanel.message = "Victory";
                    gamePanel.score = "Score: " + model.getScore();
                    updateStat();
                    stopGame();
                }

            }
        }

        if ((gamePanel.ball.getRect()).intersects(gamePanel.player.getRect())) {
            model.breakCombo();
            updateStat();
            int playerLPos = (int) gamePanel.player.getRect().getMinX();
            int ballLPos = (int) gamePanel.ball.getRect().getMinX();

            int first = playerLPos + 8;
            int second = playerLPos + 16;
            int third = playerLPos + 24;
            int fourth = playerLPos + 32;

            if (ballLPos < first) {
                model.ball.setXDir(-1);
                model.ball.setYDir(-1);
            }

            if (ballLPos >= first && ballLPos < second) {
                model.ball.setXDir(-1);
                model.ball.setYDir(-1 * model.ball.getYDir());
            }

            if (ballLPos >= second && ballLPos < third) {
                model.ball.setXDir(0);
                model.ball.setYDir(-1);
            }

            if (ballLPos >= third && ballLPos < fourth) {
                model.ball.setXDir(1);
                model.ball.setYDir(-1 * model.ball.getYDir());
            }

            if (ballLPos > fourth) {
                model.ball.setXDir(1);
                model.ball.setYDir(-1);
            }
        }

        for (int i = 0; i < model.getCurrentLevel().enemyList.size(); i++) {
            if ((gamePanel.ball.getRect()).intersects(gamePanel.enemies.get(i).getRect())) {

                int ballLeft = (int) gamePanel.ball.getRect().getMinX();
                int ballHeight = (int) gamePanel.ball.getRect().getHeight();
                int ballWidth = (int) gamePanel.ball.getRect().getWidth();
                int ballTop = (int) gamePanel.ball.getRect().getMinY();

                Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                Point pointLeft = new Point(ballLeft - 1, ballTop);
                Point pointTop = new Point(ballLeft, ballTop - 1);
                Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                if (!model.getCurrentLevel().enemyList.get(i).isDestroyed()) {
                    if (gamePanel.enemies.get(i).getRect().contains(pointRight)) {
                        model.ball.setXDir(-1);
                    } else if (gamePanel.enemies.get(i).getRect().contains(pointLeft)) {
                        model.ball.setXDir(1);
                    }

                    if (gamePanel.enemies.get(i).getRect().contains(pointTop)) {
                        model.ball.setYDir(1);
                    } else if (gamePanel.enemies.get(i).getRect().contains(pointBottom)) {
                        model.ball.setYDir(-1);
                    }
                    if (!model.getCurrentLevel().enemyList.get(i).isHit()) {
                        model.getCurrentLevel().enemyList.get(i).enemyHit();
                        model.getCurrentLevel().enemyList.get(i).setHit(true);
                        gamePanel.enemies.get(i).setSprite( model.getCurrentLevel().enemyList.get(i).getHP());
                    }
                    if ( model.getCurrentLevel().enemyList.get(i).getHP() == 0) {
                        model.getCurrentLevel().enemyList.get(i).setDestroyed(true);
                        gamePanel.enemies.get(i).setVisible(false);
                        model.comboIncrement();
                        model.destructionScoreIncrement();
                        updateStat();
                    }
                }
            }
            if (!(gamePanel.ball.getRect()).intersects(gamePanel.enemies.get(i).getRect())) {
                model.getCurrentLevel().enemyList.get(i).setHit(false);
            }
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            playerController.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            playerController.keyPressed(e);
        }
    }

    private class ScheduleTask extends TimerTask {

        @Override
        public void run() {
            playerController.move();
            gamePanel.player.repaint();
            ballController.move();
            gamePanel.ball.repaint();
            checkCollision();
            gamePanel.repaint();
        }

    }

    public void addEnemiesOnBoard() {
        for (Enemy enemy : model.getCurrentLevel().enemyList) {
            gamePanel.enemies.add(new EnemySprite(enemy.getX(), enemy.getY(), enemy.getHP()));
        }
    }

    public void updateStat() {
        gamePanel.combo.setText("x" + model.getCombo());
        int level = model.getCurrentLevelNumber()+1;
        gamePanel.level.setText("Level " + level);
        gamePanel.goals.setText(model.getPlayerGoals() + " : " + model.getEnemyGoals());
        gamePanel.scoreLabel.setText("Score: " + model.getScore());
    }




}