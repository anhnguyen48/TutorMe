package com.example.tutormeapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import androidx.appcompat.app.AppCompatActivity;

// test
public class Login extends AppCompatActivity implements View.OnClickListener{
    public String URL = "jdbc:mysql://frodo.bentley.edu:3306/tutorme";
    public String username = "harry";
    public String password = "harry";
    private EditText user;
    private EditText pass;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button loginButton = (Button) findViewById(R.id.loginScreenButton);
        loginButton.setOnClickListener(this);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.loginScreenButton:
                try (Connection con = DriverManager.getConnection(URL, username, password)) {
                    EditText user = findViewById(R.id.username);
                    EditText pass = findViewById(R.id.password);
                    String sql = "SELECT pass FROM Person WHERE UserName = ? LIMIT 1";
                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.setString(1, String.valueOf(user.getText()));
                    ResultSet result = stmt.executeQuery();
                    result.next();
                    if(result.getString(1).equals(String.valueOf(pass.getText()))){
                        String sql2 = "SELECT PersonID from Person WHERE UserName = ? LIMIT 1";
                        PreparedStatement stmt2 = con.prepareStatement(sql2);
                        stmt2.setString(1, String.valueOf(user.getText()));
                        ResultSet result2 = stmt2.executeQuery();
                        while (result2.next()) {
                            userID = result2.getString("PersonID");
                        }
                        Intent i1 = new Intent(this, MainActivity.class);
                        i1.putExtra("userID", userID); //Bring userID to MainActivity for later use
                        startActivity(i1);
                    }
                    else {
                        Toast.makeText(this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
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
                break;
        }
    }
}
