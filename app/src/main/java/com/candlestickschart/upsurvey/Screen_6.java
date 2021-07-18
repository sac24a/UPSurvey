package com.candlestickschart.upsurvey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Screen_6 extends AppCompatActivity {
    private ImageButton backward;
    private ImageButton forward;

    RadioButton rbtn1;
    RadioButton rbtn2;
    RadioButton rbtn3;
    RadioButton rbtn4;
    RadioButton rbtn5;
    RadioButton rbtn6;
    RadioButton rbtn7;
    RadioButton rbtn8;
    RadioButton rbtn9;
    RadioButton rbtn10;
    RadioButton rbtn11;
    RadioButton rbtn12;
    RadioButton rbtn13;
    RadioButton rbtn14;
    RadioButton rbtn15;

    String bijli = "";
    String road = "";
    String hospital = "";
    String school ="";
    String water = "";
    TextView name;
    TextView locname;
    TextView villName;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_6);
        backward=(ImageButton) findViewById(R.id.btn);
        forward=(ImageButton) findViewById(R.id.forward);
        rbtn1 = findViewById(R.id.rbtn1);
        rbtn2 = findViewById(R.id.rbtn2);
        rbtn3 = findViewById(R.id.rbtn3);
        rbtn4 = findViewById(R.id.rbtn4);
        rbtn5 = findViewById(R.id.rbtn5);
        rbtn6 = findViewById(R.id.rbtn6);
        rbtn7 = findViewById(R.id.rbtn7);
        rbtn8 = findViewById(R.id.rbtn8);
        rbtn9 = findViewById(R.id.rbtn9);
        rbtn10 = findViewById(R.id.rbtn10);
        rbtn11 = findViewById(R.id.rbtn11);
        rbtn12 = findViewById(R.id.rbtn12);
        rbtn13 = findViewById(R.id.rbtn13);
        rbtn14 = findViewById(R.id.rbtn14);
        rbtn15 = findViewById(R.id.rbtn15);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        name = findViewById(R.id.name);
        locname = findViewById(R.id.locaName);
        villName = findViewById(R.id.villName);
        name.setText(sharedPreferences.getString("name",""));
        locname.setText(sharedPreferences.getString("user_asign_location","Ghaziabad"));
        villName.setText(sharedPreferences.getString("LOC_Name",""));
        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bijli.equals("") && !road.equals("") && !water.equals("") && !hospital.equals("") && !school.equals("")) {
                    try {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(Screen_6.this);
                                    pollFirstDataBase.pollFirstDao().updateScreen6(bijli,road,water,hospital,school,sharedPreferences.getString("LOC_ID",""));
                                    Intent intent = new Intent(Screen_6.this, Screen_6_1.class);
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
                    Toast.makeText(Screen_6.this,"Please enter all fields",Toast.LENGTH_SHORT).show();
                }
            }
        });

        rbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bijli = rbtn1.getText().toString();
            }
        } );
        rbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bijli = rbtn2.getText().toString();
            }
        } );
        rbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bijli = rbtn3.getText().toString();
            }
        } );
        rbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                road = rbtn4.getText().toString();
            }
        } );
        rbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                road = rbtn5.getText().toString();

            }
        } );
        rbtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                road = rbtn6.getText().toString();
            }
        } );

        rbtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                water = rbtn7.getText().toString();
            }
        } );
        rbtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                water = rbtn8.getText().toString();
            }
        } );
        rbtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                water = rbtn9.getText().toString();
            }
        } );
        rbtn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hospital = rbtn10.getText().toString();
            }
        } );
        rbtn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hospital = rbtn11.getText().toString();
            }
        } );
        rbtn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hospital = rbtn12.getText().toString();
            }
        } );
        rbtn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                school = rbtn10.getText().toString();
            }
        } );
        rbtn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                school = rbtn11.getText().toString();
            }
        } );
        rbtn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                school = rbtn12.getText().toString();
            }
        } );
    }
}