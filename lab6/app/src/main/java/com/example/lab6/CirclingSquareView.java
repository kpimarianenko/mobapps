package com.example.lab6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;


public class CirclingSquareView extends View {
    public CirclingSquareView(Context context) {
        super(context);
        init(null);
    }

    public CirclingSquareView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {}

    int a = 100;
    double pos = 0;
    double velocity = 0.03;
    Rectangle rect = new Rectangle(0, 0, 0, 0, a, a, Color.YELLOW, 0);

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();

        int cenX = getWidth() / 2;
        int cenY = getHeight() / 2;
        int r = (int)(cenX * 0.8);
        pos += velocity;
        double x = cenX + r * Math.cos(pos) - rect.getWidth() / 2;
        double y = cenY + r * Math.sin(pos) - rect.getHeight() / 2;

        System.out.println(pos);
        paint.setColor(Color.rgb(230, 230, 230));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(12);
        canvas.drawCircle(cenX, cenY, r, paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(rect.getColor());
        canvas.drawRect((float)x, (float)(y),
                (float)(x + rect.getWidth()), (float)(y + rect.getHeight()), paint);

        invalidate();
    }
}
