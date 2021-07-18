package com.candlestickschart.upsurvey;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BoothAgentAc extends AppCompatActivity {

    EditText boothNumber;
    EditText partyName;
    EditText agentName;
    Spinner socialType;
    Spinner gender;
    EditText age;
    EditText mobile;
    TextView name;
    TextView locname;
    TextView villName;
    SharedPreferences sharedPreferences;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booth_agent);

        boothNumber = findViewById(R.id.boothnum);
        partyName = findViewById(R.id.partyname);
        age = findViewById(R.id.age);
        agentName = findViewById(R.id.agentname);
        socialType = findViewById(R.id.socialtype);
        gender = findViewById(R.id.gender);
        mobile = findViewById(R.id.mobile);
        save = findViewById(R.id.save);

        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        name = findViewById(R.id.name);
        locname = findViewById(R.id.locaName);
        villName = findViewById(R.id.villName);
        name.setText(sharedPreferences.getString("name",""));
        locname.setText(sharedPreferences.getString("user_asign_location","Ghaziabad"));
        villName.setText(sharedPreferences.getString("LOC_Name",""));
        partyName.setText(getIntent().getStringExtra("party"));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!boothNumber.equals("") && !partyName.getText().toString().equals("") && !age.getText().toString().equals("") && !agentName.getText().toString().equals("") && !mobile.getText().toString().equals("")) {
                    try {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(BoothAgentAc.this);
                                    BoothAgent pollFirstData = new BoothAgent(sharedPreferences.getString("user_id",""),sharedPreferences.getString("user_mobile_no",""),sharedPreferences.getString("LOC_ID",""),boothNumber.getText().toString(),partyName.getText().toString(),agentName.getText().toString(),socialType.getSelectedItem().toString(),gender.getSelectedItem().toString(),age.getText().toString(),mobile.getText().toString());
                                    pollFirstDataBase.pollFirstDao().insertBoothAgent(pollFirstData);
                                    finish();
                                }
                                catch (Exception e ) {

                                }
                            }
                        });
                    }
                    catch (NullPointerException e) {

                    }
                }
                else {
                    Toast.makeText(BoothAgentAc.this,"Please enter all fields",Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}