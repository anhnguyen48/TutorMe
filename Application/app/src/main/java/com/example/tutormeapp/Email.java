
package com.example.tutormeapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Email extends AppCompatActivity implements View.OnClickListener{

    private EditText address;
    private EditText subject;
    private EditText body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);

    }
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.sendButton:
                address = findViewById(R.id.address);
                subject = findViewById(R.id.subject);
                body = findViewById(R.id.body);
                Intent i1 = new Intent(Intent.ACTION_SENDTO);
                i1.setData(Uri.parse("mailto:"));
                i1.putExtra(i1.EXTRA_EMAIL, new String[]{address.getText().toString()});
                i1.putExtra(i1.EXTRA_SUBJECT, subject.getText().toString());
                i1.putExtra(i1.EXTRA_TEXT, body.getText().toString());
                startActivity(i1);
                break;
        }
    }
}



