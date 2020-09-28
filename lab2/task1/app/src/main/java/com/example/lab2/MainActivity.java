package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button_1;
    Button button_2;
    Button button_3;
    Button button_4;
    Button button_5;
    Button button_6;
    Button button_7;
    Button button_8;
    Button button_9;
    Button button_0;
    Button button_plus;
    Button button_minus;
    Button button_multiply;
    Button button_divide;
    Button button_erase;
    Button button_equal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_7 = findViewById(R.id.button_7);
        button_0 = findViewById(R.id.button_0);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);
        button_plus = findViewById(R.id.button_plus);
        button_minus = findViewById(R.id.button_minus);
        button_multiply = findViewById(R.id.button_multiply);
        button_divide = findViewById(R.id.button_divide);
        button_erase = findViewById(R.id.button_erase);
        button_equal = findViewById(R.id.button_equal);

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScreenStringWithChar('1');
            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScreenStringWithChar('2');
            }
        });

        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScreenStringWithChar('3');
            }
        });

        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScreenStringWithChar('4');
            }
        });

        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScreenStringWithChar('5');
            }
        });

        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScreenStringWithChar('6');
            }
        });

        button_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScreenStringWithChar('7');
            }
        });

        button_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScreenStringWithChar('8');
            }
        });

        button_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScreenStringWithChar('9');
            }
        });

        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScreenStringWithChar('0');
            }
        });

        button_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScreenStringWithChar('+');
            }
        });

        button_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {updateScreenStringWithChar('-');
            }
        });

        button_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScreenStringWithChar('*');
            }
        });

        button_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScreenStringWithChar('/');
            }
        });

        button_erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScreenStringWithChar('C');
            }
        });

        button_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScreenStringWithChar('=');
            }
        });
    }

    private void updateScreenStringWithChar(char ch) {
        TextView tv = findViewById(R.id.screen);
        String screenStr = tv.getText().toString();
        if (screenStr.contains("=")) screenStr = "0";
        screenStr = updateStringWithChar(screenStr, ch);
        tv.setText(screenStr);
    }

    private String updateStringWithChar(String screenStr, char ch) {
        if (Character.isDigit(ch)) {
            if ((screenStr.length() > 0 && screenStr.charAt(screenStr.length() - 1) == '0' && screenStr.length() == 1) || (screenStr.length() >= 2 && screenStr.charAt(screenStr.length() - 1) == '0' && !Character.isDigit(screenStr.charAt(screenStr.length() - 2))))
                screenStr = replaceLastCharInStr(screenStr, ch);
            else screenStr += ch;
        }
        else if (ch == 'C') {
            screenStr = screenStr.substring(0, screenStr.length() - 1);
            if (screenStr.length() == 0) screenStr += '0';
        }
        else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            if (screenStr.contains("+") || screenStr.contains("-") || screenStr.contains("*") || screenStr.contains("/")) {
                char lastCh = screenStr.charAt(screenStr.length() - 1);
                if (lastCh == '+' || lastCh == '-' || lastCh == '*' || lastCh == '/')
                    screenStr = replaceLastCharInStr(screenStr, ch);
            }
            else screenStr += ch;
        }
        else if (ch == '=') screenStr = parseAndCalculate(screenStr);
        return screenStr;
    }

    private String replaceLastCharInStr(String str, char ch) {
        return str.substring(0, str.length() - 1) + ch;
    }

    private String parseAndCalculate(String screenStr) {
        ExampleParts parts = new ExampleParts(screenStr);

        if (parts.getLeftPart() < 0 || parts.getRightPart() < 0 || parts.getOperator() == '\0')
            return screenStr;
        return screenStr + '=' + parts.calculate();
    }
}

class ExampleParts {
    private int leftPart;
    private int rightPart;
    private char operator;

    ExampleParts(String str) {

        StringBuilder leftPart_ = new StringBuilder();
        StringBuilder rightPart_ = new StringBuilder();
        StringBuilder operator_ = new StringBuilder();
        boolean isOperatorWasHere = false;


        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                if (!isOperatorWasHere) {
                    leftPart_.append(ch);
                } else {
                    rightPart_.append(ch);
                }
            }
            else if ((ch == '+' || ch == '-' || ch == '*' || ch == '/') && !isOperatorWasHere) {
                operator_.append(ch);
                isOperatorWasHere = true;
            }
        }

        leftPart = (leftPart_.length() > 0) ? Integer.parseInt(leftPart_.toString()) : -1;
        rightPart = (rightPart_.length() > 0) ? Integer.parseInt(rightPart_.toString()) : -1;
        operator = (operator_.length() > 0) ? operator_.charAt(0) : '\0';
    }

    public int getLeftPart() {
        return leftPart;
    }

    public int getRightPart() {
        return rightPart;
    }

    public int getOperator() {
        return operator;
    }

    public double calculate() {
        if (leftPart < 0 || rightPart < 0 || operator == '\0')
            return -1;
        switch (operator) {
            case '+':
                return leftPart + rightPart;
            case '-':
                return leftPart - rightPart;
            case '*':
                return leftPart * rightPart;
            case '/':
                return (double) leftPart / (double) rightPart;
            default:
                return -1;
        }
    }
}