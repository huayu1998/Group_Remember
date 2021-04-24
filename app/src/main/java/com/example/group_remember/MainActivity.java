package com.example.group_remember;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {


    TextView time;
    TextView length;
    TextView name;
    FloatingActionButton button;
    Button detail;
    ArrayList<Date> dateList;
    ReaderAndWriter readerAndWriter = new ReaderAndWriter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //读取
        Intent intent = getIntent();
        Serializable serializable = getIntent().getSerializableExtra("dateList");//在另一个activity中用于获取对象
        if(serializable != null) {
            dateList = new ArrayList<Date>();
            dateList.addAll((ArrayList<Date>)serializable);//之后将serializable对象强转使用即可
        }else{
            try {
                dateList = readerAndWriter.read(this,"data.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }





        setContentView(R.layout.activity_main);
        time = (TextView)findViewById(R.id.time);
        button = (FloatingActionButton)findViewById(R.id.addb);



        RecyclerView rvDate = findViewById(R.id.recyclerView);
            DateAdapter dateAdapter = new DateAdapter(this, dateList);
            rvDate.setAdapter(dateAdapter);
            rvDate.setLayoutManager(new LinearLayoutManager(this));



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditEventOption.class);
                Date date = new Date();
                dateList.add(date);
                intent.putExtra("dateList", (Serializable)dateList);
                intent.putExtra("int",dateList.size()-1);
                intent.putExtra("version","new");
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        //checking if asynctask is still runnning
        dateList = new ArrayList<Date>();
        try {
            readerAndWriter.write(this,"data.txt",dateList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}