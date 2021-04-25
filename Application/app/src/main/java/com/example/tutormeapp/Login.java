package com.example.tutormeapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity implements View.OnClickListener{
    public String URL = "jdbc:mysql://frodo.bentley.edu:3306/tutorme";
    public String username = "harry";
    public String password = "harry";
    private EditText user;
    private EditText pass;

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
                        Intent i1= new Intent(this, MainActivity.class);
                        startActivity(i1);
                    }
                    try {
                        if (con != null)
                            con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
