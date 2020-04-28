package com.saegl.quickcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import java.util.Random;

public class CalcActivity extends AppCompatActivity {
    TextView textView;
    Random r = new Random();

    int correctAnswer;
    int userAnswer;

    int range;
    int problems = 1;
    int right = 0;
    int wrong = 0;

    long starttime = 0;

    String operations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc);

        textView = (TextView) findViewById(R.id.textView);
        Intent intent = getIntent();
        range = intent.getIntExtra("Range", 10) + 1;
        problems = intent.getIntExtra("Problems", 1) - 1;
        operations = intent.getStringExtra("Operations");

        starttime = System.currentTimeMillis();
        updateText();
    }

    private void updateText() {
        int a = r.nextInt(range);
        int b = r.nextInt(range);

        char op = operations.charAt(r.nextInt(operations.length()));

        switch (op) {
            case '+':
                correctAnswer = a + b;
                break;
            case '-':
                // Swap
                if (b > a) {
                    int temp = b;
                    b = a;
                    a = temp;
                }
                correctAnswer = a - b;
                break;
            case '*':
                correctAnswer = a * b;
                break;
            case '/':
                int q = r.nextInt((range / 10) + 1) + 1;
                b = r.nextInt((range / 10) + 1) + 1;
                a = b * q;
                correctAnswer = a / b;
        }
        userAnswer = 0;

        textView.setText(String.format("%d %c %d = ", a, op, b));
    }

    public void numButton(View v) {
        String text = textView.getText().toString();
        userAnswer *= 10;
        Button b = (Button) v;
        String bText = b.getText().toString();
        Log.d("Num", bText);
        text += b.getText();
        userAnswer += Character.digit(bText.charAt(0), 10);
        textView.setText(text);
    }

    public void enterAnswer(View v) {
        if (userAnswer == correctAnswer) {
            right++;
        } else {
            wrong++;
        }
        if (problems == 0) {
            Intent intent = new Intent(this, Result.class);

            intent.putExtra("Right", right);
            intent.putExtra("Wrong", wrong);
            intent.putExtra("StartTime", starttime);

            startActivity(intent);
        } else {
            updateText();
            problems--;
        }
    }
}
