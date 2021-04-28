
package com.example.tutormeapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.Locale;

public class Email extends AppCompatActivity implements View.OnClickListener{

    private EditText address;
    private EditText subject;
    private EditText body;
    private TextToSpeech speaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);
        Button sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(this);
        Button ttsButton = (Button) findViewById(R.id.ttsButton);
        ttsButton.setOnClickListener(this);
        speaker = new TextToSpeech(getApplicationContext(), i -> speaker.setLanguage(Locale.US)); // set up speaker with US english language
    }
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.sendButton:
                address = findViewById(R.id.address);
                subject = findViewById(R.id.subject);
                body = findViewById(R.id.body);
                Intent i1 = new Intent(Intent.ACTION_SENDTO); // set up intent for emailing without attachments
                i1.setData(Uri.parse("mailto:")); // set up uri to let user pick what email app they want to use
                i1.putExtra(i1.EXTRA_EMAIL, new String[]{address.getText().toString()}); // add email address
                i1.putExtra(i1.EXTRA_SUBJECT, subject.getText().toString()); // add email subject
                i1.putExtra(i1.EXTRA_TEXT, body.getText().toString()); // add email body
                startActivity(i1);
                break;
            case R.id.ttsButton:
                EditText bodyTwo = findViewById(R.id.body);
                speaker.speak(bodyTwo.getText().toString(), TextToSpeech.QUEUE_FLUSH, null); // speak out body text
                break;
        }
    }
}



