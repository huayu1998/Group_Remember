package com.example.group_remember;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
                    musicOnOff.setText("Off");
                    Toast.makeText(Day.this, "Please select a music first.", Toast.LENGTH_LONG).show();
                }
                else {
                    if (isChecked == true) {
                        musicOnOff.setText("On");
                        mp.start();
                    }
                    else if (isChecked == false) {
                        musicOnOff.setText("Off");
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

            /*
            String dateFormat = date.getDay() + "/" + date.getMonth() + "/" + date.getYear();
            java.util.Date format = null;
            try {
                format = new SimpleDateFormat("dd/mm/yyyy").parse(dateFormat);
            } catch (ParseException e) {
                e.printStackTrace();
            }

             */

            // Find the day of week
            Calendar c = Calendar.getInstance();
            c.set(date.getYear(), date.getMonth() - 1, date.getDay());
            //c.setTime(format);
            int dayOfWeek = c.get(c.DAY_OF_WEEK);

            if (dayOfWeek == 1) {
                time.setText(date.getMonth() + "/" + date.getDay() + "/" + date.getYear() + "  Sunday");
            }
            else if (dayOfWeek == 2) {
                time.setText(date.getMonth() + "/" + date.getDay() + "/" + date.getYear() + "  Monday");
            }
            else if (dayOfWeek == 3) {
                time.setText(date.getMonth() + "/" + date.getDay() + "/" + date.getYear() + "  Tuesday");
            }
            else if (dayOfWeek == 4) {
                time.setText(date.getMonth() + "/" + date.getDay() + "/" + date.getYear() + "  Wednesday");
            }
            else if (dayOfWeek == 5) {
                time.setText(date.getMonth() + "/" + date.getDay() + "/" + date.getYear() + "  Thursday");
            }
            else if (dayOfWeek == 6) {
                time.setText(date.getMonth() + "/" + date.getDay() + "/" + date.getYear() + "  Friday");
            }
            else if (dayOfWeek == 7) {
                time.setText(date.getMonth() + "/" + date.getDay() + "/" + date.getYear() + "  Saturday");
            }

            // Find how many day passed or will be coming
            Calendar cal1 = new GregorianCalendar();
            Calendar cal2 = new GregorianCalendar();
            cal1.set(date.getYear(), date.getMonth() - 1, date.getDay());
            LocalDate currentdate = LocalDate.now();
            int month = 0;
            if (currentdate.getMonth().equals(Month.JANUARY)) {
                month = 1;
            }
            else if (currentdate.getMonth().equals(Month.FEBRUARY)) {
                month = 2;
            }
            else if (currentdate.getMonth().equals(Month.MARCH)) {
                month = 3;
            }
            else if (currentdate.getMonth().equals(Month.APRIL)) {
                month = 4;
            }
            else if (currentdate.getMonth().equals(Month.MAY)) {
                month = 5;
            }
            else if (currentdate.getMonth().equals(Month.JUNE)) {
                month = 6;
            }
            else if (currentdate.getMonth().equals(Month.JULY)) {
                month = 7;
            }
            else if (currentdate.getMonth().equals(Month.AUGUST)) {
                month = 8;
            }
            else if (currentdate.getMonth().equals(Month.SEPTEMBER)) {
                month = 9;
            }
            else if (currentdate.getMonth().equals(Month.OCTOBER)) {
                month = 10;
            }
            else if (currentdate.getMonth().equals(Month.NOVEMBER)) {
                month = 11;
            }
            else if (currentdate.getMonth().equals(Month.DECEMBER)) {
                month = 12;
            }
            cal2.set(currentdate.getYear(), month - 1, currentdate.getDayOfMonth());

            long numOfDays = (cal1.getTime().getTime() - cal2.getTime().getTime()) / (1000 * 60 * 60 * 24);

            if (numOfDays <= 0) {
                length.setText(Math.abs(numOfDays) + " Days Passed");
            }
            else if (numOfDays > 0) {
                length.setText(Math.abs(numOfDays) + " Days In The Future");
            }

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