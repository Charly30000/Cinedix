package com.example.cinedix.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cinedix.R;
import com.example.cinedix.retrofit.request.RequestSignup;
import com.example.cinedix.retrofit.response.ResponseAuth;
import com.example.cinedix.retrofit.CinedixClient;
import com.example.cinedix.retrofit.CinedixService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsername, etEmail, etPassword, etRepeatPassword;
    Button btnSignUp;
    TextView tvBack;
    CinedixClient cinedixClient;
    CinedixService cinedixService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        retrofitInit();
        findViews();
        events();

    }

    private void retrofitInit() {
        cinedixClient = CinedixClient.getInstance();
        cinedixService = cinedixClient.getCinedixService();
    }

    private void events() {
        tvBack.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    private void findViews() {
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etRepeatPassword = findViewById(R.id.etRepeatPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvBack = findViewById(R.id.tvBack);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.tvBack:
                goToMain();
                break;
            case R.id.btnSignUp:
                signUp();
                break;
        }
    }

    private void signUp() {
        String username = etUsername.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String repeatPassword = etRepeatPassword.getText().toString();

        if (username.isEmpty() || !(username.length() >= 5 && username.length() <= 30)) {
            etUsername.setError("Debe de tener entre 5 y 30 caracteres");
            return;
        } else if (email.isEmpty()) {
            etEmail.setError("Debes de introducir un Email");
            return;
        } else if (password.isEmpty() || !(password.length() >= 8 && password.length() <= 60)) {
            etPassword.setError("La contraseña debe de tener entre 8 y 60 caracteres");
            return;
        } else if (!repeatPassword.equals(password)) {
            etRepeatPassword.setError("Las contraseñas deben ser identicas!");
            return;
        } else {
            RequestSignup requestSignup = new RequestSignup(username, password, email);
            Call<ResponseAuth> call = cinedixService.doSignUp(requestSignup);
            call.enqueue(new Callback<ResponseAuth>() {
                @Override
                public void onResponse(Call<ResponseAuth> call, Response<ResponseAuth> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(SignUpActivity.this, "Cuenta creada exitosamente!", Toast.LENGTH_SHORT).show();
                        goToMain();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Ha ocurrido un error, revise los datos o cambie el nombre de usuario", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseAuth> call, Throwable t) {
                    Toast.makeText(SignUpActivity.this, "Error en la conexion, comprueba tu conexion a internet", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void goToMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.goToMain();
    }
}