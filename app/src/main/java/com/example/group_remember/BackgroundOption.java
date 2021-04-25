package com.example.group_remember;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


public class BackgroundOption extends AppCompatActivity {

    Button camera;
    Button finish;
    Button back;
    static final int REQUEST_IMAGE= 1;
    private static final int REQUEST_CAPTURE_IMAGE = 100;
    String imageFilePath;

    ImageView backGround0;
    ImageView backGround1;
    ImageView backGround2;
    ImageView backGround3;
    ImageView backGround4;
    ImageView backGround5;
    //Date date = new Date();
    int im;
    ArrayList<Date> dateList;
    int number;
    String version;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_option);

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
        camera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                openCameraIntent();
                //Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //if(takePicIntent.resolveActivity(getPackageManager()) != null){
                //    startActivityForResult(takePicIntent, REQUEST_IMAGE);
               // }
            }
        });



        backGround0 = (ImageView) findViewById(R.id.b0);
        backGround1 = (ImageView) findViewById(R.id.b1);
        backGround2 = (ImageView) findViewById(R.id.b2);
        backGround3 = (ImageView) findViewById(R.id.b3);
        backGround4 = (ImageView) findViewById(R.id.b4);
        backGround5 = (ImageView) findViewById(R.id.b5);

        //Date date = new Date();
        //date.setImage(R.id.b0);
        finish = (Button)findViewById(R.id.finishb);
        finish.setOnClickListener(new View.OnClickListener() {
            Intent intent1;
            public void onClick(View view) {
                date.setImage(im);
                intent1 = new Intent(BackgroundOption.this, EditEventOption.class);
                intent1.putExtra("dateList", (Serializable)dateList);
                intent1.putExtra("int",number);
                startActivity(intent1);

            }
        });

        back = (Button)findViewById(R.id.backb);
        back.setOnClickListener(new View.OnClickListener() {
            Intent intent1;
            public void onClick(View view) {
                //date.setImage(im);
                intent1 = new Intent(BackgroundOption.this, EditEventOption.class);
                intent1.putExtra("dateList", (Serializable)dateList);
                intent1.putExtra("int",number);
                startActivity(intent1);

            }
        });

    }

    private void openCameraIntent() {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if (pictureIntent.resolveActivity(getPackageManager()) != null) {
            //創建一個資料夾去存圖片
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                //當創建資料夾失敗...
            }

            //當創建的資料夾不為null直，把創建資料夾的路徑帶給相機，並開啟系統資料夾
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "com.hello.kaiser.camerademo.provider", photoFile);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        photoURI);
                startActivityForResult(pictureIntent,
                        REQUEST_CAPTURE_IMAGE);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap thumbnail = (Bitmap) extras.get("data");
            //imageView.setBackground(new BitmapDrawable(getResources(), thumbnail));
            //ByteArrayOutputStream stream = new ByteArrayOutputStream();
            //thumbnail.compress(Bitmap.CompressFormat.PNG, 90, stream);
            //byte[] image = stream.toByteArray();
            Intent intent = new Intent(this, Day.class);
            intent.putExtra("photo", thumbnail);
            startActivity(intent);
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir =
                getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();
        return image;
    }

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



}
