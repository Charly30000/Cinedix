package com.example.cinedix.retrofit;

import com.example.cinedix.models.entity.Pelicula;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AuthPeliculasService {

    @GET("api/clientes/peliculas/venta")
    Call<List<Pelicula>> getPeliculasCartelera();
}
