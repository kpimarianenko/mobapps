package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static int maxColorIntensity = 255;
    ImageView originalView, invertedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        originalView = findViewById(R.id.original);
//        invertedView = findViewById(R.id.inverted);
//
//        Bitmap originalMap = BitmapFactory.decodeResource(getResources(), R.drawable.garold);
//        int width = originalMap.getWidth();
//        int height = originalMap.getHeight();
//
//        int[] pixels = new int[width * height];
//        originalMap.getPixels(pixels, 0, width, 0, 0, width, height);
//        int[] invertedPixels = invertPixels(pixels);
//
//        Bitmap invertedMap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//        invertedMap.setPixels(invertedPixels, 0, width, 0, 0, width, height);
//        originalView.setImageBitmap(originalMap);
//        invertedView.setImageBitmap(invertedMap);
    }

    private int[] invertPixels(int[] pixels) {
        for (int i = 0; i < pixels.length; i++) {
            int alpha = Color.alpha(pixels[i]);
            int red = maxColorIntensity - Color.red(pixels[i]);
            int green = maxColorIntensity - Color.green(pixels[i]);
            int blue = maxColorIntensity - Color.blue(pixels[i]);
            pixels[i] = Color.argb(alpha, red, green, blue);
        }
        return pixels;
    }
}