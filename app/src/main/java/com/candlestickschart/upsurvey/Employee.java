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

public class Employee extends AppCompatActivity {

    EditText names;
    EditText age;
    EditText mobile;
    Spinner gender;
    Spinner socialType;
    Spinner post;
    TextView name;
    TextView locname;
    TextView villName;
    SharedPreferences sharedPreferences;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        names = findViewById(R.id.empname);
        age = findViewById(R.id.age);
        mobile = findViewById(R.id.mobile);
        gender = findViewById(R.id.gender);
        socialType = findViewById(R.id.socialtype);
        post = findViewById(R.id.post);
        name = findViewById(R.id.name);
        locname = findViewById(R.id.locaName);
        villName = findViewById(R.id.villName);
        save = findViewById(R.id.save);
        name.setText(sharedPreferences.getString("name",""));
        locname.setText(sharedPreferences.getString("user_asign_location","Ghaziabad"));
        villName.setText(sharedPreferences.getString("LOC_Name",""));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!names.getText().toString().equals("") && !age.getText().toString().equals("") && !mobile.getText().toString().equals("")) {
                    try {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(Employee.this);
                                    EmployeeData pollFirstData = new EmployeeData(sharedPreferences.getString("user_id",""),sharedPreferences.getString("user_mobile_no",""),sharedPreferences.getString("LOC_ID",""),names.getText().toString(),gender.getSelectedItem().toString(),age.getText().toString(),socialType.getSelectedItem().toString(),mobile.getText().toString(),post.getSelectedItem().toString());
                                    pollFirstDataBase.pollFirstDao().insertEmployee(pollFirstData);
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
                    Toast.makeText(Employee.this,"Please enter all fields",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}