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

public class Screen_6_1 extends AppCompatActivity {

    Button save;
    RadioButton rbtn1;
    RadioButton rbtn2;
    RadioButton rbtn3;
    EditText editText;
    EditText editText1;
    TextView name;
    TextView locname;
    TextView villName;
    SharedPreferences sharedPreferences;
    String emp_opps= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_6_1);
        save=  findViewById(R.id.save);
        rbtn1 = findViewById(R.id.rbtn1);
        rbtn2 = findViewById(R.id.rbtn2);
        rbtn3 = findViewById(R.id.rbtn3);
        editText = findViewById(R.id.editText);
        editText1 = findViewById(R.id.editText1);
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
                emp_opps = rbtn1.getText().toString();
            }
        } );
        rbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emp_opps = rbtn2.getText().toString();
            }
        } );
        rbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emp_opps = rbtn3.getText().toString();
            }
        } );

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!emp_opps.equals("") && !editText.getText().toString().equals("") && !editText1.getText().toString().equals("") ) {
                    try {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(Screen_6_1.this);
                                    pollFirstDataBase.pollFirstDao().updateScreen6_1(emp_opps,editText.getText().toString(),editText1.getText().toString(),sharedPreferences.getString("LOC_ID",""));
                                    Intent intent = new Intent(Screen_6_1.this, MainDashBoard.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    Screen_6_1.this.finish();

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
                    Toast.makeText(Screen_6_1.this,"All fields are mandatory",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}