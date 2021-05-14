package com.example.cinedix.retrofit;

import com.example.cinedix.common.Constantes;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthEntradasClient {

    private static AuthEntradasClient instance = null;
    private AuthEntradasService authEntradasService;
    private Retrofit retrofit;

    public AuthEntradasClient() {
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

        authEntradasService = retrofit.create(AuthEntradasService.class);
    }

    // Patron Singleton
    public static AuthEntradasClient getInstance() {
        if (instance == null) {
            instance = new AuthEntradasClient();
        }

        return instance;
    }

    public AuthEntradasService getAuthEntradasService() {
        return authEntradasService;
    }
}
