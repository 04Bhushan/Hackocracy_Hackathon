package com.example.hackathone1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    ViewPager2 viewPager2_Second;
    LinearLayout search_bar_LL,show_search_bar_CV,Image_Three_bAr;
    private Handler sliderHander =  new Handler();
    Spinner m_status;
    int i =0;
    ImageView image_cross,image_search;

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);
        //************************************************
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        client = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLacation();
        }
        else {
            ActivityCompat.requestPermissions(MainActivity.this,new  String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }
        //***************************************************************************************************
        m_status = (Spinner) findViewById(R.id.m_status);
        ArrayAdapter adpater_work = ArrayAdapter.createFromResource(this, R.array.works_of_labours, android.R.layout.simple_spinner_item);
        m_status.setAdapter(adpater_work);

        m_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int a = 18;
                TextView tv = (TextView) view;
                tv.setTextColor(Color.parseColor("#2B2929"));
                tv.setTextSize(a);
                if(position == 0){

                }
                else {
                    Intent intent = new Intent(getApplicationContext(),Activiyt_LIST.class);
                    intent.putExtra("name",m_status.getSelectedItem().toString().trim());
                    startActivity(intent);
                    finish();
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        //***************************************************************************************************************************


        ImageView image_cross, image_search;
        image_cross = (ImageView) findViewById(R.id.image_cross);//
        Image_Three_bAr = (LinearLayout) findViewById(R.id.Image_Three_bAr);//Image_Three_bAr
        Image_Three_bAr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Transparancy2.class);
                startActivity(intent);
                overridePendingTransition(R.anim.left_to_right, R.anim.slide_out_left);//code
            }
        });
        image_search = (ImageView) findViewById(R.id.image_search);//
        search_bar_LL = (LinearLayout) findViewById(R.id.search_bar_LL);//
        show_search_bar_CV = (LinearLayout) findViewById(R.id.show_search_bar_CV);//show_search_bar_CV
        show_search_bar_CV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i%2 == 0){
                    search_bar_LL.setVisibility(View.VISIBLE);
                    image_cross.setVisibility(View.VISIBLE);
                    image_search.setVisibility(View.GONE);
                    i++;
                }
                else {
                    search_bar_LL.setVisibility(View.GONE);
                    image_search.setVisibility(View.VISIBLE);
                    image_cross.setVisibility(View.GONE);
                    i++;
                }
            }
        });

    }
//************************************************************************************************************
// ************************************************************************************
    private void getCurrentLacation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            MarkerOptions options = new MarkerOptions().position(latLng).title("I am there");
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                            googleMap.addMarker(options);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 44){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurrentLacation();
            }
        }

    }
}