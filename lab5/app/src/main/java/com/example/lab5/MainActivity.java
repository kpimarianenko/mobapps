package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button inverseImage, applyAColorFilter, splitByColors, imageOverlay, task4, task5, task6;
    public static final int maxColorIntensity = 255;
    static Bitmap originalMap;
    public static int mapWidth, mapHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtons();
        initBitmap();

        inverseImage.setOnClickListener(getListener(InverseImageActivity.class));
        applyAColorFilter.setOnClickListener(getListener(ApplyAColorFilterActivity.class));
        splitByColors.setOnClickListener(getListener(SplitByColorsActivity.class));
        imageOverlay.setOnClickListener(getListener(ImageOverlayActivity.class));
    }

    private void initBitmap() {
        originalMap = BitmapFactory.decodeResource(getResources(), R.drawable.garold);
        mapWidth = originalMap.getWidth();
        mapHeight = originalMap.getHeight();
    }

    private void initButtons() {
        inverseImage = findViewById(R.id.inverse_image);
        applyAColorFilter = findViewById(R.id.apply_a_color_filter);
        splitByColors = findViewById(R.id.split_by_colors);
        imageOverlay = findViewById(R.id.image_overlay);
    }

    private View.OnClickListener getListener(final Class<?> cl) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, cl);
                startActivity(intent);
            }
        };
    }
}