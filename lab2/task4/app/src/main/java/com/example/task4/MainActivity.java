package com.example.task4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    TableLayout tl;
    boolean isTic = true;
    boolean isWin = false;
    ArrayList<ArrayList<Button>> buttons = new ArrayList<>();
    Button restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        tl = findViewById(R.id.tableLayout);
        restart = findViewById(R.id.restart);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWin = false;
                tv.setText("");

                for (int i = 0; i < buttons.size(); i++) {
                    for (int j = 0; j < buttons.get(i).size(); j++) {
                        buttons.get(i).get(j).setText(R.string.button_placeholder);
                    }
                }
            }
        });

        for (int i = 0; i < tl.getChildCount(); i++) {
            TableRow row = (TableRow)tl.getChildAt(i);
            buttons.add(new ArrayList<Button>());
            for (int j = 0; j < row.getChildCount(); j++) {
                Button button = (Button) row.getChildAt(j);
                buttons.get(i).add(button);
            }
        }
    }

    public void processClick(View view) {
        Button button = (Button) view;

        if (button.getText() != getResources().getString(R.string.tic) && button.getText() != getResources().getString(R.string.tac) && !isWin) {
            setTicOrTac(button);
            if (checkHorizontalCells() || checkVerticalCells() || checkDiagonalCells()) {
                isWin = true;
                tv.setText(isTic ? R.string.tac_won : R.string.tic_won);
            }

        }
    }

    private void setTicOrTac(Button button) {
        if (isTic)
            button.setText(R.string.tic);
        else
            button.setText(R.string.tac);
        isTic = !isTic;
    }

    private boolean checkHorizontalCells() {
        for (int i = 0; i < buttons.size(); i++) {
            String iterator = buttons.get(i).get(0).getText().toString();
            boolean res = true;

            for (int j = 1; j < buttons.get(i).size() && res; j++) {
                String iteratorNext = buttons.get(i).get(j).getText().toString();
                res = iteratorNext.equals(iterator);
            }

            if (res && (iterator.equals(getResources().getString(R.string.tic)) || iterator.equals(getResources().getString(R.string.tac))))
                return true;
        }
        return false;
    }

    private boolean checkVerticalCells() {
        for (int i = 0; i < buttons.size(); i++) {
            String iterator = buttons.get(0).get(i).getText().toString();
            boolean res = true;

            for (int j = 1; j < buttons.get(i).size() && res; j++) {
                String iteratorNext = buttons.get(j).get(i).getText().toString();
                res = iteratorNext.equals(iterator);
            }

            if (res && (iterator.equals(getResources().getString(R.string.tic)) || iterator.equals(getResources().getString(R.string.tac))))
                return true;
        }
        return false;
    }

    private boolean checkDiagonalCells() {
        boolean res = true;
        String iteratorLeft = buttons.get(0).get(0).getText().toString();
        for (int i = 1; i < buttons.size() && res; i++) {
           String iteratorNext = buttons.get(i).get(i).getText().toString();
           res = iteratorNext.equals(iteratorLeft);
        }
        if (res && (iteratorLeft.equals(getResources().getString(R.string.tic)) || iteratorLeft.equals(getResources().getString(R.string.tac))))
            return true;
        res = true;
        String iteratorRight = buttons.get(buttons.size() - 1).get(0).getText().toString();
        for (int i = 1; i < buttons.size() && res; i++) {
            String iteratorNext = buttons.get(buttons.size() - i - 1).get(i).getText().toString();
            res = iteratorNext.equals(iteratorRight);
        }
        return res && (iteratorRight.equals(getResources().getString(R.string.tic)) || iteratorRight.equals(getResources().getString(R.string.tac)));
    }
}