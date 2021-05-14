package com.example.cinedix.retrofit;

import com.example.cinedix.common.Constantes;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthUsuarioClient {

    private static AuthUsuarioClient instance = null;
    private AuthUsuarioService authUsuarioService;
    private Retrofit retrofit;

    public AuthUsuarioClient() {
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

        authUsuarioService = retrofit.create(AuthUsuarioService.class);
    }

    // Patron Singleton
    public static AuthUsuarioClient getInstance() {
        if (instance == null) {
            instance = new AuthUsuarioClient();
        }

        return instance;
    }

    public AuthUsuarioService getAuthUsuarioService() {
        return authUsuarioService;
    }
}
