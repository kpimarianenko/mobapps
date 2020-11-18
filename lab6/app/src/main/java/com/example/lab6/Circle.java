package com.example.lab6;

import android.graphics.Color;

public class Circle extends Shape {
    private int r;

    Circle() {
        this.x = 0;
        this.y = 0;
        this.vx = minVel;
        this.vy = minVel;
        this.r = 50;
        this.strokeWidth = minStrokeWidth;
        color = Color.BLACK;
    }

    Circle(int x, int y, int vx, int vy, int r, int color, float strokeWidth) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.r = r;
        this.color = color;
        this.strokeWidth = strokeWidth;
    }

    public int getRadius() {
        return r;
    }

    public void setRadius(int w) {
        this.r = r;
    }
}
