package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtons();

        btn1.setOnClickListener(getListener(MovingRectangleActivity.class));
        btn2.setOnClickListener(getListener(IndependentRectanglesActivity.class));
        btn3.setOnClickListener(getListener(RectsAndCirclesActivity.class));
        btn4.setOnClickListener(getListener(CirclingSquareActivity.class));
        btn5.setOnClickListener(getListener(CellularAutomataActivity.class));
    }

    private void initButtons() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
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