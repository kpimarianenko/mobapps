package com.example.task3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    Button calculate;
    TextView result;
    Spinner operators;
    TextInputLayout firstNum;
    TextInputLayout secondNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculate = findViewById(R.id.calculate);
        result = findViewById(R.id.result);
        operators = findViewById(R.id.operators);
        firstNum = findViewById(R.id.first_num);
        secondNum = findViewById(R.id.second_num);

        calculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String firstValueStr = firstNum.getEditText().getText().toString();
                String secondValueStr = secondNum.getEditText().getText().toString();
                String operator = operators.getSelectedItem().toString();

                if (firstValueStr.length() <= 0 || secondValueStr.length() <= 0) {
                    result.setText(R.string.input_error);
                    result.setTextColor(getResources().getColor(R.color.error_color));
                    return;
                }

                double firstValue = Double.parseDouble(firstValueStr);
                double secondValue = Double.parseDouble(secondValueStr);
                double resultValue;

                switch (operator) {
                    case "+": {
                        resultValue = firstValue + secondValue;
                        break;
                    }
                    case "-": {
                        resultValue = firstValue - secondValue;
                        break;
                    }
                    case "*": {
                        resultValue = firstValue * secondValue;
                        break;
                    }
                    case "/": {
                        resultValue = firstValue / secondValue;
                        break;
                    }
                    default: {
                        resultValue = 0;
                        break;
                    }
                }

                result.setText(Double.toString(resultValue));
                result.setTextColor(getResources().getColor(R.color.result_color));
            }
        });
    }
}