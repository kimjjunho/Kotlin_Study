package com.example.calendarkt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class StartActivity extends AppCompatActivity {

    /*int[] images = new int[]{R.drawable.brightlight, R.drawable.brighttree, R.drawable.greensky,
            R.drawable.korean,R.drawable.moon,R.drawable.purplesky,R.drawable.redtree,R.drawable.sky,
            R.drawable.star,R.drawable.space};*/
    int[] images = new int[]{R.drawable.moon};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ImageView imageView = findViewById(R.id.backgroundImage);
        int imageld = (int)(Math.random()*images.length);
        imageView.setBackgroundResource(images[imageld]);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(StartActivity.this, LoginActivity.class));
                finish();
            }
        },1000L);
    }

}