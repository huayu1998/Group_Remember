package com.example.group_remember;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Day extends AppCompatActivity {

    TextView title;
    TextView length;
    TextView time;
    TextView message;
    Button back, button;
    //FloatingActionButton music;
    Button editText;
    MediaPlayer mp;
    ImageView photo;
    Switch musicOnOff;

    //接收信息
    ArrayList<Date> dateList;
    int number;
    String version;
    Date date;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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

        title = (TextView)findViewById(R.id.titleT);
        length = (TextView)findViewById(R.id.lengthT);
        time = (TextView)findViewById(R.id.timeT);
        message = (TextView)findViewById(R.id.messageT);
        back = (Button) findViewById(R.id.back);
        button = (Button)findViewById(R.id.edit);
        photo = (ImageView)findViewById(R.id.photoiv);
        musicOnOff = (Switch) findViewById(R.id.musicOnOff);
        musicOnOff.bringToFront();
        message.bringToFront();
        time.bringToFront();
        length.bringToFront();
        title.bringToFront();



        // Set the Text Information
        setInformation();
        setImage();

        // Play the music
        if (!date.getMusic().equals("null")) {

            if (date.getMusic().equals("Music1")) {
                mp = MediaPlayer.create(Day.this, R.raw.song1);
                mp.setLooping(true);
                mp.start();
            }
            else if (date.getMusic().equals("Music2")) {
                mp = MediaPlayer.create(Day.this, R.raw.song2);
                mp.setLooping(true);
                mp.start();
            }
            else if (date.getMusic().equals("Music3")) {
                mp = MediaPlayer.create(Day.this, R.raw.song3);
                mp.setLooping(true);
                mp.start();
            }
            else if (date.getMusic().equals("Music4")) {
                mp = MediaPlayer.create(Day.this, R.raw.song4);
                mp.setLooping(true);
                mp.start();
            }

        }
        else {
            musicOnOff.setChecked(false);
            musicOnOff.setText("Off");
        }

        // Turn the music on or off
        musicOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (date.getMusic().equals("null")) {
                    musicOnOff.setChecked(false);
                    musicOnOff.setText("Music Off");
                    Toast.makeText(Day.this, "Please select a music first.", Toast.LENGTH_LONG).show();
                }
                else {
                    if (isChecked == true) {
                        musicOnOff.setText("Music On");
                        mp.start();
                    }
                    else if (isChecked == false) {
                        musicOnOff.setText("Music Off");
                        mp.pause();
                    }
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp != null) {
                    mp.release();
                }
                Intent intent = new Intent(Day.this, MainActivity.class);
                intent.putExtra("dateList", (Serializable)dateList);
                intent.putExtra("int",number);
                intent.putExtra("version",version);
                startActivity(intent);

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mp != null) {
                    mp.release();
                }
                Intent intent = new Intent(Day.this, EditEventOption.class);
                intent.putExtra("dateList", (Serializable)dateList);
                intent.putExtra("int",number);
                intent.putExtra("version",version);
                startActivity(intent);
            }
        });

//        FloatingActionButton music = (FloatingActionButton)findViewById(R.id.musicOnOff);

//        music.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });
      //  editText = (Button)findViewById(R.id.editb);
      //  editText.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View v) {
       //         Intent intent = new Intent(Day.this, EventEdit.class);
        //        startActivity(intent);
       //     }
       // });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setInformation() {

        if (!date.getTopic().isEmpty() && !date.getTopic().equals("null")) {
            title.setText(date.getTopic());
        }

        if (!date.getText().isEmpty() && !date.getText().equals("null")) {
            message.setText(date.getText());
        }

        if (date.getYear() != 0 && date.getMonth() != 0 && date.getDay() != 0) {
            time.setText(date.getWeek());
            length.setText(date.getlength());
        }

    }

    public void setImage() {
        if (date.getImage() != 0) {
            //Drawable im = (Drawable)findViewById(date.getImage());
            int i = date.getImage();
            switch (i) {
                case 1:
                    photo.setImageResource(R.drawable.background_1);
                    break;
                case 2:
                    photo.setImageResource(R.drawable.background_2);
                    break;
                case 3:
                    photo.setImageResource(R.drawable.background_3);
                    break;
                case 4:
                    photo.setImageResource(R.drawable.background_4);
                    break;
                case 5:
                    photo.setImageResource(R.drawable.background_5);
                    break;
                case 6:
                    photo.setImageResource(R.drawable.background_6);
                    break;
                case -1:
                    String TargetPath = this.getFilesDir() + "/images/"+ number + ".jpg";
                    Bitmap bitmap = getLoacalBitmap(TargetPath);
                    photo.setImageBitmap(bitmap);
                    break;
            }
        }
    }

    public static Bitmap getLoacalBitmap(String url) {
        if (url != null) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(url);
                return BitmapFactory.decodeStream(fis); // /把流转化为Bitmap图片
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