package com.example.group_remember;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
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
    static final int REQUEST_IMAGE = 1;
    private static final int REQUEST_CAPTURE_IMAGE = 100;
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


        backGround0 = (ImageView) findViewById(R.id.b0);
        backGround1 = (ImageView) findViewById(R.id.b1);
        backGround2 = (ImageView) findViewById(R.id.b2);
        backGround3 = (ImageView) findViewById(R.id.b3);
        backGround4 = (ImageView) findViewById(R.id.b4);
        backGround5 = (ImageView) findViewById(R.id.b5);

        Intent intent = getIntent();
        Serializable serializable = getIntent().getSerializableExtra("dateList");//在另一个activity中用于获取对象
        if(serializable != null) {
            dateList = new ArrayList<Date>();
            dateList.addAll((ArrayList<Date>) serializable);//之后将serializable对象强转使用即可
        }
        number = intent.getIntExtra("int",0);
        version = intent.getStringExtra("version");
        date = dateList.get(number);


        camera = (Button) findViewById(R.id.camera);
        camera.setOnClickListener(this);
        finish = (Button)findViewById(R.id.finishb);
        finish.setOnClickListener(this);
        back = (Button)findViewById(R.id.backb);
        back.setOnClickListener(this);

    }
    static void saveBitmap(String name, Bitmap bm, Context mContext) {
        Log.d("Save Bitmap", "Ready to save picture");
        //指定我们想要存储文件的地址
        String TargetPath = mContext.getFilesDir() + "/images/";
        Log.d("Save Bitmap", "Save Path=" + TargetPath);
            //如果指定文件夹创建成功，那么我们则需要进行图片存储操作
        File appDir = new File(TargetPath);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        File saveFile = new File(TargetPath, name + ".jpg");
        try {
                FileOutputStream saveImgOut = new FileOutputStream(saveFile);
                // compress - 压缩的意思
                bm.compress(Bitmap.CompressFormat.JPEG, 100, saveImgOut);
                //存储完成后需要清除相关的进程
                saveImgOut.flush();
                saveImgOut.close();
                Log.d("Save Bitmap", "The picture is save to your phone!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }

//    public static File saveImage(Bitmap bmp, String path, String fileName) {
//        File appDir = new File(path);
//        if (!appDir.exists()) {
//            appDir.mkdir();
//        }
//        File file = new File(appDir, fileName);
//        try {
//            FileOutputStream fos = new FileOutputStream(file);
//            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//            fos.flush();
//            fos.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return file;
//    }

    public void radioClicked(View view) {
        boolean checked =((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.rb1:
                if(checked)
                    im = R.id.b0;
                    //date.setImage(R.id.b0);
                break;
            case R.id.rb2:
                if(checked)
                    im = R.id.b1;
                    //date.setImage(R.id.b1);
                break;
            case R.id.rb3:
                if(checked)
                    im = R.id.b2;
                //date.setImage(R.id.b2);
                break;
            case R.id.rb4:
                if(checked)
                    im = R.id.b3;
                    //date.setImage(R.id.b3);
                break;
            case R.id.rb5:
                if(checked)
                    im = R.id.b4;
                    //date.setImage(R.id.b4);
                break;
            case R.id.rb6:
                if(checked)
                    im = R.id.b5;
                  //date.setImage(R.id.b5);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
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
                        // no camera on this device
                    }
                break;
            case R.id.finishb:
                date.setImage(im);
                intent = new Intent(BackgroundOption.this, EditEventOption.class);
                intent.putExtra("dateList", (Serializable)dateList);
                intent.putExtra("int",number);
                startActivity(intent);
                break;
            case R.id.backb:
                intent = new Intent(BackgroundOption.this, EditEventOption.class);
                intent.putExtra("dateList", (Serializable)dateList);
                intent.putExtra("int",number);
                startActivity(intent);
                break;
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap thumbnail = (Bitmap) extras.get("data");
            saveBitmap(number+"",thumbnail,this);
        }
    }
}
