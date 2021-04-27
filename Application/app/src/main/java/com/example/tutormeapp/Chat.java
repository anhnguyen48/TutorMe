package com.example.tutormeapp;

import androidx.fragment.app.FragmentActivity;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebViewClient;
import android.widget.TabHost;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.KeyEvent;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.webkit.WebView;
import android.widget.Toast;
import android.widget.Spinner;
import android.content.Intent;
import android.net.Uri;

public class Chat extends Activity implements OnClickListener, OnItemSelectedListener {
    private final String[] counselors = {"Jane George", "Adam Ventura", "Bill Tao", "Liam Miller", "Fiona Monroe", "Ivor Manson"};
    private final String[] numbers = {"5684237852", "7951234865", "7854963215", "2645781569", "3458961257", "8585963262"};
    private TextView selection;

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
        }
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

        }
        public void onItemSelected(AdapterView<?> parent, View v, int position,
                               long id) {
            selection.setText(counselors[position]);
            String number_entry = numbers[position];
            Uri uri3 = Uri.parse(number_entry);
            Intent i3 = new Intent(Intent.ACTION_CALL,uri3);
            startActivity(i3);
        }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

