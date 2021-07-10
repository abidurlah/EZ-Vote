package com.example.ezvote;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity implements View.OnClickListener {

    Button login_button,register_button;
    EditText student_id_login, student_course_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register_button = (Button) findViewById(R.id.register_btn_login);
        login_button = (Button) findViewById(R.id.Login_btn);
        student_id_login = (EditText) findViewById(R.id.student_id_login);
        student_course_login = (EditText) findViewById(R.id.student_course_login);

        login_button.setOnClickListener(this);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToRegisterView();
            }
        });
    }

    private void userlogin(){
        final String student_id = student_id_login.getText().toString().trim();
        final String student_course = student_course_login.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if (!obj.getBoolean("error")) {
                        SharedPrefManager.getInstance(getApplication()).userLogin(obj.getInt("id"),
                                obj.getString("student_id"),obj.getString("student_course"));
//                        Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),ScanButton.class));
                    }else {
                        Toast.makeText(getApplicationContext(),obj.getString("message"),Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("student_id",student_id);
                params.put("student_course",student_course);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void GoToRegisterView() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(view == login_button){
            userlogin();
        }

    }
}