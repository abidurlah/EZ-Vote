package com.example.ezvote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ScanButton extends AppCompatActivity {

    Button Profile_btn,Scan_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_button);

        Scan_btn = (Button) findViewById(R.id.button);
        Scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),QRScanner.class));
            }
        });

//          Profile_btn = (Button) findViewById(R.id.student_profile);
//          Profile_btn.setOnClickListener(new View.OnClickListener() {
//              @Override
//              public void onClick(View view) {
//                  startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
//              }
//          });
    }
}