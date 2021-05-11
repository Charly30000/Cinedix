package com.example.cinedix.retrofit;

import com.example.cinedix.models.request.RequestLogin;
import com.example.cinedix.models.request.RequestSignup;
import com.example.cinedix.models.response.ResponseAuth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CinedixService {

    @POST("login")
    Call<ResponseAuth> doLogin(@Body RequestLogin requestLogin);

    @POST("clientes/usuario/crear")
    Call<ResponseAuth> doSignUp(@Body RequestSignup requestSignup);

}
