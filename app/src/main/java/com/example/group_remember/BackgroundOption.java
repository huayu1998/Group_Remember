package com.example.group_remember;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;


public class BackgroundOption extends AppCompatActivity {


    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_option);

        back = (Button) findViewById(R.id.backButton);


    }


}
