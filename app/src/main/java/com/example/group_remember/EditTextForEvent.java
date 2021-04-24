package com.example.group_remember;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditTextForEvent extends AppCompatActivity{

    Button finish;
    TextView name;
    EditText editText;
    Date date = new Date();
    boolean validTitleNDescription = false;
    boolean validDate = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        finish = (Button) findViewById(R.id.finish);
        name = (TextView) findViewById(R.id.eventName);
        editText = (EditText) findViewById(R.id.editTextContent);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditTextForEvent.this, EditEventOption.class);
                startActivity(intent);
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






