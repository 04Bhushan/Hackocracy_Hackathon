package com.example.hackathone1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Activiyt_LIST extends AppCompatActivity {
TextView name1,name2,name3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activiyt__l_i_s_t);
        String s1 = getIntent().getStringExtra("name");
        name1 = (TextView) findViewById(R.id.name1);
        name2 = (TextView) findViewById(R.id.name2);
        name3 = (TextView) findViewById(R.id.name3);

        name1.setText(s1);name2.setText(s1);
        name3.setText(s1);

    }
}