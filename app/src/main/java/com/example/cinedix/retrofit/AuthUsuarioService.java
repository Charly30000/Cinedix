package com.example.cinedix.retrofit;

import com.example.cinedix.models.entity.ModificarUsuario;
import com.example.cinedix.models.entity.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface AuthUsuarioService {

    @GET("api/clientes/usuario")
    Call<Usuario> getUsuario();

    @PUT("api/clientes/usuario/modificar")
    Call<ModificarUsuario> modificarUsuario(@Body ModificarUsuario usuario);
}
