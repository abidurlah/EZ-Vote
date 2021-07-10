package com.example.ezvote;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView TextViewhaveAccount;
    EditText EditTextid,EditTextname,EditTextcourse;
    Button Buttonregister;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditTextid = (EditText) findViewById(R.id.student_id);
        EditTextname = (EditText) findViewById(R.id.student_name);
        EditTextcourse = (EditText) findViewById(R.id.student_course);

        TextViewhaveAccount = (TextView) findViewById(R.id.Already_have_account);

        Buttonregister = (Button) findViewById(R.id.register_button);

        Buttonregister.setOnClickListener(this);
        TextViewhaveAccount.setOnClickListener(this);
    }

    private void registerUser() {
        final String student_id = EditTextid.getText().toString().trim();
        final String student_name = EditTextname.getText().toString().trim();
        final String student_course = EditTextcourse.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constant.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("student_id", student_id);
                params.put("student_name", student_name);
                params.put("student_course", student_course);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    @Override
    public void onClick(View view) {
        if (view == Buttonregister)
            registerUser();
        if(view == TextViewhaveAccount)
            startActivity(new Intent(this, login.class));
    }
}