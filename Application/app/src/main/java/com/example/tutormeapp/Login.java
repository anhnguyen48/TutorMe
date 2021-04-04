package com.example.tutormeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button loginButton = (Button) findViewById(R.id.loginScreenButton);
        loginButton.setOnClickListener(this);
    }
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.loginScreenButton:
                Intent i1= new Intent(this, MainActivity.class);
                startActivity(i1);
                break;
        }
    }
}
