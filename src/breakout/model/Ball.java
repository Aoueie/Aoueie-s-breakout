package breakout.model;

import breakout.util.Constants;
import breakout.model.Paddle;

public class Ball {

    private double x;
    private double y;
    private double vx;
    private double vy;
    private double radius;

    public Ball() {
        this.radius = Constants.BALL_RADIUS;
    }

    public void placeOnPaddle(Paddle paddle){
        this.x = paddle.getCenterX() - radius;
        this.y = paddle.getY() - getDiameter();
        this.vx = 0;
        this.vy = 0;
    }

    public void launch(){
        vy = - Constants.BALL_BASE_SPEED;
    }

    public void move(){
        x += vx;
        y += vy;
    }

    public void getOnCanvas() {
        if (x + getDiameter() > Constants.CANVAS_WIDTH) {
            x = Constants.CANVAS_WIDTH - getDiameter();
        } else if (x < 0) {
            x = 0;
        }
    }

    public void reverseX() {
        vx = - vx;
    }

    public void reverseY() {
        vy = - vy;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public double getDiameter() {
        return radius * 2;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public void bounceOffPaddle(Paddle paddle){
        double deltaX = (x + radius) - paddle.getCenterX();
        double halfWidth = paddle.getWidth() / 2.0;
        double offset = Math.min(1.0, Math.max(-1.0, deltaX / halfWidth));

        double angle = offset * Math.toRadians(Constants.MAX_BOUNCE_ANGLE_DEG);
        double speed = Math.hypot(vx, vy);

        this.vx = speed * Math.sin(angle);
        this.vy = -Math.abs(speed * Math.cos(angle));
        this.y = paddle.getY() - getDiameter();
    }
}
