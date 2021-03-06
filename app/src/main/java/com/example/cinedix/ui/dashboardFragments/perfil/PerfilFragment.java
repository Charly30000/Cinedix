package com.example.cinedix.ui.dashboardFragments.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cinedix.R;
import com.example.cinedix.common.Constantes;
import com.example.cinedix.common.SharedPreferencesManager;
import com.example.cinedix.models.entity.ModificarUsuario;
import com.example.cinedix.models.entity.Usuario;
import com.example.cinedix.retrofit.AuthUsuarioClient;
import com.example.cinedix.retrofit.AuthUsuarioService;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFragment extends Fragment implements View.OnClickListener {

    private Usuario usuario;
    private AuthUsuarioClient authUsuarioClient;
    private AuthUsuarioService authUsuarioService;

    private TextView tvPerfilNombre;
    private EditText etPerfilEmail;

    private EditText etPerfilOldPassword;
    private EditText etPerfilNewPassword;
    private EditText etPerfilRepeatNewPassword;
    private Button btnPerfilSubmit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        findViews(root);
        retrofitInit();
        loadUsuarioData();
        events();
        //textView = root.findViewById(R.id.tvtest);
        //textView.setText("Estoy siendo modificado desde el perfiil");

        return root;
    }

    private void events() {
        btnPerfilSubmit.setOnClickListener(this);
    }

    private void findViews(View v) {
        tvPerfilNombre = v.findViewById(R.id.tvPerfilNombre);
        etPerfilEmail = v.findViewById(R.id.etPerfilEmail);
        etPerfilOldPassword = v.findViewById(R.id.etPerfilOldPassword);
        etPerfilNewPassword = v.findViewById(R.id.etPerfilNewPassword);
        etPerfilRepeatNewPassword = v.findViewById(R.id.etPerfilRepeatNewPassword);
        btnPerfilSubmit = v.findViewById(R.id.btnPerfilSubmit);
    }

    private void loadUsuarioData() {
        Call<Usuario> call = authUsuarioService.getUsuario();
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    usuario = response.body();
                    putData(usuario);
                } else {
                    Toast.makeText(getActivity(), "Algo ha ido mal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getActivity(), "No hay conexion a internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void putData(Usuario usuario) {
        tvPerfilNombre.setText(usuario.getUsername());
        etPerfilEmail.setText(usuario.getEmail());
    }

    private void retrofitInit() {
        authUsuarioClient = AuthUsuarioClient.getInstance();
        authUsuarioService = authUsuarioClient.getAuthUsuarioService();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btnPerfilSubmit:
                submitData();
                break;
        }
    }

    private void submitData() {
        String nombreUsuario = usuario.getUsername();
        String email = etPerfilEmail.getText().toString();
        String oldPassword = etPerfilOldPassword.getText().toString();
        String newPassword = etPerfilNewPassword.getText().toString();
        String repeatNewPassword = etPerfilRepeatNewPassword.getText().toString();

        if (email.isEmpty() || !email.matches("\\w+@\\w+\\.\\w+")) {
            etPerfilEmail.setError("Debes de rellenar correctamente este campo");
            return;
        } else if (oldPassword.isEmpty()) {
            etPerfilOldPassword.setError("Debes de rellenar este campo");
            return;
        } else if (newPassword.isEmpty() || !(newPassword.length() >= 8 && newPassword.length() <= 60)) {
            etPerfilNewPassword.setError("La contrase??a debe de tener entre 8 y 60 caracteres");
            return;
        } else if (!repeatNewPassword.equals(newPassword)) {
            etPerfilRepeatNewPassword.setError("Las contrase??as deben ser identicas!");
            return;
        } else {
            ModificarUsuario nuevoUsuario = new ModificarUsuario();
            nuevoUsuario.setUsername(nombreUsuario);
            nuevoUsuario.setOldPassword(oldPassword);
            nuevoUsuario.setNewPassword(newPassword);
            nuevoUsuario.setEmail(email);

            Call<ModificarUsuario> call = authUsuarioService.modificarUsuario(nuevoUsuario);
            call.enqueue(new Callback<ModificarUsuario>() {
                @Override
                public void onResponse(Call<ModificarUsuario> call, Response<ModificarUsuario> response) {
                    if (response.isSuccessful()) {
                        SharedPreferencesManager
                                .setSomeStringValue(Constantes.PREF_USERNAME, nuevoUsuario.getUsername());
                        Toast.makeText(getActivity(), "Usuario modificado correctamente!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Ha ocurrido un error, comprueba tus datos por favor", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ModificarUsuario> call, Throwable t) {
                    Toast.makeText(getActivity(), "No tienes conexion a internet", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}