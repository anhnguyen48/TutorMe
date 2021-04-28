package com.example.tutormeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public String URL = "jdbc:mysql://frodo.bentley.edu:3306/tutorme";
    public String username = "harry";
    public String password = "harry";
    private String userID;
    private String firstName;
    private String lastName;

    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder notifyDetails = null;
    private int SIMPLE_NOTFICATION_ID = 1;
    private String contentTitle = "Welcome back!";
    private String contentText = "Hello ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        userID = getIntent().getStringExtra("userID"); //grab userID from LoginActivity

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try (Connection con = DriverManager.getConnection(URL, username, password)) {
            EditText user = findViewById(R.id.username);
            EditText pass = findViewById(R.id.password);
            String sql = "SELECT FirstName, LastName FROM Person WHERE PersonID = ? LIMIT 1";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, userID);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                firstName = result.getString("FirstName");
                lastName = result.getString("LastName");
            }
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(this, "Fail to close to the database. Try again", Toast.LENGTH_SHORT).show();

            }
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "Fail to connect to the database. Try again", Toast.LENGTH_SHORT).show();
        }

        contentText = contentText + firstName + " " + lastName;

        mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default",
                    "Channel foobar",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Channel description");
            channel.setLightColor(Color.GREEN);
            mNotificationManager.createNotificationChannel(channel);
        }

        //set icon, text, and time on notification status bar
        notifyDetails = new NotificationCompat.Builder(this, "default")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setTimeoutAfter(2000)
                .setAutoCancel(true);     //cancel Notification after clicking on it

        mNotificationManager.notify(SIMPLE_NOTFICATION_ID,
                notifyDetails.build());

        Button chatButton = (Button) findViewById(R.id.chatButton);
        chatButton.setOnClickListener(this);

        Button scheduleButton = (Button) findViewById(R.id.scheduleButton);
        scheduleButton.setOnClickListener(this);

        Button meetingButton = (Button) findViewById(R.id.meetingButton);
        meetingButton.setOnClickListener(this);

        Button emailButton = (Button) findViewById(R.id.emailButton);
        emailButton.setOnClickListener(this);

        Button webButton = (Button) findViewById(R.id.localTutoringCenters);
        webButton.setOnClickListener(this);


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

            case R.id.localTutoringCenters:
                Intent i4 = new Intent(this, WebLookup.class);
                i4.putExtra("userID", userID); //Bring userID to WebLookupActivity for later use
                startActivity(i4);
                break;
        }
    }
}