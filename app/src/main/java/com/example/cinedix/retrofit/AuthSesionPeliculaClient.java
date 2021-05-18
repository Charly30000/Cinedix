package com.example.cinedix.retrofit;

import com.example.cinedix.common.Constantes;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthSesionPeliculaClient {

    private static AuthSesionPeliculaClient instance = null;
    private AuthSesionPeliculaService authSesionPeliculaService;
    private Retrofit retrofit;

    public AuthSesionPeliculaClient() {
        // Incluir en la cabecera de la peticion el token que autoriza al usuario
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(new AuthInterceptor());
        OkHttpClient cliente = okHttpClientBuilder.build();

        // Remote > Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(cliente)
                .build();

        authSesionPeliculaService = retrofit.create(AuthSesionPeliculaService.class);
    }

    // Patron Singleton
    public static AuthSesionPeliculaClient getInstance() {
        if (instance == null) {
            instance = new AuthSesionPeliculaClient();
        }

        return instance;
    }

    public AuthSesionPeliculaService getAuthPeliculasService() {
        return authSesionPeliculaService;
    }
}
