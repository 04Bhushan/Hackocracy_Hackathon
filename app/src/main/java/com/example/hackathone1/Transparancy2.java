package com.example.hackathone1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Transparancy2 extends Activity {
LinearLayout LL_Home_Drawer,home1,nav_search1,logOut1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_transparancy2);
        home1 = (LinearLayout) findViewById(R.id.home1);
        home1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
                //Toast.makeText(getApplicationContext(),"Thank you to perseu our Membership", Toast.LENGTH_LONG).show();

            }
        });
        closedDrwaer1();
        goToSearch();
    }
    public void goToSearch(){
        nav_search1= (LinearLayout)findViewById(R.id.nav_search1);
        nav_search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Transparancy2.this, "Setting", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Map1.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void closedDrwaer1(){

        LL_Home_Drawer = (LinearLayout) findViewById(R.id.main_drawer);
        LL_Home_Drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LL_Home_Drawer.setBackground(null);
                LL_Home_Drawer.setBackgroundColor(Color.parseColor("#10000000"));
                // Toast.makeText(Transparancy2.this, "Hello", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        logOut1 = (LinearLayout) findViewById(R.id.logOut1);
        logOut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),beforeLogin.class);
                startActivity(intent);
                finish();
            }
        });

    }


    //code for animation of clossing "Transparancy2.xml"
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

}