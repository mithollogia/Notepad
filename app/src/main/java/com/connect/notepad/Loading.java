package com.connect.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.connect.notepad.interfaces.MainActivity;

public class Loading extends AppCompatActivity {
    private TextView title, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        title = findViewById(R.id.title);
        slogan = findViewById(R.id.slogan);

        title.setAlpha(0);
        slogan.setAlpha(0);

        findViewById(R.id.logocirculo).animate().rotation(-720).setDuration(5000).setStartDelay(0).start();
        title.animate().alpha(1).setDuration(3000).setStartDelay(2000).start();
        slogan.animate().alpha(1).setDuration(1000).setStartDelay(3000).start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Loading.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}
