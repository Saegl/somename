package com.saegl.quickcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_complexity);
    }

    public void startCalc(View v) {
        Intent intent = new Intent(this, CalcActivity.class);

        SeekBar bar = (SeekBar) findViewById(R.id.SeekBarComlexity);
        EditText problems = (EditText) findViewById(R.id.problems);
        boolean add = ((Switch) findViewById(R.id.add)).isChecked();
        boolean sub = ((Switch) findViewById(R.id.sub)).isChecked();
        boolean mul = ((Switch) findViewById(R.id.mul)).isChecked();
        boolean div = ((Switch) findViewById(R.id.div)).isChecked();

        if (!(add || sub || mul || div)) {
            Context context = getApplicationContext();
            CharSequence text = "Выберите по крайней мере одну арифметическую операцию";

            Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        if (Integer.parseInt(problems.getText().toString()) <= 0) {
            Context context = getApplicationContext();
            CharSequence text = "Установите как минимум 1 пример";

            Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        String opertations = "";
        if (add) opertations += '+';
        if (sub) opertations += '-';
        if (mul) opertations += '*';
        if (div) opertations += '/';

        intent.putExtra("Range", bar.getProgress() * 10);
        intent.putExtra("Problems", Integer.parseInt(problems.getText().toString()));
        intent.putExtra("Operations", opertations);

        startActivity(intent);
    }
}
