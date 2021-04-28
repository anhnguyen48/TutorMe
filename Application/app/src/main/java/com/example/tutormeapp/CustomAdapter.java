package com.example.tutormeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    ArrayList<String> arrayList;
    Context context;

    public CustomAdapter(Context context, ArrayList<String> arrayList) { //set up CustomAdapter that takes in an ArrayList
        super(context, 0, arrayList);
        this.arrayList = arrayList;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        String meetingInfo = arrayList.get(position); //grab each tutoring session in ArrayList
        String[] detail_info = meetingInfo.split(" "); //split the string wherever there is a space

        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.meeting_item, null);

            //set up how each session should be displayed
            String tempString = "Course Number: " + detail_info[0] +
                    "\nDate: " + detail_info[1] +
                    "\nTime: " + detail_info[2] +
                    "\nMeeting Length: " + detail_info[3] + " minutes" +
                    "\nCapacity: " + detail_info[4];

            TextView session = convertView.findViewById(R.id.session);
            session.setText(tempString);
        }
        return convertView;
    }
}
