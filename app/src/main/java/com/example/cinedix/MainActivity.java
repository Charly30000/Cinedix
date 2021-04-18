package com.example.cinedix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName, etPassword;
    Button btnLogin;
    TextView tvSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relations();
        clickListener();

    }

    private void clickListener() {
        tvSignIn.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    private void relations() {
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        tvSignIn = findViewById(R.id.tvSignIn);
        btnLogin = findViewById(R.id.btnLogin);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.tvSignIn:
                goToSignIn();
                break;
            case R.id.btnLogin:
                goToDashboard();
                break;
        }
    }

    private void goToDashboard() {
        Intent i = new Intent(this, DashboardActivity.class);
        startActivity(i);
        finish();
    }

    private void goToSignIn() {
        Intent i = new Intent(this, SignInActivity.class);
        startActivity(i);
        finish();
    }
}