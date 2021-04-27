package com.example.group_remember;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class EditEventOption extends AppCompatActivity implements View.OnClickListener {

    Button back, done, addTextContent, chooseDate, backgroundOption, addMusic,delete;

    //接收信息
    ArrayList<Date> dateList;
    int number;
    String version;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


        setContentView(R.layout.activity_edit_event_option);

        back = (Button) findViewById(R.id.back);
        done = (Button) findViewById(R.id.done);
        delete = (Button)findViewById(R.id.delete);
        addTextContent = (Button) findViewById(R.id.addTextContent);
        chooseDate = (Button) findViewById(R.id.chooseDate);
        backgroundOption = (Button) findViewById(R.id.backgroundOption);
        addMusic = (Button) findViewById(R.id.addMusic);

        delete.setOnClickListener(this);
        back.setOnClickListener(this);
        done.setOnClickListener(this);
        addTextContent.setOnClickListener(this);
        chooseDate.setOnClickListener(this);
        backgroundOption.setOnClickListener(this);
        addMusic.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.back:
                Intent intent1;
                if(version.equals("new")){
                    intent1 = new Intent(EditEventOption.this, MainActivity.class);
                    dateList.remove(dateList.size()-1);
                    intent1.putExtra("dateList", (Serializable)dateList);
                }
                else{
                    intent1 = new Intent(EditEventOption.this, Day.class);
                    intent1.putExtra("dateList", (Serializable)dateList);
                    intent1.putExtra("int",number);
                }
                startActivity(intent1);
                break;
            case R.id.done:
                if(date.finish()) {
                    Intent intent2 = new Intent(EditEventOption.this, MainActivity.class);
                    intent2.putExtra("dateList", (Serializable) dateList);
                    startActivity(intent2);
                }
                else {
                    Toast.makeText(this, "The date is not finished!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.addTextContent:
                Intent intent3 = new Intent(EditEventOption.this, EditTextForEvent.class);
                //传递信息
                intent3.putExtra("dateList", (Serializable)dateList);
                intent3.putExtra("int",number);
                intent3.putExtra("version",version);
                startActivity(intent3);
                break;
            case R.id.chooseDate:
                Intent intent4 = new Intent(EditEventOption.this, ChooseDate.class);
                intent4.putExtra("dateList", (Serializable)dateList);
                intent4.putExtra("int",number);
                intent4.putExtra("version",version);
                startActivity(intent4);
                break;
            case R.id.backgroundOption:
                Intent intent5 = new Intent(EditEventOption.this, BackgroundOption.class);
                intent5.putExtra("dateList", (Serializable)dateList);
                intent5.putExtra("int",number);
                intent5.putExtra("version",version);
                startActivity(intent5);
                break;
            case R.id.addMusic:
                Intent intent6 = new Intent(EditEventOption.this, PlayMusic.class);
                intent6.putExtra("dateList", (Serializable)dateList);
                intent6.putExtra("int",number);
                intent6.putExtra("version",version);
                startActivity(intent6);
                break;
            case R.id.delete:
                Intent intent7 = new Intent(EditEventOption.this, MainActivity.class);
                if(version.equals("new")){
                    dateList.remove(dateList.size()-1);

                }
                else{
                    dateList.remove(number);
                }
                intent7.putExtra("dateList", (Serializable)dateList);
                startActivity(intent7);
                break;

        }
    }

}