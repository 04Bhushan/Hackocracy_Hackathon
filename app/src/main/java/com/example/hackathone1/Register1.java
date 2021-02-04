 package com.example.hackathone1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

 public class Register1 extends AppCompatActivity {
     String str_select_radio;

     private ImageView back_arrow;
     private Button Save_As_reg;
     private CardView CV_gallery,CV_Camera;
     private EditText ET_name,ET_address,ET_Password,ET_mobile,ET_username;
     private static final int CAMERA_REQUEST = 1888;
     private static int RESULT_LOAD_IMAGE = 1;
     Bitmap bitmap;
     private ImageView profilePic;
    // private static final String REGISTRAION1_URL = "http://iglobeinfotech.in/online_quiz/registration.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        method_Picture();
        saveBtn_method();
        backMethod();
    }
     public void backMethod(){
         back_arrow = (ImageView) findViewById(R.id.back_arrow);
         back_arrow.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(Register1.this,beforeLogin.class);
                 startActivity(intent);
                 finish();
             }
         });
     }
     public void validation(){

         if(ET_name.getText().toString().trim().length() == 0){
             ET_name.setError("Please Enter Your Name");
         }
         else if(ET_mobile.getText().toString().trim().length() == 0 || ET_mobile.getText().toString().trim().length() < 10 || ET_mobile.getText().toString().trim().length() > 10 ){
             ET_mobile.setError("Please Enter Mobile No.");
         }
         else if(ET_Password.getText().toString().trim().length() == 0){
             ET_Password.setError("Please Enter Password");
         }
         else if(ET_address.getText().toString().trim().length() == 0){
             ET_address.setError("Please Enter Address");
         }
         else {
             Toast.makeText(getApplicationContext(), "You Have Successfully Registerd!....", Toast.LENGTH_SHORT).show();
             Intent i = new Intent(Register1.this,  MainActivity.class);
             startActivity(i);
             finish();
         }

     }
     public void saveBtn_method(){
         profilePic = (ImageView) findViewById(R.id.profilePic);
         ET_name = (EditText) findViewById(R.id.ET_name);
         ET_Password = (EditText) findViewById(R.id.ET_Password);
         ET_mobile = (EditText) findViewById(R.id.ET_mobile);//
         ET_address = (EditText) findViewById(R.id.ET_address);//ET_address

         Save_As_reg = (Button) findViewById(R.id.Save_As_reg);

         Save_As_reg.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 validation();
                 //********************

             }
         });
     }


     private void method_Picture(){
         CV_gallery = (CardView) findViewById(R.id.CV_gallery);
         CV_gallery.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Intent i = new Intent(
                         Intent.ACTION_PICK,
                         MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                 startActivityForResult(i, RESULT_LOAD_IMAGE);
                 Toast.makeText(Register1.this, "Gallary", Toast.LENGTH_SHORT).show();
             }
         });
         //***************************************************************
         CV_Camera = (CardView) findViewById(R.id.CV_Camera);
         CV_Camera.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                 startActivityForResult(cameraIntent, CAMERA_REQUEST);
                 Toast.makeText(Register1.this, "Camera", Toast.LENGTH_SHORT).show();
             }
         });

     }
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         super.onActivityResult(requestCode, resultCode, data);
         if (requestCode == CAMERA_REQUEST) {
             bitmap = (Bitmap) data.getExtras().get("data");
             profilePic.setImageBitmap(bitmap);
         }else
         if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
             try{
                 final Uri uriImage = data.getData();
                 final InputStream inputStream = getContentResolver().openInputStream(uriImage);
                 bitmap = BitmapFactory.decodeStream(inputStream);
                 profilePic.setImageBitmap(bitmap);
             }
             catch (FileNotFoundException e) {
                 e.printStackTrace();
                 Toast.makeText(getApplicationContext(), "Image was not found", Toast.LENGTH_SHORT).show();
             }
         }else{}
     }

     public String getStringImage(Bitmap bmp){

         Drawable drawable =profilePic.getDrawable();
         bitmap = ((BitmapDrawable)drawable).getBitmap();

         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         bitmap.compress(Bitmap.CompressFormat.JPEG, 90, baos);
         byte[] imageBytes = baos.toByteArray();
         String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
         return encodedImage;
     }


}