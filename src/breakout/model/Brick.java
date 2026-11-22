package breakout.model;

import breakout.util.Constants;

public class Brick  {

    private final double x;
    private final double y;
    private final double width;
    private final double height;
    private final int row;
    private final int maxHitPoints;
    private int hitPoints;

    public Brick(double x, double y, int row) {
        this(x, y, row, 1);
    }

    public Brick(double x, double y, int row, int hitPoints) {
        this.x = x;
        this.y = y;
        this.width = Constants.BRICK_WIDTH;
        this.height = Constants.BRICK_HEIGHT;
        this.row = row;
        this.maxHitPoints = Math.max(1, hitPoints);
        this.hitPoints = this.maxHitPoints;
    }

    public boolean hit() {
        if (isDestroyed()) {
            return false;
        }
        hitPoints--;
        return isDestroyed();
    }

    public boolean isDestroyed() {
        return hitPoints <= 0;
    }

    public void reset() {
        this.hitPoints = maxHitPoints;
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

    public int getRow() {
        return row;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }
}
