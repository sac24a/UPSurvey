package com.candlestickschart.upsurvey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Screen_12 extends AppCompatActivity {

    EditText brahmin;
    EditText baniya;
    EditText thakur;
    EditText kayasth;
    Button btn;
    Button save;
    Button btn2;
    Button btn3;
    Button btn4;
    TextView name;
    TextView locname;
    TextView villName;
    SharedPreferences sharedPreferences;
    List<String> socialData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_12);
        brahmin= findViewById(R.id.editText);
        baniya= findViewById(R.id.editText1);
        thakur= findViewById(R.id.editText2);
        kayasth= findViewById(R.id.editText3);

        btn= findViewById(R.id.btn);
        btn2= findViewById(R.id.btn2);
        btn3= findViewById(R.id.btn3);
        btn4= findViewById(R.id.btn4);

        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        name = findViewById(R.id.name);
        locname = findViewById(R.id.locaName);
        villName = findViewById(R.id.villName);
        name.setText(sharedPreferences.getString("name",""));
        locname.setText(sharedPreferences.getString("user_asign_location","Ghaziabad"));
        villName.setText(sharedPreferences.getString("LOC_Name",""));
        
        save= findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Screen_12.this, MainDashBoard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                Screen_12.this.finish();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkValue(brahmin.getText().toString(),"अगडे मुस्लिम");

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValue(baniya.getText().toString(),"पिछड़े मुस्लिम");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValue(thakur.getText().toString(),"सिक्ख समाज");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValue(kayasth.getText().toString(),"अन्य");
            }
        });
    }
    public void checkValue(String voter,String socialType){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(Screen_12.this);
                socialData = pollFirstDataBase.pollFirstDao().checkSocialData(socialType);
                if (socialData.size() == 0) {
                    Intent intent = new Intent(Screen_12.this, SocialPerson.class);
                    intent.putExtra("value",socialType);
                    intent.putExtra("voter",voter);
                    startActivity(intent);
                }
                else {
                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Screen_12.this,"You have saved this data",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
}