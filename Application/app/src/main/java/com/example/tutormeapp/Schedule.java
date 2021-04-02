package com.example.tutormeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class Schedule extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private final String[] months = { "(Month)", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
            "Sep", "Oct", "Nov", "Dec"};

    private final String[] days = { "(Day)", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
    "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

    private final String[] years = { "(Year)", "2021", "2022", "2023", "2024", "2025"};

    private final String[] courses = {"(Course)", "CS180: Programming Fundamentals","CS230: Programming with Python",
    "CS350: Database Management Systems", "CS480: Advanced Application Development Technology",
    "MA131: Calculus I", "MA139: Calculus II", "MA233: Calculus III", "MA252: Regression Analysis"};

    private final String[] methods = {"(Method)", "Online", "Offline"};

    private final String[] locations = {"(Location)", "Not Applicable", "AL", "AK", "AS", "AZ", "AR", "CA", "CO",
    "CT", "DE", "DC", "FL", "GA", "GU", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA",
    "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "MP", "OH", "OK", "OR",
    "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "VI", "WA", "WV", "WI", "WY"};

    private TextView someText;
    private String monthString;
    private String dayString;
    private String yearString;
    private String courseString;
    private String methodString;
    private String locationString;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);

        Button findButton = (Button) findViewById(R.id.findButton);
        findButton.setOnClickListener(this);

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
        ArrayAdapter<String> courseAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, courses);
        ArrayAdapter<String> methodAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, methods);
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, locations);

        //Specify the layout to use when the list of choices appears
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month.setAdapter(monthAdapter);  //connect ArrayAdapter to <Spinner>

        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day.setAdapter(dayAdapter);

        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(yearAdapter);

        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        course.setAdapter(courseAdapter);

        methodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        method.setAdapter(methodAdapter);

        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(locationAdapter);

        someText = (TextView) findViewById(R.id.Placeholder);
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        switch (parent.getId()) {
            case R.id.month:
                monthString = months[position];
                break;

            case R.id.day:
                dayString = days[position];
                break;

            case R.id.year:
                yearString = years[position];
                break;

            case R.id.course:
                courseString = courses[position];
                break;

            case R.id.method:
                methodString = methods[position];
                break;

            case R.id.location:
                locationString = locations[position];
                break;

        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        //Something
    }

    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.findButton:
                someText.setText("Date: " + monthString + " " + dayString + ", " + yearString +
                        ". Course: " + courseString + ". Method & location: " + methodString +
                        " & " + locationString + ".");
        }
    }
}


//Third row: online or offline
//Fourth row: If online, choose NA (not applicable). If offline, choose state abbreviation
