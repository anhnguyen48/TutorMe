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
        speaker = new TextToSpeech(getApplicationContext(), i -> speaker.setLanguage(Locale.US));
    }
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.sendButton:
                EditText address = findViewById(R.id.address);
                EditText subject = findViewById(R.id.subject);
                EditText body = findViewById(R.id.body);
                Intent i1= new Intent(Intent.ACTION_SENDTO);
                i1.setData(Uri.parse("mailto:"));
                i1.putExtra(i1.EXTRA_EMAIL, new String[]{address.getText().toString()});
                i1.putExtra(i1.EXTRA_SUBJECT, subject.getText().toString());
                i1.putExtra(i1.EXTRA_TEXT, body.getText().toString());
                startActivity(i1);
                break;
            case R.id.ttsButton:
                EditText bodyTwo = findViewById(R.id.body);
                speaker.speak(bodyTwo.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                break;
        }
    }
}
