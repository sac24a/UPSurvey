package com.candlestickschart.upsurvey;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SocialMediaAc extends AppCompatActivity {

    EditText names;
    EditText age;
    EditText mobile;
    Spinner gender;
    Spinner socialType;
    String facebook = "";
    String whatsapp = "";
    String insta = "";
    String twitter = "";
    String value = "";
    TextView name;
    TextView locname;
    TextView villName;
    SharedPreferences sharedPreferences;
    Button save;
    CheckBox rbtn1;
    CheckBox rbtn2;
    CheckBox rbtn3;
    CheckBox rbtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        names = findViewById(R.id.names);
        age = findViewById(R.id.age);
        mobile = findViewById(R.id.mobile);
        gender = findViewById(R.id.gender);
        socialType = findViewById(R.id.samaj);
        name = findViewById(R.id.name);
        locname = findViewById(R.id.locaName);
        villName = findViewById(R.id.villName);
        name.setText(sharedPreferences.getString("name",""));
        locname.setText(sharedPreferences.getString("user_asign_location","Ghaziabad"));
        villName.setText(sharedPreferences.getString("LOC_Name",""));
        rbtn1 = findViewById(R.id.rbtn1);
        rbtn2 = findViewById(R.id.rbtn2);
        rbtn3 = findViewById(R.id.rbtn3);
        rbtn4 = findViewById(R.id.rbtn4);
        save = findViewById(R.id.save);

        rbtn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    whatsapp = "Whatsapp";
                }
                else {
                    whatsapp = "";
                }
            }
        });
        rbtn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    facebook = "Facebook";
                }
                else {
                    facebook = "";
                }
            }
        });
        rbtn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    insta = "Instagram";
                }
                else {
                    insta = "";
                }
            }
        });
        rbtn4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    twitter = "Twitter";
                }
                else {
                    twitter = "";
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!whatsapp.equals("")) {
                    value = whatsapp;
                }
                if (!facebook.equals("")) {
                    if (!value.equals("")) {

                        value = value+","+whatsapp;
                    }
                    else {
                        value = whatsapp;
                    }

                }
                if (!insta.equals("")) {
                    if (!value.equals("")) {

                        value = value+","+insta;
                    }
                    else {
                        value = insta;
                    }
                }
                if (!twitter.equals("")) {
                    if (!value.equals("")) {

                        value = value+","+twitter;
                    }
                    else {
                        value = twitter;
                    }
                }

                if (!names.getText().toString().equals("") && !age.getText().toString().equals("") && !mobile.getText().toString().equals("") && !value.equals("")) {
                    try {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(SocialMediaAc.this);
                                    SocialMediaData pollFirstData = new SocialMediaData(sharedPreferences.getString("user_id",""),sharedPreferences.getString("user_mobile_no",""),sharedPreferences.getString("LOC_ID",""),names.getText().toString(),gender.getSelectedItem().toString(),age.getText().toString(),socialType.getSelectedItem().toString(),mobile.getText().toString(),value);
                                    pollFirstDataBase.pollFirstDao().insertSocialMedia(pollFirstData);
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
                    Toast.makeText(SocialMediaAc.this,"Please enter all fields",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}