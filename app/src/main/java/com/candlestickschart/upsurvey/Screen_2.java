package com.candlestickschart.upsurvey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Screen_2 extends AppCompatActivity {

    TextView name;
    TextView locname;
    Spinner spinner;
    ProgressBar progressBar;
    ArrayList<LocationData> locationData;
    ArrayList<String> locationDataName;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_2);
        name = findViewById(R.id.name);
        locname = findViewById(R.id.locaName);
        spinner = findViewById(R.id.locSpinner);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        name.setText(sharedPreferences.getString("name",""));
        locname.setText(sharedPreferences.getString("user_asign_location","Ghaziabad"));
        editor = sharedPreferences.edit();
        locationData = new ArrayList<>();
        locationDataName = new ArrayList<>();

        apicallToLogin();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (locationDataName.size()!=0 ){
                    if (!locationDataName.get(i).equals("छेत्र का नाम")) {
                        String LOC_ID = locationData.get(i).LOC_ID;
                        String VS_No = sharedPreferences.getString("VS","0");
                        String locality_name = locationData.get(i).Name;
                        try {
                            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(Screen_2.this);
                                        List<PollFirstData> pollFirstData1 = pollFirstDataBase.pollFirstDao().getPollfirstData();
                                        PollFirstData pollFirstData = new PollFirstData(sharedPreferences.getString("user_id",""),sharedPreferences.getString("user_mobile_no",""),LOC_ID,VS_No,locality_name,"","","","","","","","","","","","","","","","","","","","","","");
                                        pollFirstDataBase.pollFirstDao().insertPollFirst(pollFirstData);
                                        List<PollFirstData> pollFirstData2 = pollFirstDataBase.pollFirstDao().getPollfirstData();
                                        if (pollFirstData2.size()>pollFirstData1.size()) {
                                            Intent intent = new Intent(Screen_2.this, Screen3.class);
                                            editor.putString("LOC_ID",locationData.get(i).LOC_ID);
                                            editor.putString("LOC_Name",locationData.get(i).Name);
                                            editor.apply();
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            Screen_2.this.finish();
                                        }
                                        else {
                                            AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                                @Override
                                                public void run() {
                                                    progressBar.setVisibility(View.GONE);
                                                    Toast.makeText(Screen_2.this,"Data not Inserted",Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                        }
                                    }
                                    catch (Exception e ) {
                                        Toast.makeText(Screen_2.this,e.toString(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        catch (NullPointerException e) {

                        }

                    }
                    else  {
                        Toast.makeText(Screen_2.this,"छेत्र का नाम चुने",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void apicallToLogin(){
        try {
            String url = "https://linier.in/UK/Rishikesh/LUP_API/Location_ListRecord.php";
            Map<String, String> postParam= new HashMap<String, String>();
            JSONArray jsonObject = new JSONArray();

            JsonArrayRequest postRequest = new JsonArrayRequest(Request.Method.POST, url,jsonObject,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            // response
                            Log.e("Responselogin", response.toString());
                            progressBar.setVisibility(View.GONE);
                            try {
                                JSONArray jsonArray = response;
                                Log.d("TAG===>", "onResponse: "+jsonArray);
                                locationDataName.add("छेत्र का नाम");
                                locationData.add(new LocationData("","","","","",""));
                                for (int i =0;i<jsonArray.length();i++) {
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    locationDataName.add(jsonObject1.getString("Name"));
                                    locationData.add(new LocationData(jsonObject1.getString("id"),jsonObject1.getString("LOC_ID"),jsonObject1.getString("Name"),jsonObject1.getString("Voters"),jsonObject1.getString("Latitude"),jsonObject1.getString("Longitude")));
                                }
                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Screen_2.this,android.R.layout.simple_spinner_item, locationDataName);
                                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner.setAdapter(arrayAdapter);
                                Log.d("TAG===>", "onResponse: "+locationDataName);
                            }
                            catch (JSONException e) {
                                e.printStackTrace();
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