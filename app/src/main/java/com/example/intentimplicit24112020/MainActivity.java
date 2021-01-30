package com.example.intentimplicit24112020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button mBtnCamera, mBtnGallery;
    ImageView mImg;
    int REQUEST_CODE_CAMERA = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnCamera = findViewById(R.id.buttonCamera);
        mBtnGallery = findViewById(R.id.buttonGallery);
        mImg = findViewById(R.id.imageview);

        // sms : Hau nhu khong duoc dua len store
        // contact : Duoc su dung it tren store

        // GPS : dinh vi vi tri dang dung
        // Open file pdf
        // Chon nhieu hinh tu gallery
        mBtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat
                            .requestPermissions(
                                    MainActivity.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    REQUEST_CODE_CAMERA
                            );
                }else{
                    Intent intent = new Intent();
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent , REQUEST_CODE_CAMERA);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_CAMERA){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent , REQUEST_CODE_CAMERA);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}