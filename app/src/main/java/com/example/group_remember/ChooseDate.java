package com.example.group_remember;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class ChooseDate extends AppCompatActivity {

     EditText inputYear;
     EditText inputMonth;
     EditText inputDay;
     Button back, finish, choosetoday;
    boolean validDate = false;

    ArrayList<Date> dateList;
    int number;
    String version;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_date);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent intent = getIntent();
        Serializable serializable = getIntent().getSerializableExtra("dateList");
        if(serializable != null) {
            dateList = new ArrayList<Date>();
            dateList.addAll((ArrayList<Date>) serializable);
        }
        number = intent.getIntExtra("int",0);
        version = intent.getStringExtra("version");
        date = dateList.get(number);

        inputYear = (EditText) findViewById(R.id.yearInput);
        inputMonth = (EditText) findViewById(R.id.monthInput);
        inputDay = (EditText) findViewById(R.id.dayInput);
        back = (Button) findViewById(R.id.back);
        finish = (Button) findViewById(R.id.FinishDate);
        choosetoday = (Button) findViewById(R.id.choosetoday);

        if(version.equals("old")){
            inputYear.setText(date.getYear() + "");
            inputMonth.setText(date.getMonth() + "");
            inputDay.setText(date.getDay() + "");
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseDate.this, EditEventOption.class);
                intent.putExtra("dateList", (Serializable)dateList);
                intent.putExtra("int",number);
                intent.putExtra("version",version);
                startActivity(intent);
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!setDate()) {
                    Toast.makeText(ChooseDate.this, "Please enter valid date", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(ChooseDate.this, EditEventOption.class);
                    intent.putExtra("dateList", (Serializable)dateList);
                    intent.putExtra("int",number);
                    intent.putExtra("version",version);
                    startActivity(intent);
                }
            }
        });

        choosetoday.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                LocalDate currentdate = LocalDate.now();

                date.setYear(currentdate.getYear());
                date.setMonth(currentdate.getMonthValue());
                date.setDay(currentdate.getDayOfMonth());
                Intent intent = new Intent(ChooseDate.this, EditEventOption.class);
                intent.putExtra("dateList", (Serializable)dateList);
                intent.putExtra("int",number);
                intent.putExtra("version",version);
                startActivity(intent);
            }
        });

    }

    public boolean setDate() {

        if (inputYear.getText().toString().isEmpty() || inputMonth.getText().toString().isEmpty()
            || inputDay.getText().toString().isEmpty()) {
            return true;
        }

        String curdate = inputDay.getText().toString() + inputMonth.getText().toString() + inputYear.getText().toString();

        boolean check = true;
        Calendar cal = Calendar.getInstance();
        cal.setLenient(false);
        cal.set(Integer.valueOf(inputYear.getText().toString()),Integer.valueOf(inputMonth.getText().toString()) - 1,
                Integer.valueOf(inputDay.getText().toString()));
        try {
            cal.getTime();
        }
        catch (Exception e) {
            check = false;
            System.out.println("Invalid date");
        }


        if (check){
            date.setYear(Integer.valueOf(inputYear.getText().toString()));
            date.setMonth(Integer.valueOf(inputMonth.getText().toString()));
            date.setDay(Integer.valueOf(inputDay.getText().toString()));
            validDate = true;
        }
        else {
            validDate = false;
        }
        return validDate;
    }
}