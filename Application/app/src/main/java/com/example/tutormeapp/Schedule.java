package com.example.tutormeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Schedule extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, AdapterView.OnItemClickListener {

    public String URL = "jdbc:mysql://frodo.bentley.edu:3306/tutorme";
    public String username = "harry";
    public String password = "harry";

    private final String[] months = { "(Month)", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

    private final String[] days = { "(Day)", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
    "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

    private final String[] years = { "(Year)", "2021", "2022", "2023", "2024", "2025"};

    private final String[] methods = {"(Method)", "Online", "Offline"};

    private final String[] locations = {"(Location)", "Not Applicable", "AL", "AK", "AS", "AZ", "AR", "CA", "CO",
    "CT", "DE", "DC", "FL", "GA", "GU", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA",
    "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "MP", "OH", "OK", "OR",
    "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "VI", "WA", "WV", "WI", "WY"};

    private ArrayList<String> courses = new ArrayList<String>();

    private ArrayList<String> schedule_list = new ArrayList<>();

    private String monthString;
    private String dayString;
    private String yearString;
    private String courseString;
    private String methodString;
    private String locationString;
    private String userID;
    private String class_name;
    private ListView listview2;
    private boolean check = true;
    private boolean check2 = true;

    private String classID;
    private String method;
    private String state;
    private String date;

    private Integer place;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);

        ActionBar actionBar = getSupportActionBar();

        userID = getIntent().getStringExtra("userID"); //grab userID from MainActivity

        listview2 = (ListView) findViewById(R.id.list2);
        listview2.setOnItemClickListener(this);

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

        courses.add("(Course)");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Statement stmt = null;

        try (Connection con = DriverManager.getConnection(URL, username, password)) {
            stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("SELECT ClassName, ClassDescription FROM Class;");

            //for each record in CLass table add them to course2 ArrayList
            while (result.next()) {
                class_name = result.getString("ClassName") + ": " + result.getString("ClassDescription");
                courses.add(class_name);
            }
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(this, "Fail to close to the database. Try again", Toast.LENGTH_SHORT).show();

            }
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "Fail to connect to the database. Try again", Toast.LENGTH_SHORT).show();
        }

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
        year.setSelection(1);

        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        course.setAdapter(courseAdapter);

        methodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        method.setAdapter(methodAdapter);

        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(locationAdapter);


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
                courseString = courses.get(position);
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
        //Do nothing
    }

    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.findButton:

                if (monthString.equals("(Month)")) {
                    Toast.makeText(this, "Option 'month' is not chosen", Toast.LENGTH_SHORT).show();
                    check = false;
                }

                if (dayString.equals("(Day)")) {
                    Toast.makeText(this, "Option 'day' haven't been chosen", Toast.LENGTH_SHORT).show();
                    check = false;
                }

                if (courseString.equals("(Course)")) {
                    Toast.makeText(this, "Option 'course' haven't been chosen", Toast.LENGTH_SHORT).show();
                    check = false;
                }

                if (methodString.equals("(Method)")) {
                    Toast.makeText(this, "Option 'preferred tutoring method' haven't been chosen", Toast.LENGTH_SHORT).show();
                    check = false;
                }

                if (locationString.equals("(Location)")) {
                    Toast.makeText(this, "Option 'location' haven't been chosen", Toast.LENGTH_SHORT).show();
                    check = false;
                }

                if (check) {
                    try (Connection con = DriverManager.getConnection(URL, username, password)) {
                        date = yearString + "-" + monthString + "-" + dayString;
                        courseString = courseString.substring(0,5);

                        String temp_sql = "SELECT ClassID, IsOnline, State FROM Class WHERE ClassName = ? LIMIT 1";
                        PreparedStatement stmt2 = con.prepareStatement(temp_sql);
                        stmt2.setString(1, courseString);
                        ResultSet result2 = stmt2.executeQuery();
                        while (result2.next()) {
                            classID = result2.getString("ClassID");
                            method = result2.getString("IsOnline");
                            state = result2.getString("State");
                        }

                        if ((method.equals("0") & methodString.equals("Online")) | (method.equals("1") & methodString.equals("Offline"))) {
                            Toast.makeText(this, method, Toast.LENGTH_SHORT).show();
                            Toast.makeText(this, "There is no section available for " +
                                   "your chosen tutoring method", Toast.LENGTH_SHORT).show();
                            check2 = false;
                        }

                        if (methodString.equals("Online") & !locationString.equals("Not Applicable")) {
                            Toast.makeText(this, "Location option should be 'not applicable' " +
                                    "since preferred tutoring method is online", Toast.LENGTH_SHORT).show();
                            check2 = false;
                        }

                        if (methodString.equals("Offline") & locationString.equals("Not Applicable")) {
                            Toast.makeText(this, "Location option should  not be 'not applicable' " +
                                    "since preferred tutoring method is offline", Toast.LENGTH_SHORT).show();
                            check2 = false;
                        }

                        if (methodString.equals("Offline")) {
                            if (!state.equals(locationString)) {
                                Toast.makeText(this, "There is no section available for " +
                                        "your chosen location", Toast.LENGTH_SHORT).show();
                                check2 = false;
                            }
                        }

                        if (check2) {
                            String sql = "SELECT SectionDate, SectionTime, Length, Capacity, SectionID " +
                                    "FROM Section WHERE ClassID = ? AND SectionDate = ?";
                            PreparedStatement stmt = con.prepareStatement(sql);
                            stmt.setString(1, classID);
                            stmt.setString(2, date);
                            ResultSet result = stmt.executeQuery();
                            while (result.next()) {
                                schedule_list.add(courseString + " " +
                                        result.getString("SectionDate") + " " +
                                        result.getString("SectionTime") + " " +
                                        result.getString("Length") + " " +
                                        result.getString("Capacity") + " " +
                                        result.getString("SectionID"));
                            }

                            if (schedule_list.isEmpty()) {
                                Toast.makeText(this, "There is no section available", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                CustomAdapter customAdapter = new CustomAdapter(this, schedule_list);
                                listview2.setAdapter(customAdapter);
                            }
                            try {
                                if (con != null)
                                    con.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                                Toast.makeText(this, "Fail to close to the database. Try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Fail to connect to the database. Try again", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuschedule, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signup:
                String meetingInfo = schedule_list.get(place);
                String[] detail_info = meetingInfo.split(" ");
                String Section = detail_info[5];

                PreparedStatement stmt;
                try (Connection con = DriverManager.getConnection(URL, username, password)) {
                    stmt = con.prepareStatement("INSERT into Assignment value(?,?);");
                    stmt.setString(1, userID);
                    stmt.setString(2, Section);
                    stmt.executeUpdate();
                   String registered = "Successfully registered for " + detail_info[0] + " section on "
                         + detail_info[1];
                    Toast.makeText(this, registered, Toast.LENGTH_LONG).show();

                    try {
                        if (con != null)
                            con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Fail to close to the database. Try again", Toast.LENGTH_SHORT).show();

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Fail to connect to the database. Try again", Toast.LENGTH_SHORT).show();
                }
                return true;

            default:
                //Do nothing

                return super.onOptionsItemSelected(item);
        }
    }

    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        place = position; //store section's index in ArrayList
    }

}


//Third row: online or offline
//Fourth row: If online, choose NA (not applicable). If offline, choose state abbreviation
