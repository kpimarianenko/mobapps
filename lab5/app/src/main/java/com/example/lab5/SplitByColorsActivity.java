package com.example.lab5;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplitByColorsActivity extends AppCompatActivity {

    ImageView originalView, redView, greenView, blueView;
    Bitmap redMap, greenMap, blueMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_by_colors);
        initViews();

        redMap = Bitmap.createBitmap(MainActivity.mapWidth, MainActivity.mapHeight, Bitmap.Config.ARGB_8888);
        greenMap = Bitmap.createBitmap(MainActivity.mapWidth, MainActivity.mapHeight, Bitmap.Config.ARGB_8888);
        blueMap = Bitmap.createBitmap(MainActivity.mapWidth, MainActivity.mapHeight, Bitmap.Config.ARGB_8888);

        int[] pixels = new int[MainActivity.mapWidth * MainActivity.mapHeight];
        MainActivity.originalMap.getPixels(pixels, 0, MainActivity.mapWidth, 0, 0, MainActivity.mapWidth, MainActivity.mapHeight);

        int[] redPixels = getRedPixels(pixels);
        int[] greenPixels = getGreenPixels(pixels);
        int[] bluePixels = getBluePixels(pixels);

        redMap.setPixels(redPixels, 0, MainActivity.mapWidth, 0, 0, MainActivity.mapWidth, MainActivity.mapHeight);
        greenMap.setPixels(greenPixels, 0, MainActivity.mapWidth, 0, 0, MainActivity.mapWidth, MainActivity.mapHeight);
        blueMap.setPixels(bluePixels, 0, MainActivity.mapWidth, 0, 0, MainActivity.mapWidth, MainActivity.mapHeight);

        originalView.setImageBitmap(MainActivity.originalMap);
        redView.setImageBitmap(redMap);
        greenView.setImageBitmap(greenMap);
        blueView.setImageBitmap(blueMap);
    }

    private void initViews() {
        originalView = findViewById(R.id.original);
        redView = findViewById(R.id.red);
        greenView = findViewById(R.id.green);
        blueView = findViewById(R.id.blue);
    }

    private int[] getRedPixels(int[] pixels) {
        int[] newPixels = new int[pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            int alpha = Color.alpha(pixels[i]);
            int red = Color.red(pixels[i]);
            newPixels[i] = Color.argb(alpha, red, 0, 0);
        }
        return newPixels;
    }

    private int[] getGreenPixels(int[] pixels) {
        int[] newPixels = new int[pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            int alpha = Color.alpha(pixels[i]);
            int green = Color.green(pixels[i]);
            newPixels[i] = Color.argb(alpha, 0, green, 0);
        }
        return newPixels;
    }

    private int[] getBluePixels(int[] pixels) {
        int[] newPixels = new int[pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            int alpha = Color.alpha(pixels[i]);
            int blue = Color.blue(pixels[i]);
            newPixels[i] = Color.argb(alpha, 0, 0, blue);
        }
        return newPixels;
    }
}
