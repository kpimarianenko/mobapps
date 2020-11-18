package com.example.lab6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

public class IndependentRectanglesView extends View {
    public IndependentRectanglesView(Context context) {
        super(context);
        initRects();
        init(null);
    }

    public IndependentRectanglesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initRects();
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {}

    int rectQuantity = 100;

    int color = Color.RED;
    float strokeWidth = 12;
    int w = 300;
    int h = 200;

    Rectangle[] rects;

    private void initRects() {
        rects = new Rectangle[rectQuantity];
        for(int i = 0; i < rectQuantity; i++) {
            int x = Helper.randInt(0, this.getWidth() - w);
            int y = Helper.randInt(0, this.getHeight() - h);
            int vx = Helper.randInt(Rectangle.minVel, Rectangle.maxVel);
            int vy = Helper.randInt(Rectangle.minVel, Rectangle.maxVel);
            rects[i] = new Rectangle(x, y, vx, vy, w, h, color, strokeWidth);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Rectangle rect : rects) {
            Helper.updateRect(canvas, rect);
        }
        invalidate();
    }
}
