package kickerbreaker.controller;

import kickerbreaker.model.Const;
import kickerbreaker.model.Model;
import kickerbreaker.view.Board;
import kickerbreaker.view.EnemySprite;
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
    public Board board;
    private Model model;
    private Window window;
    private BallController ballController;
    private PlayerController playerController;

    private Timer timer;

    public Controller() {
        this.model = new Model();
        this.window = new Window();
        this.board = new Board();
        XMLReader reader = new XMLReader(model);
        window.add(board);
        board.setPreferredSize(new Dimension(Const.WIDTH, Const.HEIGHT));
        window.setContentPane(board);
        window.pack();
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        model.generateEnemies();
        addEnemiesOnBoard();
        updateStat();
        ballController = new BallController(model.ball, board.ball);
        playerController = new PlayerController(model.player, board.player);
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), Const.DELAY, Const.PERIOD);
        board.addKeyListener(new TAdapter());
        board.setFocusable(true);
        board.setDoubleBuffered(true);
    }

    private void stopGame() {

        board.ingame = false;
        timer.cancel();
    }

    private void checkCollision() {

        if (model.getEnemyGoals() == Const.MAX_GOALS) {
            board.combo.setVisible(false);
            board.scoreLabel.setVisible(false);
            board.level.setVisible(false);
            board.goals.setVisible(false);
            board.score = "Score: " + model.getScore();
            stopGame();
        }

        if ((board.playerGate.getRect()).intersects(board.ball.getRect()) && !model.playerGate.isHit) {
            model.addEnemyGoal();
            model.playerGate.isHit = true;
            updateStat();
        }

        if (!((board.playerGate.getRect()).intersects(board.ball.getRect()))) {
            model.playerGate.isHit = false;
        }

        if ((board.enemyGate.getRect()).intersects(board.ball.getRect()) && !model.enemyGate.isHit) {
            model.addPlayerGoal();
            model.enemyGate.isHit = true;
            updateStat();
        }

        if (!((board.enemyGate.getRect()).intersects(board.ball.getRect()))) {
            model.enemyGate.isHit = false;
        }

        for (int i = 0, j = 0; i < model.enemies.size(); i++) {

            if (model.enemies.get(i).isDestroyed()) {
                j++;
            }

            if (j == model.enemies.size() || model.getPlayerGoals() == Const.MAX_GOALS) {

                if (model.getCurrentLevel() < model.getNumberOfLevels() - 1) {
                    model.clearEnemyList();
                    board.clearEnemyList();
                    model.nullifyGoals();
                    model.breakCombo();
                    model.levelUp();
                    updateStat();
                    model.generateEnemies();
                    addEnemiesOnBoard();
                    board.player.resetState();
                    ballController.sprite.resetState();
                } else {
                    board.message = "Victory";
                    board.score = "Score: " + model.getScore();
                    updateStat();
                    stopGame();
                }

            }
        }

        if ((board.ball.getRect()).intersects(board.player.getRect())) {
            model.breakCombo();
            updateStat();
            int playerLPos = (int) board.player.getRect().getMinX();
            int ballLPos = (int) board.ball.getRect().getMinX();

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

        for (int i = 0; i < model.enemies.size(); i++) {
            if ((board.ball.getRect()).intersects(board.enemies.get(i).getRect())) {

                int ballLeft = (int) board.ball.getRect().getMinX();
                int ballHeight = (int) board.ball.getRect().getHeight();
                int ballWidth = (int) board.ball.getRect().getWidth();
                int ballTop = (int) board.ball.getRect().getMinY();

                Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                Point pointLeft = new Point(ballLeft - 1, ballTop);
                Point pointTop = new Point(ballLeft, ballTop - 1);
                Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                if (!model.enemies.get(i).isDestroyed()) {
                    if (board.enemies.get(i).getRect().contains(pointRight)) {
                        model.ball.setXDir(-1);
                    } else if (board.enemies.get(i).getRect().contains(pointLeft)) {
                        model.ball.setXDir(1);
                    }

                    if (board.enemies.get(i).getRect().contains(pointTop)) {
                        model.ball.setYDir(1);
                    } else if (board.enemies.get(i).getRect().contains(pointBottom)) {
                        model.ball.setYDir(-1);
                    }
                    if (!model.enemies.get(i).isHit()) {
                        model.enemies.get(i).enemyHit();
                        model.enemies.get(i).setHit(true);
                        board.enemies.get(i).setSprite(model.enemies.get(i).getHP());
                    }
                    if (model.enemies.get(i).getHP() == 0) {
                        model.enemies.get(i).setDestroyed(true);
                        board.enemies.get(i).setVisible(false);
                        model.comboIncrement();
                        model.destructionScoreIncrement();
                        updateStat();
                    }
                }
            }
            if (!(board.ball.getRect()).intersects(board.enemies.get(i).getRect())) {
                model.enemies.get(i).setHit(false);
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
            board.player.repaint();
            ballController.move();
            board.ball.repaint();
            checkCollision();
            board.repaint();
        }

    }

    public void addEnemiesOnBoard() {
        for (int index = 0; index < model.enemies.size(); index++) {
            board.enemies.add(new EnemySprite(model.enemies.get(index).getX(), model.enemies.get(index).getY(),
                    model.enemies.get(index).getHP()));
        }
    }

    public void updateStat() {
        board.combo.setText("x" + model.getCombo());
        int level = model.getCurrentLevel()+1;
        board.level.setText("Level " + level);
        board.goals.setText(model.getPlayerGoals() + " : " + model.getEnemyGoals());
        board.scoreLabel.setText("Score: " + model.getScore());
    }


}