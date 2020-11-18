package com.example.lab6;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Random;

public class Helper {
    public static int randInt(int from, int to) {
        Random rand = new Random();
        int r = Math.abs(rand.nextInt());
        return from + r % (to - from + 1);
    }

    public static void updateRect(Canvas canvas, Rectangle rect) {
        Paint paint = new Paint();
        paint.setColor(rect.getColor());
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(rect.getStrokeWidth());
        canvas.drawRect(rect.getX(), rect.getY(), rect.getWidth() + rect.getX(),
                rect.getHeight() + rect.getY(), paint);

        rect.moveX();
        rect.moveY();

        boolean isHorCollision = rect.getX() < 0 || rect.getX() > canvas.getWidth() - rect.getWidth();
        boolean isVerCollision = rect.getY() < 0 || rect.getY() > canvas.getHeight() - rect.getHeight();

        if (isHorCollision || isVerCollision) {
            if (isHorCollision) rect.reverseVx();
            if (isVerCollision) rect.reverseVy();

            int r = Helper.randInt(0, 255);
            int g = Helper.randInt(0, 255);
            int b = Helper.randInt(0, 255);
            rect.setColor(Color.rgb(r, g, b));

            rect.setStrokeWidth(Helper.randInt(Rectangle.minStrokeWidth, Rectangle.maxStrokeWidth));
        }
    }
}
