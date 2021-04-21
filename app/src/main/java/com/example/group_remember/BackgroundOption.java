package com.example.group_remember;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class BackgroundOption extends AppCompatActivity {


    Button camera;

    ImageView backGround0;
    ImageView backGround1;
    ImageView backGround2;
    ImageView backGround3;
    ImageView backGround4;
    ImageView backGround5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_option);

        camera = (Button) findViewById(R.id.camera);

        backGround0 = (ImageView) findViewById(R.id.b0);
        backGround1 = (ImageView) findViewById(R.id.b1);
        backGround2 = (ImageView) findViewById(R.id.b2);
        backGround3 = (ImageView) findViewById(R.id.b3);
        backGround4 = (ImageView) findViewById(R.id.b4);
        backGround5 = (ImageView) findViewById(R.id.b5);




    }


}
