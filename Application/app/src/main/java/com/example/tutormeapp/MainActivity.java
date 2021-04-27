package com.example.tutormeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //public String URL = "jdbc:mysql://frodo.bentley.edu:3306/tutorme";
    //public String username = "harry";
    //public String password = "harry";
    private String userID;

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

        userID = getIntent().getStringExtra("userID"); //grab userID from LoginActivity
    }

    public void onClick (View v) {
        switch (v.getId()) {

            case R.id.chatButton:
                Intent i0 = new Intent(this, Chat.class);
                i0.putExtra("userID", userID); //Bring userID to ChatActivity for later use
                startActivity(i0); //Open Chat Activity
                break;

            case R.id.scheduleButton:
                Intent i1 = new Intent(this, Schedule.class);
                i1.putExtra("userID", userID); //Bring userID to ScheduleActivity for later use
                startActivity(i1); //Open Schedule Activity
                break;

            case R.id.meetingButton:
                Intent i2 = new Intent(this, Meeting.class);
                i2.putExtra("userID", userID); //Bring userID to MeetingActivity for later use
                startActivity(i2);
                break;

            case R.id.emailButton:
                Intent i3 = new Intent(this, Email.class);
                i3.putExtra("userID", userID); //Bring userID to EmailActivity for later use
                startActivity(i3);
                break;
        }
    }
}