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

public class SocialPerson extends AppCompatActivity {

    EditText editText;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    Spinner spinner;
    Spinner spinner2;
    TextView name;
    TextView locname;
    TextView villName;
    SharedPreferences sharedPreferences;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_person);


        editText = findViewById(R.id.editText);
        editText.setText(getIntent().getStringExtra("value").toString());
        save = findViewById(R.id.save);
        editText2 = findViewById(R.id.Personname);
        editText3 = findViewById(R.id.age);
        editText4 = findViewById(R.id.mobile);
        spinner = findViewById(R.id.favparty);
        spinner2 = findViewById(R.id.gender);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        name = findViewById(R.id.name);
        locname = findViewById(R.id.locaName);
        villName = findViewById(R.id.villName);
        name.setText(sharedPreferences.getString("name",""));
        locname.setText(sharedPreferences.getString("user_asign_location","Ghaziabad"));
        villName.setText(sharedPreferences.getString("LOC_Name",""));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editText.getText().toString().equals("") && !editText2.getText().toString().equals("") && !editText3.getText().toString().equals("") && !editText4.getText().toString().equals("")) {
                    try {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(SocialPerson.this);
                                    SocialData pollFirstData = new SocialData(sharedPreferences.getString("user_id",""),sharedPreferences.getString("user_mobile_no",""),sharedPreferences.getString("LOC_ID",""),editText.getText().toString(),getIntent().getStringExtra("voter"),editText2.getText().toString(),spinner2.getSelectedItem().toString(),editText3.getText().toString(),editText4.getText().toString(),spinner.getSelectedItem().toString());
                                    pollFirstDataBase.pollFirstDao().insertSocialImpPersion(pollFirstData);
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
                    Toast.makeText(SocialPerson.this,"Please enter all fields",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}