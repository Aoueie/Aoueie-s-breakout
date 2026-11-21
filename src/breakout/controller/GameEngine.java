package breakout.controller;

import breakout.model.Paddle;
import breakout.view.BreakoutCanvas;

import java.awt.event.KeyEvent;

public class GameEngine {

    private final Paddle paddle;
    private final BreakoutCanvas canvas;

    private boolean leftPressed;
    private boolean rightPressed;

    public GameEngine(BreakoutCanvas canvas) {
        this.canvas = canvas;
        this.paddle = new Paddle();
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
    }

    private void render() {
        canvas.render(paddle);
    }

    public void onKeyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            leftPressed = true;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            rightPressed = true;
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