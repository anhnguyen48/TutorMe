package com.example.tutormeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Email extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);
        Button sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(this);
    }
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.sendButton:
                //after user sends the email, take user back to Main Activity
                Intent i1= new Intent(this, MainActivity.class);
                startActivity(i1);
                break;
        }
    }
}
