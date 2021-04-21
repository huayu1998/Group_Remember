package com.example.group_remember;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Day extends AppCompatActivity {

    TextView title;
    TextView length;
    TextView time;
    TextView message;
    Button button;
    //Button detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        title = (TextView)findViewById(R.id.titleT);
        length = (TextView)findViewById(R.id.lengthT);
        time = (TextView)findViewById(R.id.timeT);
        message = (TextView)findViewById(R.id.messageT);
        button = (Button)findViewById(R.id.editb);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Day.this, Day.class);
                //String data = song.getSelectedItem().toString();
                //intent.putExtra("songname", data);
                startActivity(intent);
            }
        });
    }
}