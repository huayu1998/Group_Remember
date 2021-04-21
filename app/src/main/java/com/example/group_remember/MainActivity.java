package com.example.group_remember;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {


    TextView time;
    TextView length;
    TextView name;
    FloatingActionButton button;
    Button detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = (TextView)findViewById(R.id.time);
        length = (TextView)findViewById(R.id.length);
        name = (TextView)findViewById(R.id.name);
        button = (FloatingActionButton)findViewById(R.id.addb);
        detail = (Button)findViewById(R.id.detailb);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Day.class);
                //String data = song.getSelectedItem().toString();
                //intent.putExtra("songname", data);
                startActivity(intent);
            }
        });
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Day.class);
                //String data = song.getSelectedItem().toString();
                //intent.putExtra("songname", data);
                startActivity(intent);
            }
        });

    }
}