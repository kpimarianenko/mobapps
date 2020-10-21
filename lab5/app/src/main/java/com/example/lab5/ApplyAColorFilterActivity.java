package com.example.lab5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ApplyAColorFilterActivity  extends AppCompatActivity {
    public static final int additionalIntensity = 80;
    ImageView originalView, filteredView;
    Bitmap filteredMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_images_layout);
        initViews();

        int[] pixels = new int[MainActivity.mapWidth * MainActivity.mapHeight];
        MainActivity.originalMap.getPixels(pixels, 0, MainActivity.mapWidth, 0, 0, MainActivity.mapWidth, MainActivity.mapHeight);
        int[] filteredPixels = filterPixels(pixels);

        filteredMap = Bitmap.createBitmap(MainActivity.mapWidth, MainActivity.mapHeight, Bitmap.Config.ARGB_8888);
        filteredMap.setPixels(filteredPixels, 0, MainActivity.mapWidth, 0, 0, MainActivity.mapWidth, MainActivity.mapHeight);
        originalView.setImageBitmap(MainActivity.originalMap);
        filteredView.setImageBitmap(filteredMap);
    }

    private void initViews() {
        originalView = findViewById(R.id.original);
        filteredView = findViewById(R.id.modified);
    }

    private int[] filterPixels(int[] pixels) {
        for (int i = 0; i < pixels.length; i++) {
            int alpha = Color.alpha(pixels[i]);
            int red = Color.red(pixels[i]) + additionalIntensity;
            int green = Color.green(pixels[i]);
            int blue = Color.blue(pixels[i]) + additionalIntensity;
            red = Math.min(red, MainActivity.maxColorIntensity);
            blue = Math.min(blue, MainActivity.maxColorIntensity);
            pixels[i] = Color.argb(alpha, red, green, blue);
        }
        return pixels;
    }
}
