package com.example.hackathone1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login1 extends AppCompatActivity {
EditText mob,etPassword123;
Button btnLoginl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        etPassword123 = (EditText) findViewById(R.id.etPassword123);//

        mob = (EditText) findViewById(R.id.mob);//btnLoginl

        btnLoginl = (Button) findViewById(R.id.btnLoginl);//btnLoginl
        btnLoginl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etPassword123.getText().toString().trim().equals("User") && mob.getText().toString().trim().equals("1999888777")){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(Login1.this, "Successfully login!...", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(Login1.this, "error to login!...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}