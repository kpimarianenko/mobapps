package com.example.lab6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

public class MovingRectangleView extends View {
    public MovingRectangleView(Context context) {
        super(context);
        init(null);
    }

    public MovingRectangleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {}

    int x = 10;
    int y = 10;
    int vx = 10;
    int vy = 10;
    int color = Color.RED;
    float strokeWidth = 12;
    int w = 200;
    int h = 200;
    Rectangle rect = new Rectangle(x, y, vx, vy, w, h, color, strokeWidth);

    @Override
    protected void onDraw(Canvas canvas) {
        Helper.updateRect(canvas, rect);
        invalidate();
    }
}
