package com.example.ezvote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView student_id, student_name, student_course,vote;
    Button logout_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);

        student_id = (TextView) findViewById(R.id.student_id_profile);
        logout_btn = findViewById(R.id.logout_btn);

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserLogout();
            }
        });

        student_id.setText(SharedPrefManager.getInstance(this).StudentID());
    }
    public void UserLogout(){
        SharedPrefManager.getInstance(this).logout();
        finish();
        Intent intent = new Intent (this, login.class);
        startActivity(intent);
    }
}