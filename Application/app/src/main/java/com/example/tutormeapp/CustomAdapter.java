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

    public CustomAdapter(Context context, ArrayList<String> arrayList) {
        super(context, 0, arrayList);
        this.arrayList = arrayList;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        String meetingInfo = arrayList.get(position);
        String[] detail_info = meetingInfo.split(" ");

        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.meeting_item, null);

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
