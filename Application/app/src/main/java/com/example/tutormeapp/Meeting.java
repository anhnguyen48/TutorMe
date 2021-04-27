package com.example.tutormeapp;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Meeting extends AppCompatActivity {

    public String URL = "jdbc:mysql://frodo.bentley.edu:3306/tutorme";
    public String username = "harry";
    public String password = "harry";

    private ListView listview;
    private ArrayList<String> list_string = new ArrayList<String>();

    private String userID;

    private String section;
    private String course_id;
    private String course_name;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting);

        userID = getIntent().getStringExtra("userID"); //grab userID from MainActivity

        listview = (ListView)findViewById(R.id.list);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try (Connection con = DriverManager.getConnection(URL, username, password)) {
            String sql = "SELECT SectionID FROM Assignment WHERE PersonID = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, userID);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                section = result.getString("SectionID");

                //get ClassID in Section table using SectionID from Assignment table
                String sql2 = "SELECT ClassID FROM Section WHERE SectionID = ? LIMIT 1;";
                PreparedStatement stmt2 = con.prepareStatement(sql2);
                stmt2.setString(1, section);
                ResultSet result2 = stmt2.executeQuery();

                while (result2.next()) {
                course_id = result2.getString("ClassID");
                }

                //get ClassName in Class table using ClassID from Section table
                String sql3 = "SELECT ClassName FROM Class WHERE ClassID = ? LIMIT 1;";
                PreparedStatement stmt3 = con.prepareStatement(sql3);
                stmt3.setString(1, course_id);
                ResultSet result3 = stmt3.executeQuery();

                while (result3.next()) {
                    course_name = result3.getString("ClassName");
                }

                String sql4 = "SELECT SectionDate, SectionTime, Length, Capacity " +
                        "FROM Section WHERE SectionID = ?";
                PreparedStatement stmt4 = con.prepareStatement(sql4);
                stmt4.setString(1, section);
                ResultSet result4 = stmt4.executeQuery();
                while (result4.next()) {
                    list_string.add(course_name + " " +
                            result4.getString("SectionDate") + " " +
                            result4.getString("SectionTime") + " " +
                            result4.getString("Length") + " " +
                            result4.getString("Capacity"));
                }
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

        CustomAdapter customAdapter = new CustomAdapter(this, list_string);
        listview.setAdapter(customAdapter);

    }

}
