package kickerbreaker.controller;

import kickerbreaker.model.Const;
import kickerbreaker.model.Model;
import kickerbreaker.view.Ball;
import kickerbreaker.view.Board;
import kickerbreaker.view.Enemy;
import kickerbreaker.view.Player;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import static kickerbreaker.model.Const.*;

/**
 * Created by karina on 04-10-2017.
 */
public class Controller {
    public Board board;
    private Model model;

    private Timer timer;

    public Controller() {
        this.board = new Board();
        this.model = new Model();
        model.generateEnemies();
        addEnemiesOnBoard();
        board.addKeyListener(new TAdapter());
        board.setFocusable(true);
        board.setDoubleBuffered(true);
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), DELAY, PERIOD);
    }

    private void stopGame() {

        board.ingame = false;
        timer.cancel();
    }

    private void checkCollision() {

        if ((board.playerGate.getRect()).intersects(board.ball.getRect())) {
            board.score += model.getScore()+"";
            stopGame();
        }

        if ((board.enemyGate.getRect()).intersects(board.ball.getRect()) && !board.enemyGate.isHit) {
                model.goalIncrement();
            board.enemyGate.isHit = true;

        }

        for (int i = 0, j = 0; i < board.enemies.size(); i++) {

            if (board.enemies.get(i).isDestroyed()) {
                j++;
            }

            if (j == board.enemies.size()) {

                if (model.getCurrentLevel() == Const.LEVELS){
                    board.message = "Victory";
                    board.score += model.getScore()+"";
                    stopGame();
                }
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                model.clearEnemyList();
                model.levelUp();
                model.generateEnemies();
                board.clearEnemyList();
                addEnemiesOnBoard();
            }
        }

        if ((board.ball.getRect()).intersects(board.player.getRect())) {

            board.enemyGate.isHit = false;

            int playerLPos = (int) board.player.getRect().getMinX();
            int ballLPos = (int) board.ball.getRect().getMinX();

            int first = playerLPos + 8;
            int second = playerLPos + 16;
            int third = playerLPos + 24;
            int fourth = playerLPos + 32;

            if (ballLPos < first) {
                board.ball.setXDir(-1);
                board.ball.setYDir(-1);
            }

            if (ballLPos >= first && ballLPos < second) {
                board.ball.setXDir(-1);
                board.ball.setYDir(-1 * board.ball.getYDir());
            }

            if (ballLPos >= second && ballLPos < third) {
                board.ball.setXDir(0);
                board.ball.setYDir(-1);
            }

            if (ballLPos >= third && ballLPos < fourth) {
                board.ball.setXDir(1);
                board.ball.setYDir(-1 * board.ball.getYDir());
            }

            if (ballLPos > fourth) {
                board.ball.setXDir(1);
                board.ball.setYDir(-1);
            }
        }

        for (int i = 0; i < board.enemies.size(); i++) {

            if ((board.ball.getRect()).intersects(board.enemies.get(i).getRect())) {

                int ballLeft = (int) board.ball.getRect().getMinX();
                int ballHeight = (int) board.ball.getRect().getHeight();
                int ballWidth = (int) board.ball.getRect().getWidth();
                int ballTop = (int) board.ball.getRect().getMinY();

                Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                Point pointLeft = new Point(ballLeft - 1, ballTop);
                Point pointTop = new Point(ballLeft, ballTop - 1);
                Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                if (!board.enemies.get(i).isDestroyed()) {
                    if (board.enemies.get(i).getRect().contains(pointRight)) {
                        board.ball.setXDir(-1);
                    } else if (board.enemies.get(i).getRect().contains(pointLeft)) {
                        board.ball.setXDir(1);
                    }

                    if (board.enemies.get(i).getRect().contains(pointTop)) {
                        board.ball.setYDir(1);
                    } else if (board.enemies.get(i).getRect().contains(pointBottom)) {
                        board.ball.setYDir(-1);
                    }

                    board.enemies.get(i).setDestroyed(true);
                    model.destructionIncrement();
                }
            }
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            board.player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            board.player.keyPressed(e);
        }
    }

    private class ScheduleTask extends TimerTask {

        @Override
        public void run() {

            board.ball.move();
            board.player.move();
            checkCollision();
            board.repaint();
        }

    }

    public void addEnemiesOnBoard(){
        for (int index = 0; index < model.enemies.size(); index++){
            board.enemies.add(model.enemies.get(index));
        }
    }
}
