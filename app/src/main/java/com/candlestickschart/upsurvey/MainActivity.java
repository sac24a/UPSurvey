package com.candlestickschart.upsurvey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private ImageView imageView;
    private TextView textView;
    private EditText editText;
    private View view;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView =(ImageView) findViewById(R.id.image_main);
        textView =(TextView) findViewById(R.id.text1);
        view =(View) findViewById(R.id.view);
        editText =(EditText) findViewById(R.id.edit);
        button =(Button) findViewById(R.id.button1);
        progressBar = findViewById(R.id.progressBar);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        editor = sharedPreferences.edit();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() < 10) {
                    Toast.makeText(MainActivity.this,"Enter valid mobile",Toast.LENGTH_LONG).show();
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    apicallToLogin();

                }

            }
        });
        getAllData();

    }
    public void getAllData() {
        JSONArray jsonArray = new JSONArray();
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(MainActivity.this);
                List<PollFirstData> pollFirstData = pollFirstDataBase.pollFirstDao().getPollfirstData();
                List<String> socialData = pollFirstDataBase.pollFirstDao().getlocalityType();
                Log.d("Size", "onCreate: "+pollFirstData.size());
                if (pollFirstData.size()!=0){
                    if (socialData.get(0).equals("")){
                        Intent intent = new Intent(MainActivity.this,Screen3.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Intent intent = new Intent(MainActivity.this,MainDashBoard.class);
                        startActivity(intent);
                        finish();
                    }

                }
                else {
                    if (!sharedPreferences.getString("user_id","").equals("")) {
                        Intent intent = new Intent(MainActivity.this,Screen_2.class);
                        startActivity(intent);
                        finish();
                    }
                }


            }
        });
    }
    public void apicallToLogin(){
        try {


            String url = "https://linier.in/UK/Rishikesh/LUP_API/MemberLoginRecord.php";
            Map<String, String> postParam= new HashMap<String, String>();
            postParam.put("user_mobile_no", editText.getText().toString());
            JSONObject jsonObject = new JSONObject(postParam);
            Log.d("TAG", "apicallToLogin: "+ jsonObject);
            JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(postParam),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // response
                            Log.e("Responselogin", response.toString());
                            progressBar.setVisibility(View.GONE);
                            try {

                                JSONObject jsonObject = new JSONObject(response.toString());
                                if (jsonObject.getBoolean("success"))
                                {
                                    Intent intent = new Intent(MainActivity.this,Screen_2.class);
                                    editor.putString("user_id",jsonObject.getString("user_id"));
                                    editor.putString("user_mobile_no",jsonObject.getString("user_mobile_no"));
                                    editor.putString("name",jsonObject.getString("name"));
                                    editor.putString("user_asign_location",jsonObject.getString("user_asign_location"));
                                    editor.putString("LOC_ID",jsonObject.getString("LOC_ID"));
                                    editor.putString("LS",jsonObject.getString("LS_no"));
                                    editor.putString("VS",jsonObject.getString("AC_no"));
                                    editor.commit();
                                    startActivity(intent);
                                }
                                else  {
                                    Toast.makeText(MainActivity.this,jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                                }
                            }
                            catch (JSONException e) {

                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Its error === >", "onErrorResponse: "+error);
                            progressBar.setVisibility(View.GONE);

                        }
                    }

            ) ;
            Mysingleton.getInstance(getApplicationContext()).addToRequestque(postRequest);



        }
        catch (NullPointerException e) {

        }
    }
}