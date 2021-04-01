package com.example.tutormeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Schedule extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private final String[] months = { " ", "January", "February", "March"}; //Just testing. FIX LATER
    private final String[] days = { " ", "1", "2", "3"}; //Just testing. FIX LATER
    private final String[] years = { " ", "2021", "2022", "2023"}; //Just testing. FIX LATER

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);

        Spinner month = (Spinner) findViewById(R.id.month);
        month.setOnItemSelectedListener(this);

        Spinner day = (Spinner) findViewById(R.id.day);
        day.setOnItemSelectedListener(this);

        Spinner year = (Spinner) findViewById(R.id.year);
        year.setOnItemSelectedListener(this);

        Spinner course = (Spinner) findViewById(R.id.course);
        course.setOnItemSelectedListener(this);

        Spinner method = (Spinner) findViewById(R.id.method); //Online or offline
        method.setOnItemSelectedListener(this);

        Spinner location = (Spinner) findViewById(R.id.location);
        location.setOnItemSelectedListener(this);

        //Create ArrayAdapter and a default spinner layout
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, months);
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, days);
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);

        //Specify the layout to use when the list of choices appears
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month.setAdapter(monthAdapter);  //connect ArrayAdapter to <Spinner>

        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day.setAdapter(dayAdapter);

        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(yearAdapter);
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        switch (parent.getId()) {
            case R.id.month:
                Toast.makeText(this, months[position] + " selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.day:
                Toast.makeText(this, days[position] + " selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.year:
                Toast.makeText(this, years[position] + " selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.course:
                break;

            case R.id.method:
                break;

            case R.id.location:
                break;

        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        //Something
    }
}


//Third row: online or offline
//Fourth row: If online, choose NA (not applicable). If offline, choose state abbreviation
