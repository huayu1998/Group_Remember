package com.example.group_remember;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Day extends AppCompatActivity {

    TextView title;
    TextView length;
    TextView time;
    TextView message;
    Button button;
    FloatingActionButton music;
    Button editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        title = (TextView)findViewById(R.id.titleT);
        length = (TextView)findViewById(R.id.lengthT);
        time = (TextView)findViewById(R.id.timeT);
        message = (TextView)findViewById(R.id.messageT);
        button = (Button)findViewById(R.id.photo);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Day.this, EditEventOption.class);
                startActivity(intent);
            }
        });

        //music = (FloatingActionButton)findViewById(R.id.musicFab);

        //music.setOnClickListener(new View.OnClickListener() {
       //     @Override
      //      public void onClick(View v) {
       //         Intent intent = new Intent(Day.this, PlayMusic.class);
       //         startActivity(intent);
       //     }
      //  });
      //  editText = (Button)findViewById(R.id.editb);
      //  editText.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View v) {
       //         Intent intent = new Intent(Day.this, EventEdit.class);
        //        startActivity(intent);
       //     }
       // });
    }

    public static Bitmap getLoacalBitmap(String url) {
        if (url != null) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(url);
                return BitmapFactory.decodeStream(fis);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } finally {
                if(fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    fis = null;
                }
            }
        } else {
            return null;
        }
    }

}