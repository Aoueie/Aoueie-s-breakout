package breakout.model;

import breakout.util.Constants;

public class Paddle {

    private double x;
    private final double y;
    private final double width;
    private final double height;
    private final double speed;

    public Paddle() {
        this.width = Constants.PADDLE_WIDTH;
        this.height = Constants.PADDLE_HEIGHT;
        this.y = Constants.PADDLE_Y;
        this.speed = Constants.PADDLE_SPEED;
        reset();
    }

    public void reset() {
        this.x = (Constants.CANVAS_WIDTH - width) / 2.0;
    }

    public void moveLeft() {
        x = Math.max(Constants.PADDLE_MIN_X, x - speed);
    }

    public void moveRight() {
        x = Math.min(Constants.PADDLE_MAX_X, x + speed);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getCenterX() {
        return x + width / 2.0;
    }
}