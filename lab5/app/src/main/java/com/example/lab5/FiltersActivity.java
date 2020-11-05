package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Arrays;

public class FiltersActivity extends AppCompatActivity {

    ImageView view;
    Button original, blur, sharpness, median, growingUp, sobel;
    int w = MainActivity.mapWidth, h = MainActivity.mapHeight;
    int [][] pixels2d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
        initViewsAndButtons();

        original.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setImageBitmap(MainActivity.originalMap);
            }
        });

        blur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpOriginalMapTo2DPixels();
                double [][] matrix = new double[][]{
                        {0.000789, 0.006581, 0.013347, 0.006581, 0.000789},
                        {0.006581, 0.054901, 0.111345, 0.054901, 0.006581},
                        {0.013347, 0.111345, 0.225821, 0.111345, 0.013347},
                        {0.006581, 0.054901, 0.111345, 0.054901, 0.006581},
                        {0.000789, 0.006581, 0.013347, 0.006581, 0.000789},
                };

                applyMatrix(matrix, 1);
            }
        });

        sharpness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpOriginalMapTo2DPixels();
                double [][] matrix = new double[][]{
                        {-1, -1, -1},
                        {-1,  9, -1},
                        {-1, -1, -1},
                };

                applyMatrix(matrix, 1);
            }
        });

        median.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpOriginalMapTo2DPixels();
                applyMedianFilter();
            }
        });

        growingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpOriginalMapTo2DPixels();
                double [][] matrix = new double[][]{
                        {0, 0, 0.1, 0, 0},
                        {0, 0.1, 0.1, 0.1, 0},
                        {0.1, 0.1, 0.1, 0.1, 0.1},
                        {0, 0.1, 0.1, 0.1, 0},
                        {0, 0, 0.1, 0, 0},
                };

                applyMatrix(matrix, 1);
            }
        });

        sobel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpOriginalMapTo2DPixels();
                double[][] matrix = new double[][] {
                        { -1, -2, -1 },
                        { 0, 0, 0 },
                        { 1, 2, 1 },
                };

                applySobelFilter(matrix);
            }
        });
    }

    private void setUpOriginalMapTo2DPixels() {
        int [] pixels = new int[w * h];
        MainActivity.originalMap.getPixels(pixels, 0, w, 0,0, w, h);
        pixels2d = pixelsToTwoDimensionalArray(pixels, w);
    }

    private void initViewsAndButtons() {
        view = findViewById(R.id.view);
        original = findViewById(R.id.original);
        blur = findViewById(R.id.blur);
        sharpness = findViewById(R.id.sharpness);
        median = findViewById(R.id.median);
        growingUp = findViewById(R.id.growingUp);
        sobel = findViewById(R.id.sobel);
        view.setImageBitmap(MainActivity.originalMap);
    }

    private void applyMatrix(double [][] matrix, int div) {
        final int subArrSize = matrix.length;
        int level = subArrSize / 2;

        for (int i = level; i < pixels2d.length - level; i++) {
            for (int j = level; j < pixels2d[i].length - level; j++) {
                int [][] subArr = getSubArr(i, j, subArrSize);
                pixels2d[i][j] = multiplyMatrix(subArr, matrix, div);
            }
        }

        setMapFromPixels2D();
    }

    private void applyMedianFilter() {
        final int subArrSize = 21;
        int level = subArrSize / 2;

        for (int i = level; i < pixels2d.length - level; i++) {
            for (int j = level; j < pixels2d[i].length - level; j++) {
                int [][] subArr = getSubArr(i, j, subArrSize);
                pixels2d[i][j] = getMedianColor(subArr);
            }
        }

        setMapFromPixels2D();
    }

    private int getMedianColor(int[][] pixels2d) {
        int red = 0;
        int green = 0;
        int blue = 0;
        int[] pixels = twoDimensionalArrayToPixels(pixels2d);
        int[] tmp = new int[pixels.length];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < pixels.length; j++) {
                if (i == 0) tmp[j] = Color.red(pixels[j]);
                else if (i == 1) tmp[j] = Color.green(pixels[j]);
                else tmp[j] = Color.blue(pixels[j]);
            }
            Arrays.sort(tmp);
            if (i == 0) red = tmp[(int)(pixels.length / 2)];
            else if (i == 1) green = tmp[(int)(pixels.length / 2)];
            else blue = tmp[(int)(pixels.length / 2)];
        }

        int center = pixels.length / 2;
        return Color.argb(Color.alpha(pixels[center]), red, green, blue);
    }

    private void applySobelFilter(double[][] horMatrix) {
        int size = horMatrix.length;
        int level = size / 2;
        double[][] verMatrix = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                verMatrix[i][j] = horMatrix[j][i];
            }
        }

        Bitmap horMap = getBitmapFromMatrixApplying(horMatrix, 1);
        Bitmap verMap = getBitmapFromMatrixApplying(horMatrix, 1);
        int width = horMap.getWidth();
        int height = horMap.getHeight();
        Bitmap finalResult = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] horPixels = new int[width * width];
        int[] verPixels = new int[horPixels.length];
        horMap.getPixels(horPixels, 0, width, 0, 0, width, height);
        verMap.getPixels(verPixels, 0, width, 0, 0, width, height);

        double alpha = 0.5;

        for (int i = 0; i < horPixels.length; i++) {
            int horPixel = horPixels[i];
            int verPixel = verPixels[i];
            int red = (int)(Color.red(horPixel) * alpha + (1 - alpha) * Color.red(verPixel));
            int green = (int)(Color.green(horPixel) * alpha + (1 - alpha) * Color.green(verPixel));
            int blue = (int)(Color.blue(horPixel) * alpha + (1 - alpha) * Color.blue(verPixel));
            horPixels[i] = Color.argb(255, red, green, blue);
        }

        finalResult.setPixels(horPixels, 0, width, 0, 0, width, height);
        view.setImageBitmap(finalResult);
    }

    private int multiplyMatrix(int [][] pixels, double [][] matrix, int div) {
        int length = pixels.length;
        int sumRed = 0;
        int sumGreen = 0;
        int sumBlue = 0;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                sumRed += (int)(Color.red(pixels[i][j]) * matrix[i][j]);
                sumGreen += (int)(Color.green(pixels[i][j]) * matrix[i][j]);
                sumBlue += (int)(Color.blue(pixels[i][j]) * matrix[i][j]);
            }
        }

        sumRed /= div;
        sumGreen /= div;
        sumBlue /= div;

        sumRed = Math.max(Math.min(255, sumRed), 0);
        sumGreen = Math.max(Math.min(255, sumGreen), 0);
        sumBlue = Math.max(Math.min(255, sumBlue), 0);

        int center = (int)(pixels.length / 2);
        return Color.argb(Color.alpha(pixels[center][center]), sumRed, sumGreen, sumBlue);
    }

    private int[][] getSubArr(int i, int j, int size) {
        int level = size / 2;
        int [][] subArr = new int[size][size];
        for (int i_ = i - level, k = 0; k < size; i_++, k++) {
            for (int j_ = j - level, l = 0; l < size; j_++, l++) {
                subArr[k][l] = pixels2d[i_][j_];
            }
        }
        return subArr;
    }

    private int[][] pixelsToTwoDimensionalArray(int [] pixels, int width) {
        int height = (int)(pixels.length / width);
        int [][] pixels2d = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixels2d[i][j] = pixels[i * width + j];
            }
        }
        return pixels2d;
    }

    private int[] twoDimensionalArrayToPixels(int [][] pixels2d) {
        int width = pixels2d[0].length;
        int height = pixels2d.length;
        int [] pixels = new int[width * height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixels[i * width + j] = pixels2d[i][j];
            }
        }
        return pixels;
    }

    private void setMapFromPixels2D() {
        Bitmap map = Bitmap.createBitmap(w, h, MainActivity.originalMap.getConfig());
        map.setPixels(twoDimensionalArrayToPixels(pixels2d), 0, w, 0,0, w, h);
        view.setImageBitmap(map);
    }

    private Bitmap getBitmapFromMatrixApplying(double[][] matrix, int div) {
        final int subArrSize = matrix.length;
        int level = subArrSize / 2;
        int[][] finalPixels = new int[h][w];

        for (int i = level; i < pixels2d.length - level; i++) {
            for (int j = level; j < pixels2d[i].length - level; j++) {
                int [][] subArr = getSubArr(i, j, subArrSize);
                finalPixels[i][j] = multiplyMatrix(subArr, matrix, div);
            }
        }

        Bitmap map = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        map.setPixels(twoDimensionalArrayToPixels(finalPixels), 0,w,0,0,w,h);
        return map;
    }
}