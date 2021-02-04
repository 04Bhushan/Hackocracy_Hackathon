package com.example.hackathone1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class beforeLogin extends AppCompatActivity {
CardView CV_login,CV_REgister;
    ViewPager2 viewPager2;
    private Handler sliderHander1 =  new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_login);
        CV_REgister = (CardView) findViewById(R.id.CV_REgister);
        CV_REgister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Register1.class);
                startActivity(intent);
            }
        });
        CV_login = (CardView) findViewById(R.id.CV_login);
        CV_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login1.class);
                startActivity(intent);
            }
        });
        //*******************************************************************************
        viewPager2 = (ViewPager2) findViewById(R.id.viewPager2);
        //viewPager2_Second = (ViewPager2) findViewById(R.id.viewPager2_Second);
        //Here i am preparing a list of images from drawable ,
        // Or you can get it from API as well.
        List<SliderItem_VP2> sliderItem_vp2 = new ArrayList<>();
        sliderItem_vp2.add(new SliderItem_VP2(R.drawable.p1,0));
        sliderItem_vp2.add(new SliderItem_VP2(R.drawable.p2,1));
        sliderItem_vp2.add(new SliderItem_VP2(R.drawable.p3,2));
        sliderItem_vp2.add(new SliderItem_VP2(R.drawable.p4,3));
        sliderItem_vp2.add(new SliderItem_VP2(R.drawable.p5,4));


        viewPager2.setAdapter(new SliderAdapter_ForVP2(sliderItem_vp2,viewPager2,getApplicationContext()));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
//        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
//            @Override
//            public void transformPage(@NonNull View page, float position) {
//                float r = 1 - Math.abs(position);
//               page.setScaleY(0.85f + r + 0.15f);
//                //page.setScaleX(0.85f + r + 0.15f);
//
//            }
//        });
        //below code for looping the sequence of images
        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHander1.removeCallbacks(sliderRunnable);
                sliderHander1.postDelayed(sliderRunnable,4000);// Slide time duration
            }
        });
        //*****************************************
//        NextPage1 = (Button) findViewById(R.id.NextPage1);
//        NextPage1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(VP2_Activity1.this, VP2_Activity2.class);
//                startActivity(intent);
//            }
//        });

        //*********************************************************************************************

    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() +1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderHander1.removeCallbacks(sliderRunnable);
    }

    protected void onResume(){
        super.onResume();
        sliderHander1.postDelayed(sliderRunnable,4000);
    }
}