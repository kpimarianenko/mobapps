package com.example.lab6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class RectsAndCirclesView extends View {
    public RectsAndCirclesView(Context context) {
        super(context);
        initRects();
        initCircles();
        init(null);
    }

    public RectsAndCirclesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initRects();
        initCircles();
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {}

    private void initRects() {
        rects = new Rectangle[2];
        for (int i = 0; i < rects.length; i++) {
            int dx = gap;
            int x = dx + i * (w + dx);
            int y = gap;
            int vx = 10;
            int vy = 10;
            rects[i] = new Rectangle(x, y, vx, vy, w, h, color, strokeWidth);
        }
    }

    private void initCircles() {
        circles = new Circle[2];
        for (int i = 0; i < rects.length; i++) {
            int x = rects[i].getX() + rects[i].getWidth() / 2;
            int y = rects[i].getY() + rects[i].getHeight() / 2;
            int vx = 15;
            int vy = 15;
            circles[i] = new Circle(x, y, vx, vy, r, color, strokeWidth);
        }
    }

    int color = Color.BLUE;
    float strokeWidth = 12;
    int gap = 100;
    int r = 50;
    int w = 300;
    int h = 200;

    Rectangle[] rects;
    Circle[] circles;

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < rects.length; i++) {
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(strokeWidth);
            paint.setColor(color);
            updateShapes(canvas, rects[i], circles[i], paint, i == 0);
        }
        invalidate();
    }

    private void updateShapes(Canvas canvas, Rectangle rect, Circle circle, Paint paint, boolean isLeft) {
        canvas.drawRect(rect.getX(), rect.getY(), rect.getWidth() + rect.getX(),
                rect.getHeight() + rect.getY(), paint);
        canvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(), paint);

        rect.moveX();
        rect.moveY();
        circle.moveX();
        circle.moveY();
        updateRect(rect, canvas, isLeft);
        updateCircle(circle, rect, canvas, isLeft);
    }

    public void reverseVx() {
        for (Rectangle rect : rects) {
            rect.reverseVx();
        }
    }

    public void updateRect(Rectangle rect, Canvas canvas, boolean isLeft) {
        boolean isLeftHorCollision = isLeft && rect.getX() < 0;
        boolean isRightHorCollision = !isLeft && rect.getX() > canvas.getWidth() - rect.getWidth();
        boolean isHorCollision = isLeftHorCollision || isRightHorCollision;
        boolean isVerCollision = rect.getY() < 0 || rect.getY() > canvas.getHeight() - rect.getHeight();
        if (isHorCollision || isVerCollision) {
            if (isHorCollision) {
                reverseVx();
                if (isLeft) {
                    rect.moveX();
                    rect.moveX();
                }
            }
            if (isVerCollision) rect.reverseVy();
            int r = Helper.randInt(0, 255);
            int g = Helper.randInt(0, 255);
            int b = Helper.randInt(0, 255);
            color = Color.rgb(r, g, b);
            strokeWidth = Helper.randInt(Rectangle.minStrokeWidth, Rectangle.maxStrokeWidth);
        }
    }

    public void updateCircle(Circle circle, Rectangle rect, Canvas canvas, boolean isLeft) {
        boolean isHorLeftCollision = circle.getX() - circle.getRadius() < rect.getX();
        boolean isHorRightCollision = circle.getX() + circle.getRadius() > rect.getX() + rect.getWidth();
        boolean isHorCollision = isHorLeftCollision || isHorRightCollision;
        boolean isUpVerCollision = circle.getY() - circle.getRadius() < rect.getY();
        boolean isDownVerCollision = circle.getY() + circle.getRadius() > rect.getY() + rect.getHeight();
        boolean isVerCollision = isUpVerCollision || isDownVerCollision;
        if (isHorCollision) {
            circle.reverseVx();
            circle.moveX();
            circle.moveX();
        }
        if (isVerCollision) {
            circle.reverseVy();
            circle.moveY();
            circle.moveY();
        }
    }
}
