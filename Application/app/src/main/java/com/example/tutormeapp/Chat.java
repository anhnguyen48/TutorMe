package com.example.tutormeapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Spinner;
import android.content.Intent;
import android.net.Uri;

public class Chat extends Activity implements OnClickListener, OnItemSelectedListener {
    private final String[] counselors = {"Jane George", "Adam Ventura", "Bill Tao", "Liam Miller", "Fiona Monroe", "Ivor Manson"};
    private final String[] numbers = {"smsto:5684237852", "smsto:7951234865", "smsto:7854963215", "smsto:2645781569", "smsto:3458961257", "smsto:8585963262"};
    private TextView selection;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        // Set up click listeners for all the buttons
        Button continueButton = (Button)findViewById(R.id.g_inquiries);
        continueButton.setOnClickListener(this);

        Button newButton = (Button)findViewById(R.id.registrar);
        newButton.setOnClickListener(this);

        Button aboutButton = (Button)findViewById(R.id.contact_counselor);
        aboutButton.setOnClickListener(this);

    }

    //avoids runtime check for permission to CALL_PHONE
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.g_inquiries:
                Uri uri = Uri.parse("smsto:5872365982");
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", "Hey TutorMe, I need some help!");
                startActivity(intent);
                break;

            case R.id.registrar:
                Uri uri2 = Uri.parse("smsto:5684520187");
                Intent intent2 = new Intent(Intent.ACTION_SENDTO, uri2);
                intent2.putExtra("sms_body", "Hey TutorMe Registrar, I need some help!");
                startActivity(intent2);

            case R.id.contact_counselor:
                Uri uri3 = Uri.parse("smsto:5684520187");
                Intent intent3 = new Intent(Intent.ACTION_SENDTO, uri3);
                intent3.putExtra("sms_body", "Hey TutorMe Tutor, I need some help!");
                startActivity(intent3);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

