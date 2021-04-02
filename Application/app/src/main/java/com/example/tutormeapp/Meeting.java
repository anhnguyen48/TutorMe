package com.example.tutormeapp;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Meeting extends AppCompatActivity {

    private ListView listview;
    private ArrayList<String> list_string = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting);

        list_string.add("Jan/04/2021 15:30 1 CS480"); //example
        list_string.add("Jan/05/2021 14:00 2 MA252"); //example

        listview = (ListView)findViewById(R.id.list);

        CustomAdapter customAdapter = new CustomAdapter(this, list_string);
        listview.setAdapter(customAdapter);


    }



}
