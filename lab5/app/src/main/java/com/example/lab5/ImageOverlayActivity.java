package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageOverlayActivity extends AppCompatActivity {

    ImageView original1, modified, original2;
    Bitmap originalMap1, modifiedMap, originalMap2;
    Button increaseAlpha, decreaseAlpha;
    TextView alphaView;
    double alpha = 0.7;
    double step = 0.05;
    int width = 0, height = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_overlay);
        initViews();
        initMaps();
        initButtonListeners();
        setModifiedImage();
    }

    private void initViews() {
        original1 = findViewById(R.id.original1);
        original2 = findViewById(R.id.original2);
        modified = findViewById(R.id.modified);
        increaseAlpha = findViewById(R.id.increase_alpha);
        decreaseAlpha = findViewById(R.id.decrease_alpha);
        alphaView = findViewById(R.id.alpha);
        setAlphaText();
    }

    private void initMaps() {
        originalMap1 = BitmapFactory.decodeResource(getResources(), R.drawable.overlay_image_1);
        originalMap2 = BitmapFactory.decodeResource(getResources(), R.drawable.overlay_image_2);
        original1.setImageBitmap(originalMap1);
        original2.setImageBitmap(originalMap2);
        width = originalMap1.getWidth();
        height = originalMap2.getHeight();
        modifiedMap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    }

    private void initButtonListeners() {
        increaseAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseAlpha();
                setModifiedImage();
                setAlphaText();
            }
        });

        decreaseAlpha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                decreaseAlpha();
                setModifiedImage();
                setAlphaText();
            }
        });
    }

    @SuppressLint("DefaultLocale")
    private void setAlphaText() {
        alphaView.setText(String.format("%.2f", alpha));
    }

    private void decreaseAlpha() {
        alpha -= step;
        alpha = Math.max(0, alpha);
    }

    private void increaseAlpha() {
        alpha += step;
        alpha = Math.min(1, alpha);
    }

    private int[] overlayTwoImages() {
        int[] pixels1 = new int[width * height], pixels2 = new int[width * height], newPixels = new int[width * height];
        originalMap1.getPixels(pixels1, 0, width, 0, 0, width, height);
        originalMap2.getPixels(pixels2, 0, width, 0, 0, width, height);
        for (int i = 0; i < pixels1.length; i++) {
            int a = getOverlayedPixel(Color.alpha(pixels1[i]), Color.alpha(pixels2[i]));
            int r = getOverlayedPixel(Color.red(pixels1[i]), Color.red(pixels2[i]));
            int g = getOverlayedPixel(Color.green(pixels1[i]), Color.green(pixels2[i]));
            int b = getOverlayedPixel(Color.blue(pixels1[i]), Color.blue(pixels2[i]));
            newPixels[i] = Color.argb(a, r, g, b);
        }

        return newPixels;
    }

    private int getOverlayedPixel(int p1, int p2) {
        return (int)(alpha * (double)p1 + (1 - alpha) * (double)p2);
    }

    private void setModifiedImage() {
        int[] pixels = overlayTwoImages();
        modifiedMap.setPixels(pixels, 0, width, 0,0, width, height);
        modified.setImageBitmap(modifiedMap);
    }
}