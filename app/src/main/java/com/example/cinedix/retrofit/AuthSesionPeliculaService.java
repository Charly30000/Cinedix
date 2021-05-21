package com.example.cinedix.retrofit;

import com.example.cinedix.models.entity.GenericResponse;
import com.example.cinedix.models.entity.SesionPelicula;
import com.example.cinedix.models.entity.SesionPeliculaRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthSesionPeliculaService {

    @GET("api/sesionpelicula/cines/{id}")
    public Call<List<SesionPelicula>> getSesionesPeliculas(@Path("id") Long id);

    @GET("api/sesionpelicula/{cineid}/{peliculaid}")
    public Call<List<SesionPelicula>> getSesionesPeliculasHoras(@Path("cineid") Long cineid, @Path("peliculaid") Long peliculaid);

    @POST("api/clientes/entradas/crear")
    public Call<GenericResponse> comprarEntrada(@Body SesionPeliculaRequest sesionPelicula);
}
