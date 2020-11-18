package com.example.lab6;

import android.graphics.Color;

public class Rectangle extends Shape{
    private int w;
    private int h;

    Rectangle() {
        this.x = 0;
        this.y = 0;
        this.vx = minVel;
        this.vy = minVel;
        this.w = 100;
        this.h = 100;
        this.strokeWidth = minStrokeWidth;
        color = Color.BLACK;
    }

    Rectangle(int x, int y, int vx, int vy, int w, int h, int color, float strokeWidth) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.w = w;
        this.h = h;
        this.color = color;
        this.strokeWidth = strokeWidth;
    }

    public int getHeight() {
        return h;
    }

    public int getWidth() {
        return w;
    }

    public void setWidth(int w) {
        this.w = w;
    }

    public void setHeight(int h) {
        this.h = h;
    }
}
