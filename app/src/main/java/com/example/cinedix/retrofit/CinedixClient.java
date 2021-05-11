package com.example.cinedix.retrofit;

import com.example.cinedix.common.Constantes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CinedixClient {

    private static CinedixClient instance = null;
    private CinedixService cinedixService;
    private Retrofit retrofit;

    public CinedixClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cinedixService = retrofit.create(CinedixService.class);
    }

    // Patron Singleton
    public static CinedixClient getInstance() {
        if (instance == null) {
            instance = new CinedixClient();
        }

        return instance;
    }

    public CinedixService getCinedixService() {
        return cinedixService;
    }
}
