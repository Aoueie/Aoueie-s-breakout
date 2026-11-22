package breakout.controller;

import breakout.model.Ball;
import breakout.model.Paddle;
import breakout.util.Constants;
import breakout.view.BreakoutCanvas;

import java.awt.event.KeyEvent;

public class GameEngine {

    private final Ball ball;
    private final Paddle paddle;
    private final BreakoutCanvas canvas;

    private boolean ballLaunched;
    private boolean leftPressed;
    private boolean rightPressed;

    public GameEngine(BreakoutCanvas canvas) {
        this.canvas = canvas;
        this.paddle = new Paddle();
        this.ball = new Ball();
        this.ballLaunched = false;
        ball.placeOnPaddle(paddle);
        render();
    }

    public void tick() {
        update();
        render();
    }

    private void update() {
        if (leftPressed) {
            paddle.moveLeft();
        }
        if (rightPressed) {
            paddle.moveRight();
        }
        if (!ballLaunched){
            ball.placeOnPaddle(paddle);
        }else {
            ball.move();
            if (ball.getX() < 0 || ball.getX() + ball.getDiameter() > Constants.CANVAS_WIDTH){
                ball.reverseX();
            }
            if (ball.getY() < Constants.HUD_HEIGHT){
                ball.reverseY();
            }
        }
    }

    private void render() {
        canvas.render(paddle, ball);
    }

    public void onKeyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            leftPressed = true;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        } else if (keyCode == KeyEvent.VK_SPACE && !ballLaunched){
            ball.launch();
            ballLaunched = true;
        }
    }

    public void onKeyReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            leftPressed = false;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}