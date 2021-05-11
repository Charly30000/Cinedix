package com.example.cinedix.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cinedix.ui.DashboardActivity;
import com.example.cinedix.R;
import com.example.cinedix.common.Constantes;
import com.example.cinedix.common.SharedPreferencesManager;
import com.example.cinedix.models.request.RequestLogin;
import com.example.cinedix.models.response.ResponseAuth;
import com.example.cinedix.retrofit.CinedixClient;
import com.example.cinedix.retrofit.CinedixService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsername, etPassword;
    Button btnLogin;
    TextView tvSignIn;
    CinedixClient cinedixClient;
    CinedixService cinedixService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        retrofitInit();
        findViews();
        events();

    }

    private void retrofitInit() {
        cinedixClient = CinedixClient.getInstance();
        cinedixService = cinedixClient.getCinedixService();
    }

    private void events() {
        tvSignIn.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    private void findViews() {
        etUsername = findViewById(R.id.etUsername);
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
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (username.isEmpty()) {
            etUsername.setError("El nombre de usuario es requerido");
        } else if (password.isEmpty()) {
            etPassword.setError("La contraseña es requerida");
        } else {
            RequestLogin requestLogin = new RequestLogin(username, password);

            Call<ResponseAuth> call = cinedixService.doLogin(requestLogin);
            call.enqueue(new Callback<ResponseAuth>() {
                @Override
                public void onResponse(Call<ResponseAuth> call, Response<ResponseAuth> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Sesion iniciada correctamente", Toast.LENGTH_SHORT).show();

                        SharedPreferencesManager.setSomeStringValue(Constantes.PREF_TOKEN, response.body().getToken());
                        SharedPreferencesManager.setSomeStringValue(Constantes.PREF_USERNAME, response.body().getUser().getUsername());
                        SharedPreferencesManager.setSomeStringValue(Constantes.PREF_AUTHORITY, response.body().getUser().getAuthorities().get(0).getAuthority());
                        SharedPreferencesManager.setSomeBooleanValue(Constantes.PREF_ACCOUNT_NON_EXPIRED, response.body().getUser().getAccountNonExpired());
                        SharedPreferencesManager.setSomeBooleanValue(Constantes.PREF_ACCOUNT_NON_LOCKED, response.body().getUser().getAccountNonLocked());
                        SharedPreferencesManager.setSomeBooleanValue(Constantes.PREF_CREDENTIALS_NON_EXPIRED, response.body().getUser().getCredentialsNonExpired());
                        SharedPreferencesManager.setSomeBooleanValue(Constantes.PREF_ENABLED, response.body().getUser().getEnabled());

                        Intent i = new Intent(MainActivity.this, DashboardActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Algo fue mal, revisa tus datos", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseAuth> call, Throwable t) {
                    Toast.makeText(MainActivity.this,
                            "Problemas de conexion. Intentelo de nuevo", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void goToSignIn() {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
        finish();
    }
}