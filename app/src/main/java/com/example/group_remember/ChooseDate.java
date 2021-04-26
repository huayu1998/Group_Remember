package com.example.group_remember;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class ChooseDate extends AppCompatActivity {

     EditText inputYear;
     EditText inputMonth;
     EditText inputDay;
     Button back, finish;
    boolean validDate = false;

    //接收信息
    ArrayList<Date> dateList;
    int number;
    String version;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_date);

        //接收信息
        Intent intent = getIntent();
        Serializable serializable = getIntent().getSerializableExtra("dateList");//在另一个activity中用于获取对象
        if(serializable != null) {
            dateList = new ArrayList<Date>();
            dateList.addAll((ArrayList<Date>) serializable);//之后将serializable对象强转使用即可
        }
        number = intent.getIntExtra("int",0);
        version = intent.getStringExtra("version");
        date = dateList.get(number);

        inputYear = (EditText) findViewById(R.id.yearInput);
        inputMonth = (EditText) findViewById(R.id.monthInput);
        inputDay = (EditText) findViewById(R.id.dayInput);
        back = (Button) findViewById(R.id.back);
        finish = (Button) findViewById(R.id.FinishDate);

        if(version.equals("old")){
            inputYear.setText(date.getYear());
            inputMonth.setText(date.getMonth());
            inputDay.setText(date.getDay());
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

    }

    public boolean setDate() {

        if (inputYear.getText().toString().isEmpty() || inputMonth.getText().toString().isEmpty()
            || inputDay.getText().toString().isEmpty()) {
            return true;
        }

        if (Integer.valueOf(inputYear.getText().toString()) >= 2016 && Integer.valueOf(inputYear.getText().toString()) <= 2026
                && Integer.valueOf(inputMonth.getText().toString()) >= 1 && Integer.valueOf(inputMonth.getText().toString()) <= 12
                && Integer.valueOf(inputDay.getText().toString()) >= 1 && Integer.valueOf(inputDay.getText().toString()) <= 31) {

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