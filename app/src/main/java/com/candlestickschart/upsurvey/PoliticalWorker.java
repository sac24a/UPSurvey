package com.candlestickschart.upsurvey;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PoliticalWorker extends AppCompatActivity {

    EditText editText;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    Spinner spinner;
    Spinner spinner2;
    RadioButton rbtn1;
    RadioButton rbtn2;
    RadioButton rbtn3;
    TextView name;
    TextView locname;
    TextView villName;
    SharedPreferences sharedPreferences;
    Button save;
    String value = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_political_worker);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.pername);
        editText3 = findViewById(R.id.age);
        editText4 = findViewById(R.id.mobile);
        spinner = findViewById(R.id.favparty);
        spinner2 = findViewById(R.id.gender);
        rbtn1 = findViewById(R.id.rbtn1);
        rbtn2 = findViewById(R.id.rbtn2);
        rbtn3 = findViewById(R.id.rbtn3);
        save = findViewById(R.id.save);

        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        name = findViewById(R.id.name);
        locname = findViewById(R.id.locaName);
        villName = findViewById(R.id.villName);
        name.setText(sharedPreferences.getString("name",""));
        locname.setText(sharedPreferences.getString("user_asign_location","Ghaziabad"));
        villName.setText(sharedPreferences.getString("LOC_Name",""));

        editText.setText(getIntent().getStringExtra("party"));

        rbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value = rbtn1.getText().toString();
            }
        } );
        rbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value = rbtn2.getText().toString();
            }
        } );
        rbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value = rbtn3.getText().toString();
            }
        } );

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!value.equals("") && !editText.getText().toString().equals("") && !editText2.getText().toString().equals("") && !editText3.getText().toString().equals("") && !editText4.getText().toString().equals("")) {
                    try {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(PoliticalWorker.this);
                                    PoliticalWorkerData pollFirstData = new PoliticalWorkerData(sharedPreferences.getString("user_id",""),sharedPreferences.getString("user_mobile_no",""),sharedPreferences.getString("LOC_ID",""),spinner.getSelectedItem().toString(),value,editText2.getText().toString(),spinner2.getSelectedItem().toString(),editText3.getText().toString(),editText4.getText().toString(),editText.getText().toString());
                                    pollFirstDataBase.pollFirstDao().insertPoliticalWorker(pollFirstData);
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
                    Toast.makeText(PoliticalWorker.this,"Please enter all fields",Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}