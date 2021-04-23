package com.example.group_remember;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;


public class BackgroundOption extends AppCompatActivity {

    Button camera;
    static final int REQUEST_IMAGE= 1;

    ImageView backGround0;
    ImageView backGround1;
    ImageView backGround2;
    ImageView backGround3;
    ImageView backGround4;
    ImageView backGround5;
    Date date = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_option);

        camera = (Button) findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(takePicIntent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(takePicIntent, REQUEST_IMAGE);
                }
            }
        });

        backGround0 = (ImageView) findViewById(R.id.b0);
        backGround1 = (ImageView) findViewById(R.id.b1);
        backGround2 = (ImageView) findViewById(R.id.b2);
        backGround3 = (ImageView) findViewById(R.id.b3);
        backGround4 = (ImageView) findViewById(R.id.b4);
        backGround5 = (ImageView) findViewById(R.id.b5);

        Date date = new Date();
        date.setImage(R.id.b0);

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

    public void radioClicked(View view) {
        boolean checked =((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.rb1:
                if(checked)
                    date.setImage(R.id.b0);
                    RadioButton rb = (RadioButton)findViewById(R.id.rb2);
                    rb.setChecked(!checked);
                break;
            case R.id.rb2:
                if(checked)
                    date.setImage(R.id.b1);
                    RadioButton rb2 = (RadioButton)findViewById(R.id.rb1);
                    rb2.setChecked(!checked);
                break;
            case R.id.rb3:
                date.setImage(R.id.b2);
                break;
            case R.id.rb4:
                if(checked)
                    date.setImage(R.id.b3);
                break;
            case R.id.rb5:
                if(checked)
                    date.setImage(R.id.b4);
                break;
            case R.id.rb6:
                date.setImage(R.id.b5);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }

    }



}
