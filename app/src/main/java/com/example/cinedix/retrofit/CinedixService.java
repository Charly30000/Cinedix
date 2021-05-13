package com.example.cinedix.retrofit;

import com.example.cinedix.retrofit.request.RequestLogin;
import com.example.cinedix.retrofit.request.RequestSignup;
import com.example.cinedix.retrofit.response.ResponseAuth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CinedixService {

    @POST("api/login")
    Call<ResponseAuth> doLogin(@Body RequestLogin requestLogin);

    @POST("api/clientes/usuario/crear")
    Call<ResponseAuth> doSignUp(@Body RequestSignup requestSignup);

}
