package kickerbreaker.controller;

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
 * Created by karina on 10-10-2017.
 */
public class Controller {
    public Board board;
    private Model model;

    private Timer timer;

    public Controller() {
        this.board = new Board();
        this.model = new Model();
        board.addKeyListener(new TAdapter());
        board.setFocusable(true);

        board.enemies = new Enemy[N_OF_BRICKS];
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
            stopGame();
        }

        if ((board.enemyGate.getRect()).intersects(board.ball.getRect())) {
            model.goalIncrement();
        }

        for (int i = 0, j = 0; i < N_OF_BRICKS; i++) {

            if (board.enemies[i].isDestroyed()) {
                j++;
            }

            if (j == N_OF_BRICKS) {
                board.message = "Victory";
                stopGame();
            }
        }

        if ((board.ball.getRect()).intersects(board.player.getRect())) {

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

        for (int i = 0; i < N_OF_BRICKS; i++) {

            if ((board.ball.getRect()).intersects(board.enemies[i].getRect())) {

                int ballLeft = (int) board.ball.getRect().getMinX();
                int ballHeight = (int) board.ball.getRect().getHeight();
                int ballWidth = (int) board.ball.getRect().getWidth();
                int ballTop = (int) board.ball.getRect().getMinY();

                Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                Point pointLeft = new Point(ballLeft - 1, ballTop);
                Point pointTop = new Point(ballLeft, ballTop - 1);
                Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                if (!board.enemies[i].isDestroyed()) {
                    if (board.enemies[i].getRect().contains(pointRight)) {
                        board.ball.setXDir(-1);
                    } else if (board.enemies[i].getRect().contains(pointLeft)) {
                        board.ball.setXDir(1);
                    }

                    if (board.enemies[i].getRect().contains(pointTop)) {
                        board.ball.setYDir(1);
                    } else if (board.enemies[i].getRect().contains(pointBottom)) {
                        board.ball.setYDir(-1);
                    }

                    board.enemies[i].setDestroyed(true);
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
}
