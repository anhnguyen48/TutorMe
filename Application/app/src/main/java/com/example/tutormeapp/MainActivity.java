package com.example.tutormeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Button chatButton = (Button) findViewById(R.id.chatButton);
        chatButton.setOnClickListener(this);

        Button scheduleButton = (Button) findViewById(R.id.scheduleButton);
        scheduleButton.setOnClickListener(this);

        Button meetingButton = (Button) findViewById(R.id.meetingButton);
        meetingButton.setOnClickListener(this);

        Button emailButton = (Button) findViewById(R.id.emailButton);
        emailButton.setOnClickListener(this);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);
    }

    public void onClick (View v) {
        switch (v.getId()) {

            case R.id.chatButton:
                Intent i0 = new Intent(this, Chat.class);
                startActivity(i0); //Open Chat Activity
                break;

            case R.id.scheduleButton:
                Intent i1 = new Intent(this, Schedule.class);
                startActivity(i1); //Open Schedule Activity
                break;

            case R.id.meetingButton:
                Intent i2 = new Intent(this, Meeting.class);
                startActivity(i2);
                break;

            case R.id.emailButton:
                Intent i3 = new Intent(this, Email.class);
                startActivity(i3);
                break;
            case R.id.loginButton:
                Intent i4= new Intent(this, Login.class);
                startActivity(i4);
                break;
        }
    }
}