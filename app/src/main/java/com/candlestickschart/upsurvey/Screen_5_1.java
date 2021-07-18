package com.candlestickschart.upsurvey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Screen_5_1 extends AppCompatActivity {
    Button save;
    EditText editText;
    RadioButton rbtn1;
    RadioButton rbtn2;
    RadioButton rbtn3;
    RadioButton rbtn4;
    RadioButton rbtn5;
    RadioButton rbtn6;
    TextView name;
    TextView locname;
    TextView villName;
    SharedPreferences sharedPreferences;

    String visit = "";
    String rai = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_5_1);
        save= findViewById(R.id.save);
        rbtn1 = findViewById(R.id.rbtn1);
        rbtn2 = findViewById(R.id.rbtn2);
        rbtn3 = findViewById(R.id.rbtn3);
        rbtn4 = findViewById(R.id.rbtn4);
        rbtn5 = findViewById(R.id.rbtn5);
        rbtn6 = findViewById(R.id.rbtn6);
        editText = findViewById(R.id.editText);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        name = findViewById(R.id.name);
        locname = findViewById(R.id.locaName);
        villName = findViewById(R.id.villName);
        name.setText(sharedPreferences.getString("name",""));
        locname.setText(sharedPreferences.getString("user_asign_location","Ghaziabad"));
        villName.setText(sharedPreferences.getString("LOC_Name",""));


        rbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visit = rbtn1.getText().toString();
            }
        } );
        rbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visit = rbtn2.getText().toString();
            }
        } );
        rbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visit = rbtn3.getText().toString();
            }
        } );
        rbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rai = rbtn4.getText().toString();
            }
        } );
        rbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rai = rbtn5.getText().toString();
            }
        } );
        rbtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rai = rbtn6.getText().toString();
            }
        } );

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!rai.equals("") && !visit.equals("") && !editText.getText().toString().equals("")) {
                    try {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(Screen_5_1.this);
                                    pollFirstDataBase.pollFirstDao().updateScreen5_1(visit,rai,editText.getText().toString(),sharedPreferences.getString("LOC_ID",""));
                                    Intent intent = new Intent(Screen_5_1.this, MainDashBoard.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    Screen_5_1.this.finish();


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
                    Toast.makeText(Screen_5_1.this,"All fields are mandatory",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}