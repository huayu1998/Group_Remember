package com.example.group_remember;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.IOException;
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
        setContentView(R.layout.activity_main);
        time = (TextView)findViewById(R.id.time);
        button = (FloatingActionButton)findViewById(R.id.addb);


        Date test = new Date();
        dateList = new ArrayList<Date>();
        dateList.add(test);
        dateList.add(test);
        dateList.add(test);
        dateList.add(test);
        dateList.add(test);
        dateList.add(test);
        dateList.add(test);
        try {
            readerAndWriter.write(this,"data.txt",dateList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dateList = readerAndWriter.read(this,"data.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        RecyclerView rvDate = findViewById(R.id.recyclerView);
            DateAdapter dateAdapter = new DateAdapter(this, dateList);
            rvDate.setAdapter(dateAdapter);
            rvDate.setLayoutManager(new LinearLayoutManager(this));



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseDate.class);
                startActivity(intent);
            }
        });

//        public ArrayList<String> creating(String fileForUserContent) throws
//        FileNotFoundException {
//
//            ReaderAndWriter readMachine = new ReaderAndWriter();
//            ArrayList<String> Array = readMachine.read(fileForUserContent);
//
//            String firstLine = Array.get(0);
//
//            Scanner scanner = new Scanner(firstLine);
//
//            String [] indexInfor = new String [10];
//            indexInfor = scanner.next().split(", *");
//
//            for (int i = 0; i < 10; i++) {
//                System.out.println(indexInfor[i]);
//            }
//
//
//
//
//            for (int x = 0; x < 10; x++) {
//                if (indexInfor[x].equals("")) {
//                    year = x;
//                }
//                else if (indexInfor[x].equals("")) {
//                    month = x;
//                }
//                else if (indexInfor[x].equals("")) {
//                    day = x;
//                }
//
//
//            }
//
//
//
//            return Array;
//        }

    }
}