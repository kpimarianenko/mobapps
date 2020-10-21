package com.example.lab5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class InverseImageActivity extends AppCompatActivity {

    ImageView originalView, invertedView;
    Bitmap invertedMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_images_layout);
        initViews();

        int[] pixels = new int[MainActivity.mapWidth * MainActivity.mapHeight];
        MainActivity.originalMap.getPixels(pixels, 0, MainActivity.mapWidth, 0, 0, MainActivity.mapWidth, MainActivity.mapHeight);
        int[] invertedPixels = invertPixels(pixels);

        invertedMap = Bitmap.createBitmap(MainActivity.mapWidth, MainActivity.mapHeight, Bitmap.Config.ARGB_8888);
        invertedMap.setPixels(invertedPixels, 0, MainActivity.mapWidth, 0, 0, MainActivity.mapWidth, MainActivity.mapHeight);
        originalView.setImageBitmap(MainActivity.originalMap);
        invertedView.setImageBitmap(invertedMap);
    }

    private void initViews() {
        originalView = findViewById(R.id.original);
        invertedView = findViewById(R.id.modified);
    }

    private int[] invertPixels(int[] pixels) {
        for (int i = 0; i < pixels.length; i++) {
            int alpha = Color.alpha(pixels[i]);
            int red = MainActivity.maxColorIntensity - Color.red(pixels[i]);
            int green = MainActivity.maxColorIntensity - Color.green(pixels[i]);
            int blue = MainActivity.maxColorIntensity - Color.blue(pixels[i]);
            pixels[i] = Color.argb(alpha, red, green, blue);
        }
        return pixels;
    }
}
