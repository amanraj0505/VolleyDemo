package com.example.volleydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.VoiceInteractor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    EditText etName, etSalary, etAge;
    Button btnGET, btnPOST;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String URL= "http://dummy.restapiexample.com/api/v1/employee/1";
        final  String URL1= "http://dummy.restapiexample.com/api/v1/create";

        tvResult = findViewById(R.id.tvResult);
        etName= findViewById(R.id.etName);
        etSalary= findViewById(R.id.etSalary);
        etAge= findViewById(R.id.etAge);
        btnPOST = findViewById(R.id.btnPOST);
        btnGET = findViewById(R.id.btnGET);


        btnGET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        tvResult.setText(response.toString());

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("Rest Response",error.toString());
                    }
                });

                requestQueue.add(objectRequest);

            }
        });

        btnPOST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest postRequest = new StringRequest(Request.Method.POST, URL1, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Rest Response",response.toString());

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error){

                        Log.d("error messsage",error.toString());

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String>  params = new HashMap<String, String>();

                        params.put("name", etName.getText().toString().trim());
                        params.put("age", etSalary.getText().toString().trim());
                        params.put("salary",etSalary.getText().toString().trim());

                        return params;
                    }
                };
            }


        });


    }
}
