package com.example.tutormeapp;

import android.app.Activity;
import android.content.Intent;
import android.content.ComponentName;
import android.os.Bundle;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Chat extends Activity implements OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        // Set up click listeners for all the buttons
        Button continueButton = (Button)findViewById(R.id.general_inquiries);
        continueButton.setOnClickListener(this);

        Button newButton = (Button)findViewById(R.id.registrar);
        newButton.setOnClickListener(this);

        Button aboutButton = (Button)findViewById(R.id.contact_counselor);
        aboutButton.setOnClickListener(this);

    }

    //avoids runtime check for permission to CALL_PHONE
    public void onClick(View v)  {
        switch (v.getId()) {

            //explicit intent
            case R.id.general_inquiries:
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms: 1234567890"));
                break;

            //implicit intent, call GoogleMaps
            case R.id.registrar:
                Intent sendIntent2 = new Intent(Intent.ACTION_VIEW);
                sendIntent2.setData(Uri.parse("sms: 0987654321"));
                break;

            case R.id.contact_counselor:
                Uri uri3 = Uri.parse("tel:6175551212");
                Intent i3 = new Intent(Intent.ACTION_CALL,uri3);
                startActivity(i3);
                break;

        }
    }
}
