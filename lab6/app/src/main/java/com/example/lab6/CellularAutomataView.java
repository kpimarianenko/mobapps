package com.example.lab6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.BitSet;

public class CellularAutomataView extends View {
    public CellularAutomataView(Context context) {
        super(context);
        init(null);
    }

    public CellularAutomataView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {}

    int n = 99;
    int rule = 0;
    int m;
    int cellSize;
    boolean[][] cells;
    int w;
    int h;
    int gap;

    @Override
    protected void onDraw(Canvas canvas) {
        if (w == 0 && h == 0) {
            w = getWidth();
            h = getHeight();
            cellSize = w / n;
            m = h / cellSize;
            gap = (w - cellSize * n) / 2;
            cells = new boolean[m][n];
            initFirstLevel();
        }

        applyRule(rule);
        drawCells(canvas);
        rule++;
        timeout(500);
        invalidate();
    }

    public void drawCells(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        for(int i = 0; i < cells.length; i++) {
            for(int j = 0; j < cells[i].length; j++) {
                if (cells[i][j]) {
                    int x = gap + j * cellSize;
                    int y = gap + i * cellSize;
                    canvas.drawRect(x, y, x + cellSize, y + cellSize, paint);
                }
            }
        }
    }

    public void initFirstLevel() {
        Arrays.fill(cells[0], false);
        cells[0][cells[0].length / 2] = true;
    }

    public void applyRule(int rule) {
        rule %= 256;
        for (int i = 1; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                boolean[] boolArr = getBoolArrByIJ(i, j);
                int n = getIntFromBoolArr(boolArr);
                cells[i][j] = getNthBit(rule, n);
            }
        }
    }

    private int getIntFromBoolArr(boolean[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[arr.length - i - 1]) sum += Math.pow(2, i);
        }
        return sum;
    }

    private boolean getNthBit(int num, int pos) {
        return ((num) & (0x01 << pos)) > 0;
    }

    private boolean[] getBoolArrByIJ(int i, int j) {
        if (i == 0) return new boolean[]{false, false, false};
        boolean[] arr = new boolean[3];
        if (j == 0) {
            arr[0] = false;
            arr[1] = cells[i - 1][j];
            arr[2] = cells[i - 1][j + 1];
        } else if (j == cells[i].length - 1) {
            arr[0] = cells[i - 1][j - 1];
            arr[1] = cells[i - 1][j];
            arr[2] = false;
        } else {
            arr[0] = cells[i - 1][j - 1];
            arr[1] = cells[i - 1][j];
            arr[2] = cells[i - 1][j + 1];
        }
        return arr;
    }

    private void timeout(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
