package com.example.hackathone1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class SlashScreen1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //show the activity in full screen
        setContentView(R.layout.activity_slash_screen);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {


                    Intent i = new Intent(SlashScreen1.this,  beforeLogin.class);
                    startActivity(i);
                    finish();


            }
        }, 4000);

    }
}