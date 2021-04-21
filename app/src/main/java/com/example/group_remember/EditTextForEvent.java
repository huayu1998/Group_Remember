package com.example.group_remember;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditTextForEvent extends AppCompatActivity{

    Button finish;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        finish = (Button) findViewById(R.id.finish);

        name = (TextView) findViewById(R.id.eventName);

    }
}






