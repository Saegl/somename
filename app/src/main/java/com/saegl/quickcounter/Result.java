package com.saegl.quickcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        int right = intent.getIntExtra("Right", 0);
        int wrong = intent.getIntExtra("Wrong", 0);

        long starttime = intent.getLongExtra("StartTime", 0);
        long time = (System.currentTimeMillis() - starttime) / 1000;

        TextView res = (TextView) findViewById(R.id.Result);
        res.setText(String.format("Вы решили %d/%d\nЗа %d минут и %d секунд",
                right, right+wrong, time / 60, time % 60));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
