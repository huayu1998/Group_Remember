package com.example.group_remember;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class EditTextForEvent extends AppCompatActivity{

    Button back, finish;
    TextView name;
    EditText editText;
    boolean validTitleNDescription = false;

    //接收信息
    ArrayList<Date> dateList;
    int number;
    String version;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_edit_text);

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

        back = (Button) findViewById(R.id.back);
        finish = (Button) findViewById(R.id.finish);
        name = (TextView) findViewById(R.id.eventName);
        editText = (EditText) findViewById(R.id.editTextContent);

        if(version.equals("old")){
            name.setText(date.getTopic());
            editText.setText(date.getText());
        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditTextForEvent.this, EditEventOption.class);
                intent.putExtra("dateList", (Serializable)dateList);
                intent.putExtra("int",number);
                intent.putExtra("version",version);
                startActivity(intent);
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!setTopicNDescription()) {
                    Toast.makeText(EditTextForEvent.this, "Please enter title and description.", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(EditTextForEvent.this, EditEventOption.class);
                    intent.putExtra("dateList", (Serializable)dateList);
                    intent.putExtra("int",number);
                    intent.putExtra("version",version);
                    startActivity(intent);
                }
            }
        });

    }

    public boolean setTopicNDescription() {

        if (!name.getText().toString().isEmpty() && !editText.getText().toString().isEmpty()) {
            date.setTopic(name.getText().toString());
            date.setText(editText.getText().toString());
            validTitleNDescription = true;
        }
        else {
            validTitleNDescription = false;
        }
        return validTitleNDescription;
    }
}






