package com.example.group_remember;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


public class BackgroundOption extends AppCompatActivity implements View.OnClickListener{

    Button camera;
    Button finish;
    Button back;
    private static final int REQUEST_IMAGE = 1;
    String imageFilePath;

    ImageView backGround0;
    ImageView backGround1;
    ImageView backGround2;
    ImageView backGround3;
    ImageView backGround4;
    ImageView backGround5;

    int im;
    ArrayList<Date> dateList;
    int number;
    String version;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_option);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        backGround0 = (ImageView) findViewById(R.id.b0);
        backGround1 = (ImageView) findViewById(R.id.b1);
        backGround2 = (ImageView) findViewById(R.id.b2);
        backGround3 = (ImageView) findViewById(R.id.b3);
        backGround4 = (ImageView) findViewById(R.id.b4);
        backGround5 = (ImageView) findViewById(R.id.b5);


        Intent intent = getIntent();
        Serializable serializable = getIntent().getSerializableExtra("dateList");
        if(serializable != null) {
            dateList = new ArrayList<Date>();
            dateList.addAll((ArrayList<Date>) serializable);
        }
        number = intent.getIntExtra("int",0);
        version = intent.getStringExtra("version");
        date = dateList.get(number);


        im = date.getImage();
        if(version.equals("old")){
            int i = date.getImage();
            RadioButton radioButton;
            switch (i) {
                case 1:
                    radioButton = findViewById(R.id.rb1);
                    if(!radioButton.isChecked()) {
                        radioButton.setChecked(true);
                        radioButton.callOnClick();
                    }
                    break;
                case 2:
                    radioButton = findViewById(R.id.rb2);
                    if(!radioButton.isChecked()) {
                        radioButton.setChecked(true);
                        radioButton.callOnClick();
                    }
                    break;
                case 3:
                    radioButton = findViewById(R.id.rb3);
                    if(!radioButton.isChecked()) {
                        radioButton.setChecked(true);
                        radioButton.callOnClick();
                    }
                    break;
                case 4:
                    radioButton = findViewById(R.id.rb4);
                    if(!radioButton.isChecked()) {
                        radioButton.setChecked(true);
                        radioButton.callOnClick();
                    }
                    break;
                case 5:
                    radioButton = findViewById(R.id.rb5);
                    if(!radioButton.isChecked()) {
                        radioButton.setChecked(true);
                        radioButton.callOnClick();
                    }
                    break;
                case 6:
                    radioButton = findViewById(R.id.rb6);
                    if(!radioButton.isChecked()) {
                        radioButton.setChecked(true);
                        radioButton.callOnClick();
                    }
                    break;
            }
        }

        camera = (Button) findViewById(R.id.camera);
        camera.setOnClickListener(this);
        finish = (Button)findViewById(R.id.finishb);
        finish.setOnClickListener(this);
        back = (Button)findViewById(R.id.backb);
        back.setOnClickListener(this);

    }
    static void saveBitmap(String name, Bitmap bm, Context mContext) {
        Log.d("Save Bitmap", "Ready to save picture");
        String TargetPath = mContext.getFilesDir() + "/images/";
        Log.d("Save Bitmap", "Save Path=" + TargetPath);
        File appDir = new File(TargetPath);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        File saveFile = new File(TargetPath, name + ".jpg");
        try {
                FileOutputStream saveImgOut = new FileOutputStream(saveFile);
                bm.compress(Bitmap.CompressFormat.JPEG, 100, saveImgOut);
                saveImgOut.flush();
                saveImgOut.close();
                Log.d("Save Bitmap", "The picture is save to your phone!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }


    public void radioClicked(View view) {
        switch(view.getId()) {
            case R.id.rb1:
                im = 1;
                break;
            case R.id.rb2:
                im =2;
                break;
            case R.id.rb3:
                im = 3;
                break;
            case R.id.rb4:
                im = 4;
                break;
            case R.id.rb5:
                im = 5;
                break;
            case R.id.rb6:
                im = 6;
                break;
        }

    }



    @Override
    public void onClick(View view) {
        Intent intent;
        switch(view.getId()) {
            case R.id.camera:
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (getApplicationContext().getPackageManager().hasSystemFeature(
                        PackageManager.FEATURE_CAMERA)) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE);
                    } else {

                    }
                im = -1;
                break;
            case R.id.finishb:
                if(im == 0){
                    Toast.makeText(this, "Please select a background or take a photo!", Toast.LENGTH_LONG).show();
                }
                else {
                    date.setImage(im);
                    intent = new Intent(BackgroundOption.this, EditEventOption.class);
                    intent.putExtra("dateList", (Serializable) dateList);
                    intent.putExtra("int", number);
                    intent.putExtra("version", version);
                    startActivity(intent);
                }
                break;
            case R.id.backb:
                intent = new Intent(BackgroundOption.this, EditEventOption.class);
                intent.putExtra("dateList", (Serializable)dateList);
                intent.putExtra("int",number);
                intent.putExtra("version",version);
                startActivity(intent);
                break;
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK) {
            System.out.println("check");
            Bundle extras = data.getExtras();
            Bitmap thumbnail = (Bitmap) extras.get("data");
            saveBitmap(date.getPhoto()+"",thumbnail,this);
            Toast.makeText(this, "Your photo has been saved!", Toast.LENGTH_LONG).show();
        }
        else {
            im = date.getImage();
        }
    }
}
