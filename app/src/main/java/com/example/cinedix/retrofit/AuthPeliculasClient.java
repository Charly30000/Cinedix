package com.example.cinedix.retrofit;

import com.example.cinedix.common.Constantes;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthPeliculasClient {

    private static AuthPeliculasClient instance = null;
    private AuthPeliculasService authPeliculasService;
    private Retrofit retrofit;

    public AuthPeliculasClient() {
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

        authPeliculasService = retrofit.create(AuthPeliculasService.class);
    }

    // Patron Singleton
    public static AuthPeliculasClient getInstance() {
        if (instance == null) {
            instance = new AuthPeliculasClient();
        }

        return instance;
    }

    public AuthPeliculasService getAuthPeliculasService() {
        return authPeliculasService;
    }
}
