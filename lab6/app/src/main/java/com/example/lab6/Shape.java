package com.example.lab6;

public class Shape {
    static final int maxStrokeWidth = 50;
    static final int minStrokeWidth = 5;
    static final int minVel = 10;
    static final int maxVel = 20;

    protected int color;
    protected float strokeWidth;
    protected int x;
    protected int y;
    protected int vx;
    protected int vy;

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public int getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVx() {
        return vx;
    }

    public int getVy() {
        return vy;
    }

    public void setVx(int vx) {
        int vxa = Math.abs(vx);
        int vxs = vx / vxa;
        if (vxa >= maxVel) this.vx = vxs * maxVel;
        else if (vxa <= minVel) this.vx = vxs * minVel;
        else this.vx = vxs * vxa;
    }

    public void setVy(int vy) {
        int vya = Math.abs(vy);
        int vys = vy / vya;
        if (vya >= maxVel) this.vy = vys * maxVel;
        else if (vya <= minVel) this.vy = vys * minVel;
        else this.vy = vys * vya;
    }

    public void moveX() {
        this.x += this.vx;
    }

    public void moveY() {
        this.y += this.vy;
    }

    public void reverseVx() {
        this.vx *= -1;
    }

    public void reverseVy() {
        this.vy *= -1;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setStrokeWidth(int strokeWidth) {
        if (strokeWidth >= maxStrokeWidth) this.strokeWidth = maxStrokeWidth;
        else this.strokeWidth = Math.max(strokeWidth, minStrokeWidth);
    }
}
