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






}
