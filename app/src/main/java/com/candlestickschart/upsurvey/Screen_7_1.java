package com.candlestickschart.upsurvey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Screen_7_1 extends AppCompatActivity {
    EditText thakur;
    EditText kayasth;
    Button btn;
    Button btn2;
    ImageButton backward;
    ImageButton forward;
    TextView name;
    TextView locname;
    TextView villName;
    SharedPreferences sharedPreferences;
    List<String> socialData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_7_1);
        thakur= findViewById(R.id.editText);
        kayasth= findViewById(R.id.editText1);
        btn= findViewById(R.id.btn);
        btn2= findViewById(R.id.btn2);
        backward= findViewById(R.id.backward);
        forward= findViewById(R.id.forward);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        name = findViewById(R.id.name);
        locname = findViewById(R.id.locaName);
        villName = findViewById(R.id.villName);
        name.setText(sharedPreferences.getString("name",""));
        locname.setText(sharedPreferences.getString("user_asign_location","Ghaziabad"));
        villName.setText(sharedPreferences.getString("LOC_Name",""));

        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Screen_7_1.this, screen_8.class);
                startActivity(intent);

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkValue(thakur.getText().toString(),"भूमिहार समाज");

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValue(kayasth.getText().toString(),"अन्य स्वर्ण समाज");
            }
        });
    }
    public void checkValue(String voter,String socialType){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(Screen_7_1.this);
                socialData = pollFirstDataBase.pollFirstDao().checkSocialData(socialType);
                if (socialData.size() == 0) {
                    Intent intent = new Intent(Screen_7_1.this, SocialPerson.class);
                    intent.putExtra("value",socialType);
                    intent.putExtra("voter",voter);
                    startActivity(intent);
                }
                else {
                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Screen_7_1.this,"You have saved this data",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
}