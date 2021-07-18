package com.candlestickschart.upsurvey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Screen_4 extends AppCompatActivity {
    private   ImageButton forward;
    private  ImageButton backward;
    EditText reason;
    Spinner spinner;

    EditText facts;
    TextView name;
    TextView locname;
    TextView villName;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_4);

        backward = (ImageButton) findViewById(R.id.backward);
        reason = findViewById(R.id.reason);
        facts = findViewById(R.id.facts);
        spinner = findViewById(R.id.spinner);
        forward= (ImageButton) findViewById(R.id.forward);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        name = findViewById(R.id.name);
        locname = findViewById(R.id.locaName);
        villName = findViewById(R.id.villName);
        name.setText(sharedPreferences.getString("name",""));
        locname.setText(sharedPreferences.getString("user_asign_location","Ghaziabad"));
        villName.setText(sharedPreferences.getString("LOC_Name",""));

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reason.getText().length() != 0 && facts.getText().length() !=0 && !spinner.getSelectedItem().toString().equals("कौन सी पार्टी आगे है")) {
                    try {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(Screen_4.this);
                                    pollFirstDataBase.pollFirstDao().updateScreen4(spinner.getSelectedItem().toString(),reason.getText().toString(),facts.getText().toString(),sharedPreferences.getString("LOC_ID",""));
                                    Intent intent = new Intent(Screen_4.this, Screen_4_1.class);
                                    startActivity(intent);


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
                    Toast.makeText(Screen_4.this,"All fields are mandatory",Toast.LENGTH_SHORT).show();
                }

            }
        });
        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}