package com.example.tutormeapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Spinner;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;

public class Chat extends Activity implements OnClickListener, OnItemSelectedListener {

    //names of available counselors
    private final String[] counselors = {"Jane George", "Adam Ventura", "Bill Tao", "Liam Miller", "Fiona Monroe", "Ivor Manson"};

    //cellphone numbers
    private final String[] numbers = {"smsto:5684237852", "smsto:7951234865", "smsto:7854963215", "smsto:2645781569", "smsto:3458961257", "smsto:8585963262"};
    private TextView selection; //user's current selection of counselor

    //used for animation
    private RelativeLayout layout;
    private ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        layout = (RelativeLayout)findViewById(R.id.layout);
        image = (ImageView) findViewById(R.id.img);
        image.setImageResource(R.drawable.shape);

        // Load the appropriate animation
        Animation an =  AnimationUtils.loadAnimation(this, R.anim.shakeitup);
        // Register a listener
        an.setAnimationListener(new MyAnimationListener());
        // Start the animation
        layout.startAnimation(an);

        // Set up click listeners for all the buttons
        Button webButton = (Button)findViewById(R.id.web_help);
        webButton.setOnClickListener(this);

        Button mapButton = (Button)findViewById(R.id.bentley_university);
        mapButton.setOnClickListener(this);

        Button continueButton = (Button)findViewById(R.id.g_inquiries);
        continueButton.setOnClickListener(this);

        Button newButton = (Button)findViewById(R.id.registrar);
        newButton.setOnClickListener(this);

        Button aboutButton = (Button)findViewById(R.id.contact_counselor);
        aboutButton.setOnClickListener(this);

    }

    public class MyAnimationListener implements Animation.AnimationListener {

        public void onAnimationEnd(Animation animation) {
            // what to do when animation ends
        }
        public void onAnimationRepeat(Animation animation) {
            // what to do when animation loops
        }

        public void onAnimationStart(Animation animation) {
            // what to do when animation starts
        }

    }

    //avoids runtime check for permission to CALL_PHONE
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.web_help:
                Intent i1 = new Intent(this, WebLookup.class);
                startActivity(i1); //Open WebLookup Activity
                break;

            case R.id.bentley_university:
                Uri uri1 = Uri.parse("geo:42.3854, -71.2219?z=16"); //coordinate of Bentley University
                Intent i2 = new Intent(Intent.ACTION_VIEW, uri1);
                i2.setPackage("com.google.android.apps.maps");
                startActivity(i2);
                break;

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
                break;

            case R.id.contact_counselor:
                selection = (TextView) findViewById(R.id.selection);

                Spinner spin = (Spinner) findViewById(R.id.find_counselor);
                spin.setOnItemSelectedListener(this);   //set listener

                //Create an ArrayAdapter and a default spinner layout
                ArrayAdapter<String> aa = new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_spinner_item,
                        counselors);

                //Specify the layout to use when the list of choices appears
                aa.setDropDownViewResource(
                        android.R.layout.simple_spinner_dropdown_item);
                spin.setAdapter(aa);  //connect ArrayAdapter to <Spinner>
                break;
        }
    }
    //listener methods for callbacks
    public void onItemSelected(AdapterView<?> parent, View v, int position,
                               long id) {
        selection.setText(counselors[position]);
    }
    public void onNothingSelected(AdapterView<?> parent) {
        selection.setText("");
    }

}




