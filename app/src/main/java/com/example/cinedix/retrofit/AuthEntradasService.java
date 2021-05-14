package com.example.cinedix.retrofit;

import com.example.cinedix.models.entity.Entrada;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AuthEntradasService {

    @GET("/api/clientes/entradas")
    Call<List<Entrada>> getEntradas();

}
